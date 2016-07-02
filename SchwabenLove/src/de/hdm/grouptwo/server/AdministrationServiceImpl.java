package de.hdm.grouptwo.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.grouptwo.server.db.BlockMapper;
import de.hdm.grouptwo.server.db.BookmarkListMapper;
import de.hdm.grouptwo.server.db.BookmarkMapper;
import de.hdm.grouptwo.server.db.DBConnection;
import de.hdm.grouptwo.server.db.DescriptionMapper;
import de.hdm.grouptwo.server.db.InformationMapper;
import de.hdm.grouptwo.server.db.ProfileMapper;
import de.hdm.grouptwo.server.db.PropertyMapper;
import de.hdm.grouptwo.server.db.SearchProfileMapper;
import de.hdm.grouptwo.server.db.SelectionItemMapper;
import de.hdm.grouptwo.server.db.SelectionMapper;
import de.hdm.grouptwo.server.db.SimilarityDegreeMapper;
import de.hdm.grouptwo.server.db.VisitMapper;
import de.hdm.grouptwo.shared.AdministrationService;
import de.hdm.grouptwo.shared.bo.Block;
import de.hdm.grouptwo.shared.bo.Bookmark;
import de.hdm.grouptwo.shared.bo.BookmarkList;
import de.hdm.grouptwo.shared.bo.Description;
import de.hdm.grouptwo.shared.bo.Information;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.Property;
import de.hdm.grouptwo.shared.bo.SearchProfile;
import de.hdm.grouptwo.shared.bo.Selection;
import de.hdm.grouptwo.shared.bo.SelectionItem;
import de.hdm.grouptwo.shared.bo.SimilarityDegree;
import de.hdm.grouptwo.shared.bo.Visit;

/**
 * Implementation class of the Interface AdministrationService. This class
 * aggregates, besides the ReportServiceImpl class, the whole application logic.
 * The application logic is defined in the methods of this class, also it
 * includes the objects of Mapper classes, which are necessary for the Database
 * access.
 * 
 * @author manuelruss
 *
 */

