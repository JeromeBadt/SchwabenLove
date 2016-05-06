package de.hdm.grouptwo.shared.bo;

/**
 * A <code>BookmarkList</code> object belongs to a <code>Profile</code> and is
 * used to store <code>Bookmark</code> objects.
 * 
 * @author JoshuaHill, Jerome Badt
 */

public class BookmarkList extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private int profileId;

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
}
