package de.hdm.grouptwo.shared.bo;

import java.util.ArrayList;


/**
 * A bookmarklist is used to store all bookmars of a user
 * 
 * @author joshuahill
 *
 */
public class BookmarkList extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private ArrayList<Bookmark> bookmarks;
	private Profile owner;

	public ArrayList<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(ArrayList<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}
}
