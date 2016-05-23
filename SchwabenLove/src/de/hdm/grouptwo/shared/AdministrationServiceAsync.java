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
	public void getMatchesByProfileId(int profileId,
			AsyncCallback<ArrayList<Profile>> callback);

	public void insertDemoProfile(AsyncCallback<String> callback);

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
}