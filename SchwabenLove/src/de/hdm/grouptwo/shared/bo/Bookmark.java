package de.hdm.grouptwo.shared.bo;

public class Bookmark extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private BookmarkList bookmarkList;

	public BookmarkList getBookmarkList() {
		return bookmarkList;
	}

	public void setBookmarkList(BookmarkList bookmarkList) {
		this.bookmarkList = bookmarkList;
	}
}
