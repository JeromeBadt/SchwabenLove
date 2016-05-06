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

	public int getBookmarkListId() {
		return bookmarkListId;
	}

	public void setBookmarkListId(int bookmarkListId) {
		this.bookmarkListId = bookmarkListId;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
}
