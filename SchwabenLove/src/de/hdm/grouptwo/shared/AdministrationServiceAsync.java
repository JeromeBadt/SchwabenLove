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

/**
 * Asynchronous interface for a RPC-enabled class to manage dating sites.
 * 
 * @author jeromebadt
 *
 */
public interface AdministrationServiceAsync {

	/**
	 * Create a new profile.
	 */
	public void createProfile(Profile profile, AsyncCallback<Void> callback);

	/**
	 * This method returns a users profile.
	 */
	public void getProfile(AsyncCallback<Profile> callback);

	/**
	 * Sets an email adress to a profile.
	 */
	public void setProfile(String email, AsyncCallback<Profile> callback);

	/**
	 * Update the users profile.
	 * 
	 * @param profile
	 *            The <code>Profile</code> to update to
	 * @return The updated profile (needs to be read from the database again to
	 *         recalculate the age
	 */
	public void updateProfile(Profile profile, AsyncCallback<Profile> callback);

	/**
	 * Delete the users profile.
	 */
	public void deleteProfile(AsyncCallback<Void> callback);

	public void updateInformation(Information information,
			AsyncCallback<Void> callback);

	/**
	 * Add a bookmark based on the profile id.
	 */
	public void addBookmarkByProfileId(int profileId,
			AsyncCallback<Void> callback);

	/**
	 * Add a block to a profile of another user, based on the profile id.
	 */
	public void addBlockByProfileId(int profileId, AsyncCallback<Void> callback);

	/**
	 * Return the profile id.
	 */
	public void getProfileById(int id, AsyncCallback<Profile> callback);

	/**
	 * Return the information of the profile.
	 */
	public void getInformationByProfileId(int profileId,
			AsyncCallback<ArrayList<Information>> callback);

	/**
	 * Return the similarity degree of the profile.
	 */
	public void getSimilarityDegreeByProfileId(int profileId,
			AsyncCallback<SimilarityDegree> callback);

	/**
	 * Return the existing searchprofiles.
	 */
	public void getSearchProfiles(
			AsyncCallback<ArrayList<SearchProfile>> callback);

	/**
	 * Create a new searchprofile.
	 */
	public void createSearchProfile(SearchProfile searchProfile,
			AsyncCallback<SearchProfile> callback);

	/**
	 * Update the attributes of an existing searchprofile of the current user.
	 */
	public void updateSearchProfile(SearchProfile searchProfile,
			AsyncCallback<Void> callback);

	/**
	 * Delete a existing searchprofile of the current user.
	 */
	public void deleteSearchProfile(SearchProfile searchProfile,
			AsyncCallback<Void> callback);

	/**
	 * Return the matches by the selected searchprofile, depending on the
	 * selected attributes.
	 */
	public void getMatchesBySearchProfile(SearchProfile searchProfile,
			AsyncCallback<ArrayList<Profile>> callback);

	/**
	 * Return the bookmarked profiles of the current user.
	 */
	public void getBookmarkedProfiles(AsyncCallback<ArrayList<Profile>> callback);

	/**
	 * Check if the profile is bookmarked already.
	 */
	public void checkBookmarked(int profileId, AsyncCallback<Boolean> callback);

	/**
	 * Returns the blocked profiles of the current user.
	 */
	public void getBlockedProfiles(AsyncCallback<ArrayList<Profile>> callback);

	/**
	 * Delete an existing bookmark of the current user.
	 */
	public void deleteBookmark(int profileId, AsyncCallback<Void> callback);

	/**
	 * Delete an existing block of the current user.
	 */
	public void deleteBlock(int profileId, AsyncCallback<Void> callback);

	public void loadTableNames(AsyncCallback<ArrayList<String>> callback);

	/**
	 * Show all existing blocks.
	 */
	public void getAllBlocks(AsyncCallback<ArrayList<Block>> callback);

	/**
	 * Return all bookmarklists.
	 */
	public void getAllBookmarkLists(
			AsyncCallback<ArrayList<BookmarkList>> callback);

	/**
	 * Return all bookmarks.
	 */
	public void getAllBookmarks(AsyncCallback<ArrayList<Bookmark>> callback);

	/**
	 * Return all descriptions.
	 */
	public void getAllDescriptions(
			AsyncCallback<ArrayList<Description>> callback);

	/**
	 * Return all information.
	 */
	public void getAllInformation(AsyncCallback<ArrayList<Information>> callback);

	/**
	 * Return all profiles.
	 */
	public void getAllProfiles(AsyncCallback<ArrayList<Profile>> callback);

	/**
	 * Return all properties.
	 */
	public void getAllProperties(AsyncCallback<ArrayList<Property>> callback);

	/**
	 * Return all searchprofiles.
	 */
	public void getAllSearchProfiles(
			AsyncCallback<ArrayList<SearchProfile>> callback);

	/**
	 * Return all selectionitems.
	 */
	public void getAllSelectionItems(
			AsyncCallback<ArrayList<SelectionItem>> callback);

	/**
	 * Return all selections.
	 */
	public void getAllSelections(AsyncCallback<ArrayList<Selection>> callback);

	/**
	 * Return all similaritydegrees.
	 */
	public void getAllSimilarityDegrees(
			AsyncCallback<ArrayList<SimilarityDegree>> callback);

	/**
	 * Return all visits.
	 */
	public void getAllVisits(AsyncCallback<ArrayList<Visit>> callback);

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
	public void validateDate(int year, int month, int day,
			AsyncCallback<Boolean> callback);

	void getUnvisitedProfiles(Profile profile,
			AsyncCallback<ArrayList<Profile>> callback);
}
