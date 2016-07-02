package de.hdm.grouptwo.shared.bo;

/**
 * A <code>Visit</code> object is created when one profile (the visitor) visits
 * another profile (the visited).
 * 
 * @author ManuelRuss, JeromeBadt
 */

public class Visit extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private int visitorProfileId;
	private int visitedProfileId;

	/**
	 * Get the profile id of the visitor.
	 * @return
	 */
	public int getVisitorProfileId() {
		return visitorProfileId;
	}

	/**
	 * Set the profile id of the visitor.
	 * @param visitorProfileId
	 */
	public void setVisitorProfileId(int visitorProfileId) {
		this.visitorProfileId = visitorProfileId;
	}

	/**
	 * Get the profile id of the visited party. 
	 * @return
	 */
	public int getVisitedProfileId() {
		return visitedProfileId;
	}

	/**
	 * Set the profile id of the visited party.
	 * @param visitedProfileId
	 */
	public void setVisitedProfileId(int visitedProfileId) {
		this.visitedProfileId = visitedProfileId;
	}

}
