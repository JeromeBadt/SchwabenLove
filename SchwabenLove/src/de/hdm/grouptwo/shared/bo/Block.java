package de.hdm.grouptwo.shared.bo;

/**
 * A Block object represents the state between two profiles which are restricted
 * from seeing each other.
 * 
 * @author ManuelRuss, JeromeBadt
 */

public class Block extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private int blockerProfileId;
	private int blockedProfileId;

	public int getBlockerProfileId() {
		return blockerProfileId;
	}

	public void setBlockerProfileId(int blockerId) {
		this.blockerProfileId = blockerId;
	}

	public int getBlockedProfileId() {
		return blockedProfileId;
	}

	public void setBlockedProfileId(int blockedId) {
		this.blockedProfileId = blockedId;
	}

}
