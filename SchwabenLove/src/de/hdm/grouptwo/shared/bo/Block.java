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

	/**
	 * Return the profile id of the blocking user.
	 * @return
	 */
	public int getBlockerProfileId() {
		return blockerProfileId;
	}

	/**
	 * Set the profile id of the blocking party.
	 * @param blockerId
	 */
	public void setBlockerProfileId(int blockerId) {
		this.blockerProfileId = blockerId;
	}

	/**
	 * Return the profile of the blocked user.
	 * @return
	 */
	public int getBlockedProfileId() {
		return blockedProfileId;
	}

	/**
	 * Set the profile id of the blocked user.
	 * @param blockedId
	 */
	public void setBlockedProfileId(int blockedId) {
		this.blockedProfileId = blockedId;
	}

}
