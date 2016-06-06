package de.hdm.grouptwo.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.grouptwo.server.db.ProfileMapper;
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

@RemoteServiceRelativePath("administration")
public interface AdministrationService extends RemoteService {
	public Profile getProfile();
	public void setProfile(String email);
	
	public Profile getProfileById(int id);
	
	public ArrayList<Profile> getMatchesByProfileId(int profileId);

	public String insertDemoProfile();

	public ArrayList<String> loadTableNames();

	public ArrayList<Block> getAllBlocks();

	public ArrayList<BookmarkList> getAllBookmarkLists();

	public ArrayList<Bookmark> getAllBookmarks();

	public ArrayList<Description> getAllDescriptions();

	public ArrayList<Information> getAllInformation();

	public ArrayList<Profile> getAllProfiles();

	public ArrayList<Property> getAllProperties();

	public ArrayList<SearchProfile> getAllSearchProfiles();

	public ArrayList<SelectionItem> getAllSelectionItems();

	public ArrayList<Selection> getAllSelections();

	public ArrayList<SimilarityDegree> getAllSimilarityDegrees();

	public ArrayList<Visit> getAllVisits();
}
