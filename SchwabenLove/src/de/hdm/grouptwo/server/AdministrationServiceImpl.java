package de.hdm.grouptwo.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

public class AdministrationServiceImpl extends RemoteServiceServlet implements
		AdministrationService {
	private static final long serialVersionUID = 1L;
	private Profile user = null;

	@Override
	public void createProfile(Profile profile) {
		profile = ProfileMapper.profileMapper().insert(profile);

		int profileId = profile.getId();

		// Create an empty bookmark list
		BookmarkList bl = new BookmarkList();
		bl.setProfileId(profileId);
		BookmarkListMapper.bookmarkListMapper().insert(bl);

		// Create a default search profile
		int searchProfileId = SearchProfileMapper.searchProfileMapper()
				.insert(new SearchProfile()).getId();

		ArrayList<Property> properties = getAllProperties();

		// Create empty Information objects for the profile
		for (Property p : properties) {
			Information i = new Information();
			i.setProfileId(profileId);
			i.setPropertyId(p.getId());
			InformationMapper.informationMapper().insert(i);
		}

		// Create empty Information objects for the search profile
		for (Property p : properties) {
			Information i = new Information();
			i.setProfileId(profileId);
			i.setPropertyId(p.getId());
			i.setSearchProfileId(searchProfileId);
			InformationMapper.informationMapper().insert(i);
		}

		// ToDo: Calculate similarity degrees
	}

	@Override
	public Profile getProfile() {
		return user;
	}

	@Override
	public Profile setProfile(String email) {
		user = ProfileMapper.profileMapper().findByEmail(email);

		return user;
	}

	@Override
	public Profile getProfileById(int id) {
		return ProfileMapper.profileMapper().findById(id);
	}

	@Override
	public void addBookmarkByProfileId(int profileId) {
		Bookmark bookmark = new Bookmark();
		bookmark.setBookmarkListId(BookmarkListMapper.bookmarkListMapper()
				.findByProfile(user.getId()).getId());
		bookmark.setProfileId(profileId);
	}

	public ArrayList<Information> getInformationByProfileId(int profileId) {
		return InformationMapper.informationMapper().findByProfileId(profileId);
	}

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
	public ArrayList<SearchProfile> getSearchProfiles() {
		ArrayList<SearchProfile> searchProfiles = new ArrayList<SearchProfile>();
		LinkedHashSet<Integer> searchProfileIds = new LinkedHashSet<Integer>();

		for (Information i : InformationMapper.informationMapper()
				.findByProfileId(user.getId())) {
			searchProfileIds.add(i.getSearchProfileId());
		}

		for (Integer id : searchProfileIds) {
			searchProfiles.add(SearchProfileMapper.searchProfileMapper()
					.findById(id));
		}

		return searchProfiles;
	}

	@Override
	public SearchProfile addSearchProfile(SearchProfile searchProfile) {
		return SearchProfileMapper.searchProfileMapper().insert(searchProfile);
	}

	@Override
	public void updateSearchProfile(SearchProfile searchProfile) {
		SearchProfileMapper.searchProfileMapper().update(searchProfile);
	}

	@Override
	public void deleteSearchProfile(SearchProfile searchProfile) {
		SearchProfileMapper.searchProfileMapper().delete(searchProfile);
	}

	@Override
	public void addBlockByProfileId(int profileId) {
		Block block = new Block();
		block.setBlockerProfileId(user.getId());
		block.setBlockedProfileId(profileId);

		BlockMapper.blockMapper().insert(block);
	}

	@Override
	public ArrayList<Profile> getMatchesByProfileId(int profileId) {
		ArrayList<Profile> matches = ProfileMapper.profileMapper().findAll();

		return matches;
	}

	public ArrayList<Profile> getBookmarkedProfiles() {
		ArrayList<Profile> bookmarks = new ArrayList<Profile>();
		// get bookmarklist
		// get bookmarks by bookmarklist
		// get profiles by bookmark

		return bookmarks;
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

	@Override
	public ArrayList<Block> getAllBlocks() {
		return BlockMapper.blockMapper().findAll();
	}

	@Override
	public ArrayList<BookmarkList> getAllBookmarkLists() {
		return BookmarkListMapper.bookmarkListMapper().findAll();
	}

	@Override
	public ArrayList<Bookmark> getAllBookmarks() {
		return BookmarkMapper.bookmarkMapper().findAll();
	}

	@Override
	public ArrayList<Description> getAllDescriptions() {
		return DescriptionMapper.descriptionMapper().findAll();
	}

	@Override
	public ArrayList<Information> getAllInformation() {
		return InformationMapper.informationMapper().findAll();
	}

	@Override
	public ArrayList<Profile> getAllProfiles() {
		return ProfileMapper.profileMapper().findAll();
	}

	@Override
	public ArrayList<Property> getAllProperties() {
		return PropertyMapper.propertyMapper().findAll();
	}

	@Override
	public ArrayList<SearchProfile> getAllSearchProfiles() {
		return SearchProfileMapper.searchProfileMapper().findAll();
	}

	@Override
	public ArrayList<SelectionItem> getAllSelectionItems() {
		return SelectionItemMapper.selectionItemMapper().findAll();
	}

	@Override
	public ArrayList<Selection> getAllSelections() {
		return SelectionMapper.selectionMapper().findAll();
	}

	@Override
	public ArrayList<SimilarityDegree> getAllSimilarityDegrees() {
		return SimilarityDegreeMapper.similarityDegreeMapper().findAll();
	}

	@Override
	public ArrayList<Visit> getAllVisits() {
		return VisitMapper.visitMapper().findAll();
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
}
