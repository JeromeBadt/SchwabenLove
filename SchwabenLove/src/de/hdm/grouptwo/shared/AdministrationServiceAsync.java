package de.hdm.grouptwo.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

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

public interface AdministrationServiceAsync {
	public void createProfile(Profile profile, AsyncCallback<Void> callback);

	public void getProfile(AsyncCallback<Profile> callback);

	public void setProfile(String email, AsyncCallback<Profile> callback);

	public void getProfileById(int id, AsyncCallback<Profile> callback);

	public void addBookmarkByProfileId(int profileId,
			AsyncCallback<Void> callback);

	public void addBlockByProfileId(int profileId, AsyncCallback<Void> callback);

	public void deleteProfile(AsyncCallback<Void> callback);

	public void getSimilarityDegreeByProfileId(int profileId,
			AsyncCallback<SimilarityDegree> callback);

	public void getSearchProfiles(
			AsyncCallback<ArrayList<SearchProfile>> callback);

	public void addSearchProfile(SearchProfile searchProfile,
			AsyncCallback<SearchProfile> callback);

	public void updateSearchProfile(SearchProfile searchProfile,
			AsyncCallback<Void> callback);

	public void deleteSearchProfile(SearchProfile searchProfile,
			AsyncCallback<Void> callback);

	public void getMatchesByProfileId(int profileId,
			AsyncCallback<ArrayList<Profile>> callback);

	public void loadTableNames(AsyncCallback<ArrayList<String>> callback);

	public void getAllBlocks(AsyncCallback<ArrayList<Block>> callback);

	public void getAllBookmarkLists(
			AsyncCallback<ArrayList<BookmarkList>> callback);

	public void getAllBookmarks(AsyncCallback<ArrayList<Bookmark>> callback);

	public void getAllDescriptions(
			AsyncCallback<ArrayList<Description>> callback);

	public void getAllInformation(AsyncCallback<ArrayList<Information>> callback);

	public void getAllProfiles(AsyncCallback<ArrayList<Profile>> callback);

	public void getAllProperties(AsyncCallback<ArrayList<Property>> callback);

	public void getAllSearchProfiles(
			AsyncCallback<ArrayList<SearchProfile>> callback);

	public void getAllSelectionItems(
			AsyncCallback<ArrayList<SelectionItem>> callback);

	public void getAllSelections(AsyncCallback<ArrayList<Selection>> callback);

	public void getAllSimilarityDegrees(
			AsyncCallback<ArrayList<SimilarityDegree>> callback);

	public void getAllVisits(AsyncCallback<ArrayList<Visit>> callback);

	public void validateDate(int year, int month, int day,
			AsyncCallback<Boolean> callback);
}
