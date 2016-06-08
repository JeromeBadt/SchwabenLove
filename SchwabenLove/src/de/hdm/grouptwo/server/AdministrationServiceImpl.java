package de.hdm.grouptwo.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	public Profile getProfile() {
		return user;
	}

	@Override
	public void setProfile(String email) {
		user = ProfileMapper.profileMapper().findByEmail(email);
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

	@Override
	public String insertDemoProfile() {

		Profile demo = new Profile();
		demo.setEmail("demo@user.com");
		demo.setFirstName("demo");
		demo.setLastName("User");
		demo.setGender("m");
		demo.setBirthdate(Date.valueOf("1990-01-01"));
		demo.setLocation("Stuttgart");
		demo.setHeight(180);
		demo.setPhysique("sportlich");
		demo.setHairColor("braun");
		demo.setSmoker(true);
		demo.setEducation("Abitur");
		demo.setProfession("Student");
		demo.setReligion("Christlich");

		ProfileMapper.profileMapper().insert(demo);

		return "Demo profile inserted";
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
}
