package de.hdm.grouptwo.shared.bo;

import java.util.ArrayList;


/**
 * A bookmarklist is used to store all bookmarks of a user
 * 
 * @author joshuahill
 *
 */
public class BookmarkList extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private ArrayList<Bookmark> bookmarks;
	private int profileId;

	public ArrayList<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(ArrayList<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
}