public class AdministrationServiceImpl extends RemoteServiceServlet implements
		AdministrationService {
	private static final long serialVersionUID = 1L;
	private Profile user = null;
	private ArrayList<Property> properties = null;

	/**
	 * This method creates a new profile, which is also saved to the database.
	 * In addition to the profile a bookmark list, search profile and
	 * information objects for this profile are created. Then, finally the
	 * similarity degrees to other profiles are calculated.
	 */
	@Override
	public void createProfile(Profile profile) {
		profile = ProfileMapper.profileMapper().insert(profile);

		int profileId = profile.getId();

		// Create an empty bookmark list
		BookmarkList bl = new BookmarkList();
		bl.setProfileId(profileId);
		BookmarkListMapper.bookmarkListMapper().insert(bl);

		// Create a default search profile
		SearchProfile sp = new SearchProfile();
		sp.setName("Standard");
		createSearchProfile(sp, profileId);

		ArrayList<Property> properties = getAllProperties();

		// Create empty Information objects for the profile
		for (Property p : properties) {
			Information i = new Information();
			i.setProfileId(profileId);
			i.setPropertyId(p.getId());
			InformationMapper.informationMapper().insert(i);
		}

		createSimilarityDegrees(profile);
	}

	/**
	 * This method returns a users profile.
	 */
	@Override
	public Profile getProfile() {
		return user;
	}

	/**
	 * Sets an email adress to a profile.
	 */
	@Override
	public Profile setProfile(String email) {
		user = ProfileMapper.profileMapper().findByEmail(email);

		return user;
	}

	/**
	 * Update the user's profile and recalculate similarity degrees.
	 * 
	 * @param profile
	 *            The <code>Profile</code> to update to
	 * @return The updated profile (needs to be read from the database again to
	 *         recalculate the age
	 */
	@Override
	public Profile updateProfile(Profile profile) {
		ProfileMapper.profileMapper().update(profile);

		updateSimilarityDegrees();

		return ProfileMapper.profileMapper().findById(profile.getId());
	}

	/**
	 * Delete the users profile.
	 */
	@Override
	public void deleteProfile() {
		int userId = user.getId();
		ArrayList<SearchProfile> searchProfiles = getSearchProfiles();
		BookmarkList bookmarkList = BookmarkListMapper.bookmarkListMapper()
				.findByProfile(userId);
		ArrayList<Bookmark> bookmarks = BookmarkMapper.bookmarkMapper()
				.findByBookmarkListId(bookmarkList.getId());

		for (Information i : InformationMapper.informationMapper()
				.findByProfileId(userId)) {
			InformationMapper.informationMapper().delete(i);
		}

		for (SearchProfile sp : searchProfiles) {
			SearchProfileMapper.searchProfileMapper().delete(sp);
		}

		for (Block b : BlockMapper.blockMapper().findByBlockerProfileId(userId)) {
			BlockMapper.blockMapper().delete(b);
		}

		for (Block b : BlockMapper.blockMapper().findByBlockedProfileId(userId)) {
			BlockMapper.blockMapper().delete(b);
		}

		BookmarkListMapper.bookmarkListMapper().delete(bookmarkList);

		for (Bookmark b : bookmarks) {
			BookmarkMapper.bookmarkMapper().delete(b);
		}

		for (SimilarityDegree sd : SimilarityDegreeMapper
				.similarityDegreeMapper().findByReferenceProfileId(userId)) {
			SimilarityDegreeMapper.similarityDegreeMapper().delete(sd);
		}

		for (SimilarityDegree sd : SimilarityDegreeMapper
				.similarityDegreeMapper().findByComparisonProfileId(userId)) {
			SimilarityDegreeMapper.similarityDegreeMapper().delete(sd);
		}

		ProfileMapper.profileMapper().delete(user);
	}

	@Override
	public void updateInformation(Information information) {
		InformationMapper.informationMapper().update(information);
		updateSimilarityDegrees();
	}

	/**
	 * Adds a bookmark based on the profile id.
	 */
	@Override
	public void addBookmarkByProfileId(int profileId) {
		Bookmark bookmark = new Bookmark();
		bookmark.setBookmarkListId(BookmarkListMapper.bookmarkListMapper()
				.findByProfile(user.getId()).getId());
		bookmark.setProfileId(profileId);

		BookmarkMapper.bookmarkMapper().insert(bookmark);
	}

	/**
	 * Adds a block to a profile of another user, based on the profile id.
	 */
	@Override
	public void addBlockByProfileId(int profileId) {
		Block block = new Block();
		block.setBlockerProfileId(user.getId());
		block.setBlockedProfileId(profileId);

		BlockMapper.blockMapper().insert(block);
	}

	/**
	 * Returns the profile id.
	 */
	@Override
	public Profile getProfileById(int id) {
		return ProfileMapper.profileMapper().findById(id);
	}

	/**
	 * Returns the information of the profile.
	 */
	@Override
	public ArrayList<Information> getInformationByProfileId(int profileId) {
		ArrayList<Information> information = InformationMapper
				.informationMapper().findByProfileId(profileId);

		Iterator<Information> it = information.iterator();
		while (it.hasNext()) {
			Information i = it.next();

			if (i.getSearchProfileId() != 0) {
				it.remove();
			}
		}

		return information;
	}

	/**
	 * Returns the similarity degree of the profile.
	 */
	@Override
	public SimilarityDegree getSimilarityDegreeByProfileId(int profileId) {
		SimilarityDegree similarityDegree = null;
		for (SimilarityDegree s : SimilarityDegreeMapper
				.similarityDegreeMapper()
				.findByReferenceProfileId(user.getId())) {
			if (s.getComparisonProfileId() == profileId) {
				similarityDegree = s;
			}
		}

		return similarityDegree;
	}

	/**
	 * Returns the existing searchprofiles.
	 */
	@Override
	public ArrayList<SearchProfile> getSearchProfiles() {
		ArrayList<SearchProfile> searchProfiles = new ArrayList<SearchProfile>();
		LinkedHashSet<Integer> searchProfileIds = new LinkedHashSet<Integer>();

		for (Information i : InformationMapper.informationMapper()
				.findByProfileId(user.getId())) {
			if (i.getSearchProfileId() != 0) {
				searchProfileIds.add(i.getSearchProfileId());
			}
		}

		for (Integer id : searchProfileIds) {
			searchProfiles.add(SearchProfileMapper.searchProfileMapper()
					.findById(id));
		}

		return searchProfiles;
	}

	/**
	 * Create a new search profile and the associated information objects.
	 * 
	 * @param searchProfile
	 *            The search profile to create
	 * @param profileId
	 *            The profileId for which profile it will be created
	 * @return The created <code>SearchProfile</code> object
	 */
	private SearchProfile createSearchProfile(SearchProfile searchProfile,
			int profileId) {
		searchProfile = SearchProfileMapper.searchProfileMapper().insert(
				searchProfile);

		// Create empty Information objects for the search profile
		for (Property p : getAllProperties()) {
			Information i = new Information();
			i.setProfileId(profileId);
			i.setPropertyId(p.getId());
			i.setSearchProfileId(searchProfile.getId());
			InformationMapper.informationMapper().insert(i);
		}

		return searchProfile;
	}

	/**
	 * Create a new searchprofile.
	 */
	@Override
	public SearchProfile createSearchProfile(SearchProfile searchProfile) {
		return createSearchProfile(searchProfile, user.getId());
	}

	/**
	 * Update the attributes of an existing searchprofile of the current user.
	 */
	@Override
	public void updateSearchProfile(SearchProfile searchProfile) {
		SearchProfileMapper.searchProfileMapper().update(searchProfile);
	}

	/**
	 * Delete a existing searchprofile of the current user.
	 */
	@Override
	public void deleteSearchProfile(SearchProfile searchProfile) {
		for (Information i : InformationMapper.informationMapper()
				.findBySearchProfileId(searchProfile.getId())) {
			InformationMapper.informationMapper().delete(i);
		}

		SearchProfileMapper.searchProfileMapper().delete(searchProfile);
	}

	/**
	 * Return the matches by the selected searchprofile, depending on the
	 * selected attributes.
	 */
	@Override
	public ArrayList<Profile> getMatchesBySearchProfile(SearchProfile sp) {
		ArrayList<Profile> matches = new ArrayList<Profile>();

		// Get profiles ordered by similarity degree
		ArrayList<SimilarityDegree> similarityDegrees = getSimilarityDegrees();

		Collections.sort(similarityDegrees, new Comparator<SimilarityDegree>() {
			public int compare(SimilarityDegree o1, SimilarityDegree o2) {
				return o1.getScore() - o2.getScore();
			}
		});

		ArrayList<Profile> profiles = new ArrayList<Profile>();
		for (SimilarityDegree sd : similarityDegrees) {
			profiles.add(ProfileMapper.profileMapper().findById(
					sd.getComparisonProfileId()));
		}

		for (Profile p : profiles) {
			if (sp.getGender() != null) {
				if (!p.getGender().equals(sp.getGender())) {
					continue;
				}
			}

			if (sp.getMinAge() != 0) {
				if (p.getAge() < sp.getMinAge()) {
					continue;
				}
			}
			if (sp.getMaxAge() != 0) {
				if (p.getAge() > sp.getMaxAge()) {
					continue;
				}
			}
			if (sp.getHairColor() != null) {
				if (!p.getHairColor().equals(sp.getHairColor())) {
					continue;
				}
			}
			if (sp.getPhysique() != null) {
				if (!p.getPhysique().equals(sp.getPhysique())) {
					continue;
				}
			}
			if (sp.getMinHeight() != 0) {
				if (p.getHeight() < sp.getMinHeight()) {
					continue;
				}
			}
			if (sp.getMaxHeight() != 0) {
				if (p.getHeight() > sp.getMaxHeight())
				{
					continue;
				}
			}

			if (sp.getSmoker() != null) {

				if (!p.getSmoker().equals(sp.getSmoker())) {
					continue;
				}
			}
			if (sp.getEducation() != null)
			{
				if (!p.getEducation().equals(sp.getEducation())) {
					continue;
				}
			}
			if (sp.getProfession() != null) {
				if (!p.getProfession().equals(sp.getProfession())) {
					continue;
				}
			}
			if (sp.getReligion() != null)
			{
				if (!p.getReligion().equals(sp.getReligion())) {
					continue;
				}
			}

			matches.add(p);
		}

		// Remove profiles that we blocked or that have blocked us
		ArrayList<Profile> blocks = getBlockedProfiles();

		for (Block b : BlockMapper.blockMapper().findByBlockedProfileId(
				user.getId())) {
			blocks.add(ProfileMapper.profileMapper().findById(
					b.getBlockerProfileId()));
		}

		Iterator<Profile> it = matches.iterator();
		while (it.hasNext()) {
			Profile match = it.next();

			for (Profile block : blocks) {
				if (block.getId() == match.getId()) {
					it.remove();
					break;
				}
			}
		}

		// ToDo: filter by information objects as well

		return matches;
	}

	/**
	 * Return the bookmarked profiles of the current user.
	 */
	@Override
	public ArrayList<Profile> getBookmarkedProfiles() {
		ArrayList<Profile> profiles = new ArrayList<Profile>();

		for (Bookmark b : getBookmarks()) {
			profiles.add(ProfileMapper.profileMapper().findById(
					b.getProfileId()));
		}

		return profiles;
	}

	/**
	 * Check if the profile is bookmarked already.
	 */
	@Override
	public boolean checkBookmarked(int profileId) {
		for (Bookmark b : getBookmarks()) {
			if (b.getProfileId() == profileId) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the blocked profiles of the current user.
	 */
	@Override
	public ArrayList<Profile> getBlockedProfiles() {
		ArrayList<Profile> profiles = new ArrayList<Profile>();

		// Loop through blocks and add their referenced blocked profile
		// to profiles list
		for (Block b : BlockMapper.blockMapper().findByBlockerProfileId(
				user.getId())) {
			profiles.add(ProfileMapper.profileMapper().findById(
					b.getBlockedProfileId()));
		}

		return profiles;
	}

	/**
	 * Delete an existing bookmark of the current user.
	 */
	@Override
	public void deleteBookmark(int profileId) {
		for (Bookmark b : getBookmarks()) {
			if (b.getProfileId() == profileId) {
				BookmarkMapper.bookmarkMapper().delete(b);
				return;
			}
		}
	}

	/**
	 * Delete an existing block of the current user.
	 */
	@Override
	public void deleteBlock(int profileId) {
		// Find the profile to delete within the users blocks
		for (Block b : BlockMapper.blockMapper().findByBlockerProfileId(
				user.getId())) {
			if (b.getBlockedProfileId() == profileId) {
				BlockMapper.blockMapper().delete(b);
				return;
			}
		}
	}

	@Override
	public ArrayList<String> loadTableNames() {
		ArrayList<String> tableNames = new ArrayList<String>();

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT TABLE_NAME FROM information_schema.tables "
							+ "WHERE TABLE_SCHEMA = 'schwabenlove'");

			while (rs.next()) {
				tableNames.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tableNames;
	}

	/**
	 * Show all existing blocks.
	 */
	@Override
	public ArrayList<Block> getAllBlocks() {
		return BlockMapper.blockMapper().findAll();
	}

	/**
	 * Return all bookmarklists.
	 */
	@Override
	public ArrayList<BookmarkList> getAllBookmarkLists() {
		return BookmarkListMapper.bookmarkListMapper().findAll();
	}

	/**
	 * Return all bookmarks.
	 */
	@Override
	public ArrayList<Bookmark> getAllBookmarks() {
		return BookmarkMapper.bookmarkMapper().findAll();
	}

	/**
	 * Return all descriptions.
	 */
	@Override
	public ArrayList<Description> getAllDescriptions() {
		return DescriptionMapper.descriptionMapper().findAll();
	}

	/**
	 * Return all information.
	 */
	@Override
	public ArrayList<Information> getAllInformation() {
		return InformationMapper.informationMapper().findAll();
	}

	/**
	 * Return all profiles.
	 */
	@Override
	public ArrayList<Profile> getAllProfiles() {
		return ProfileMapper.profileMapper().findAll();
	}

	/**
	 * Return all properties.
	 */
	@Override
	public ArrayList<Property> getAllProperties() {
		if (properties == null) {
			properties = PropertyMapper.propertyMapper().findAll();
		}

		return properties;
	}

	/**
	 * Return all searchprofiles.
	 */
	@Override
	public ArrayList<SearchProfile> getAllSearchProfiles() {
		return SearchProfileMapper.searchProfileMapper().findAll();
	}

	/**
	 * Return all selectionitems.
	 */
	@Override
	public ArrayList<SelectionItem> getAllSelectionItems() {
		return SelectionItemMapper.selectionItemMapper().findAll();
	}

	/**
	 * Return all selections.
	 */
	@Override
	public ArrayList<Selection> getAllSelections() {
		return SelectionMapper.selectionMapper().findAll();
	}

	/**
	 * Return all similaritydegrees.
	 */
	@Override
	public ArrayList<SimilarityDegree> getAllSimilarityDegrees() {
		return SimilarityDegreeMapper.similarityDegreeMapper().findAll();
	}

	/**
	 * Return all visits.
	 */
	@Override
	public ArrayList<Visit> getAllVisits() {
		return VisitMapper.visitMapper().findAll();
	}

	/**
	 * Return all selection items from a specific selection.
	 */
	@Override
	public ArrayList<SelectionItem> getSelectionItems(int selectionId) {
		return SelectionItemMapper.selectionItemMapper().findBySelection(
				selectionId);
	}

	/**
	 * Method to validate a birthdate. Checks if the day and month are valid and
	 * if the user is within the permissible age range.<br>
	 * This check is performed on the server side because GWT does not support
	 * <code>Calendar</code> on the client side.
	 * 
	 * @param day
	 *            the value for the day
	 * @param month
	 *            the value for the month
	 * @param year
	 *            the value for the year
	 * 
	 * @return <code>true</code> if the date is valid, <code>false</code> if it
	 *         isn't
	 */
	@Override
	public boolean validateDate(int day, int month, int year) {
		int threshold = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			threshold = 31;
			break;
		case 2:
			threshold = new GregorianCalendar().isLeapYear(year) ? 29 : 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			threshold = 30;
			break;
		default:
			return false;
		}

		if (day < 1 || day > threshold) {
			return false;
		}

		Calendar calendar = Calendar.getInstance();
		// Check if the user is at least 18 years old...
		calendar.add(Calendar.YEAR, -18);
		Date minAge = calendar.getTime();
		// and at most 100 years old
		calendar.add(Calendar.YEAR, -82);
		Date maxAge = calendar.getTime();

		calendar.set(year, month - 1, day);
		Date birthdate = calendar.getTime();

		if (birthdate.after(minAge) || birthdate.before(maxAge)) {
			return false;
		}

		return true;
	}

	/**
	 * Get the bookmark list ID of the current user.
	 * 
	 * @return
	 */
	private ArrayList<Bookmark> getBookmarks() {
		// Get the bookmark list ID of the current user
		int bookmarkListId = BookmarkListMapper.bookmarkListMapper()
				.findByProfile(user.getId()).getId();

		return BookmarkMapper.bookmarkMapper().findByBookmarkListId(
				bookmarkListId);
	}

	private void updateSimilarityDegrees() {
		ArrayList<Profile> comparisonProfiles = getAllProfiles();
		final int userId = user.getId();

		// Update similarity degrees to and from everyone
		SimilarityDegree sd = new SimilarityDegree();
		for (Profile p : comparisonProfiles) {
			if (!user.equals(p)) {
				sd.setScore(calculateSimilarityDegreeScore(user, p));

				sd.setReferenceProfileId(userId);
				sd.setComparisonProfileId(p.getId());
				SimilarityDegreeMapper.similarityDegreeMapper().update(sd);

				sd.setReferenceProfileId(p.getId());
				sd.setComparisonProfileId(userId);
				SimilarityDegreeMapper.similarityDegreeMapper().update(sd);
			}
		}
	}

	private void createSimilarityDegrees(Profile profile) {
		ArrayList<Profile> comparisonProfiles = getAllProfiles();
		final int userId = profile.getId();

		// Create similarity degrees to and from everyone
		SimilarityDegree sd = new SimilarityDegree();
		for (Profile p : comparisonProfiles) {
			if (!profile.equals(p)) {
				sd.setScore(calculateSimilarityDegreeScore(profile, p));

				sd.setReferenceProfileId(userId);
				sd.setComparisonProfileId(p.getId());
				SimilarityDegreeMapper.similarityDegreeMapper().insert(sd);

				sd.setReferenceProfileId(p.getId());
				sd.setComparisonProfileId(userId);
				SimilarityDegreeMapper.similarityDegreeMapper().insert(sd);
			}
		}
	}

	private int calculateSimilarityDegreeScore(Profile ref,
			Profile comp) {
		int score = 0;

		if (ref.getLocation().equals(comp.getLocation())) {
			score += 18;
		}

		score += Math.max(0, 10 - Math.abs(ref.getHeight() - comp.getHeight()));

		if (ref.getPhysique().equals(comp.getPhysique())) {
			score += 12;
		}

		if (ref.getSmoker().equals(comp.getSmoker())) {
			score += 26;
		}

		if (ref.getEducation().equals(comp.getEducation())) {
			score += 18;
		}

		if (ref.getProfession().equals(comp.getProfession())) {
			score += 22;
		}

		if (ref.getReligion().equals(comp.getReligion())) {
			score += 26;
		}

		ArrayList<Information> refInformation = getInformationByProfileId(
				ref.getId());
		ArrayList<Information> compInformation = getInformationByProfileId(
				comp.getId());

		for (Information refInfo : refInformation) {
			for (Information compInfo : compInformation) {
				if (refInfo.getPropertyId() == compInfo.getPropertyId()) {
					if (refInfo.getInputText() != null
							&& !refInfo.getInputText().isEmpty()
							&& compInfo.getInputText() != null
							&& !compInfo.getInputText().isEmpty()) {
						if (refInfo.getInputText().equals(
								compInfo.getInputText())) {
							score += 12;
						}
					}
				}
			}
		}

		return score;
	}

	/**
	 * Get all similarity degrees from off the user.
	 * 
	 * @return The similarity degrees
	 */
	private ArrayList<SimilarityDegree> getSimilarityDegrees() {
		return SimilarityDegreeMapper.similarityDegreeMapper()
				.findByReferenceProfileId(user.getId());
	}

	public ArrayList<Profile> getUnvisitedProfiles(Profile profile) {

		// Get all Visits
		ArrayList<Visit> allVisits = new ArrayList<Visit>();
		allVisits = VisitMapper.visitMapper().findByVisitorProfileId(
				profile.getId());

		// Get all Matches
		SearchProfile dummyProfile = new SearchProfile();
		ArrayList<Profile> allMatches = new ArrayList<Profile>();
		allMatches = getMatchesBySearchProfile(dummyProfile);

		// Remove visited matches von ArrayList
		for (Visit visit : allVisits) {
			for (Profile match : allMatches) {
				if (visit.getVisitedProfileId() == match.getId()) {
					int index = allMatches.indexOf(match);
					allMatches.remove(index);
				}
			}
		}
		return allMatches;
	}
}
