package de.hdm.grouptwo.shared.bo;

/**
 * A bookmark can be used by user to mark another profile for later use
 * 
 * @author joshuahill
 *
 */
public class Bookmark extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private int bookmarkList_Id;
	private int profile_Id;
	
	
	public int getBookmarkList_Id() {
		return bookmarkList_Id;
	}
	public void setBookmarkList_Id(int bookmarkList_Id) {
		this.bookmarkList_Id = bookmarkList_Id;
	}
	public int getProfile_Id() {
		return profile_Id;
	}
	public void setProfile_Id(int profile_Id) {
		this.profile_Id = profile_Id;
	}


}
