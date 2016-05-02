package de.hdm.grouptwo.shared.bo;

/**
 * 
 * @author manuelruss
 *
 */

public class Block extends BusinessObject {

	
	private static final long serialVersionUID = 1L;
	
	private Profile owner;
	private Profile profile;
	
	
	public Profile getOwner() {
		return owner;
	}
	public void setOwner(Profile owner) {
		this.owner = owner;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
