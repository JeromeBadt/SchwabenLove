package de.hdm.grouptwo.shared.bo;

/**
 * A <code>Bookmark</code> object can be set in a <code>BookmarkList</code> to
 * mark a <code>Profile</code> for later use.
 * 
 * @author JoshuaHill, Jerome Badt
 *
 */

public class Bookmark extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private int bookmarkListId;
	private int profileId;

	/**
	 * Return the id of the bookmarklist of an user.
	 * @return
	 */
	public int getBookmarkListId() {
		return bookmarkListId;
	}

	/**
	 * Set the id of a bookmarklist.
	 * @param bookmarkListId
	 */
	public void setBookmarkListId(int bookmarkListId) {
		this.bookmarkListId = bookmarkListId;
	}

	/**
	 * Return the id of a profile.
	 * @return
	 */
	public int getProfileId() {
		return profileId;
	}

	/**
	 * Set the id of a profile.
	 * @param profileId
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
}
