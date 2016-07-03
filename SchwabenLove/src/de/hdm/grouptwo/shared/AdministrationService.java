package de.hdm.grouptwo.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

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
 * Synchronous interface for a RPC-enabled class to manage dating sites.
 * 
 * @author jeromebadt
 *
 */
@RemoteServiceRelativePath("administration")
public interface AdministrationService extends RemoteService {

	/**
	 * Create a new profile.
	 */
	public void createProfile(Profile profile);

	/**
	 * This method returns a users profile.
	 */
	public Profile getProfile();

	/**
	 * Sets an email adress to a profile.
	 */
	public Profile setProfile(String email);

	/**
	 * Update the users profile.
	 * 
	 * @param profile
	 *            The <code>Profile</code> to update to
	 * @return The updated profile (needs to be read from the database again to
	 *         recalculate the age
	 */
	public Profile updateProfile(Profile profile);

	/**
	 * Delete the users profile.
	 */
	public void deleteProfile();

	public void updateInformation(Information information);

	/**
	 * Adds a bookmark to another user based on the profile id.
	 */
	public void addBookmarkByProfileId(int profileId);

	/**
	 * Adds a block the profile of another user based on the profile id.
	 */
	public void addBlockByProfileId(int profileId);

	/**
	 * Adds a visit to a profile of another user based on the profile id.
	 */
	public void addVisitByProfileId(int profileId);

	/**
	 * Return the profile id.
	 */
	public Profile getProfileById(int id);

	/**
	 * Return the information of the profile.
	 */
	public ArrayList<Information> getInformationByProfileId(int profileId);

	/**
	 * Return the similarity degree of the profile.
	 */
	public SimilarityDegree getSimilarityDegreeByProfileId(int profileId);

	/**
	 * Return the existing searchprofiles.
	 */
	public ArrayList<SearchProfile> getSearchProfiles();

	/**
	 * Create a new searchprofile.
	 */
	public SearchProfile createSearchProfile(SearchProfile searchProfile);

	/**
	 * Update the attributes of an existing searchprofile of the current user.
	 */
	public void updateSearchProfile(SearchProfile searchProfile);

	/**
	 * Delete a existing searchprofile of the current user.
	 */
	public void deleteSearchProfile(SearchProfile searchProfile);

	/**
	 * Return the matches by the selected searchprofile, depending on the
	 * selected attributes.
	 */
	public ArrayList<Profile> getMatchesBySearchProfile(
			SearchProfile searchProfile);

	/**
	 * Return the bookmarked profiles of the current user.
	 */
	public ArrayList<Profile> getBookmarkedProfiles();

	/**
	 * Check if the profile is bookmarked already.
	 */
	public boolean checkBookmarked(int profileId);

	/**
	 * Check if the profile is blocked already.
	 */
	public boolean checkBlocked(int profileId);

	/**
	 * Returns the blocked profiles of the current user.
	 */
	public ArrayList<Profile> getBlockedProfiles();

	/**
	 * Delete an existing bookmark of the current user.
	 */
	public void deleteBookmark(int profileId);

	/**
	 * Delete an existing block of the current user.
	 */
	public void deleteBlock(int profileId);

	public ArrayList<String> loadTableNames();

	/**
	 * Show all existing blocks.
	 */
	public ArrayList<Block> getAllBlocks();

	/**
	 * Return all bookmarklists.
	 */
	public ArrayList<BookmarkList> getAllBookmarkLists();

	/**
	 * Return all bookmarks.
	 */
	public ArrayList<Bookmark> getAllBookmarks();

	/**
	 * Return all descriptions.
	 */
	public ArrayList<Description> getAllDescriptions();

	/**
	 * Return all information.
	 */
	public ArrayList<Information> getAllInformation();

	/**
	 * Return all profiles.
	 */
	public ArrayList<Profile> getAllProfiles();

	/**
	 * Return all properties.
	 */
	public ArrayList<Property> getAllProperties();

	/**
	 * Return all searchprofiles.
	 */
	public ArrayList<SearchProfile> getAllSearchProfiles();

	/**
	 * Return all selectionitems.
	 */
	public ArrayList<SelectionItem> getAllSelectionItems();

	/**
	 * Return all selections.
	 */
	public ArrayList<Selection> getAllSelections();

	/**
	 * Return all similaritydegrees.
	 */
	public ArrayList<SimilarityDegree> getAllSimilarityDegrees();

	/**
	 * Return all visits.
	 */
	public ArrayList<Visit> getAllVisits();

	/**
	 * Return all selection items from a specific selection.
	 */
	public ArrayList<SelectionItem> getSelectionItems(int selectionId);

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
	public boolean validateDate(int year, int month, int day);

	ArrayList<Profile> getUnvisitedProfiles(Profile profile);
}
