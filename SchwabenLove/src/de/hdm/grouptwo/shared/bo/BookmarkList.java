package de.hdm.grouptwo.shared.bo;

import java.util.ArrayList;

public class BookmarkList extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private ArrayList<Bookmark> bookmarks;

	public ArrayList<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(ArrayList<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}
}
