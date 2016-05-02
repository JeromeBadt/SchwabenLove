package de.hdm.grouptwo.shared.bo;

/**
 * A bookmark can be used by user to mark another profile for later use
 * 
 * @author joshuahill
 *
 */
public class Bookmark extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private BookmarkList bookmarkList;
	private Profile profile;

	public BookmarkList getBookmarkList() {
		return bookmarkList;
	}

	public void setBookmarkList(BookmarkList bookmarkList) {
		this.bookmarkList = bookmarkList;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
