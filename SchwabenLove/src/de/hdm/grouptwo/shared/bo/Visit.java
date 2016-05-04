package de.hdm.grouptwo.shared.bo;

/**
 * A Visit object is created when one profile (the visitor) visits another
 * profile (the visited).
 * 
 * @author ManuelRuss, JeromeBadt
 */

public class Visit extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private int visitorProfileId;
	private int visitedProfileId;

	public int getVisitorProfileId() {
		return visitorProfileId;
	}

	public void setVisitorProfileId(int visitorProfileId) {
		this.visitorProfileId = visitorProfileId;
	}

	public int getVisitedProfileId() {
		return visitedProfileId;
	}

	public void setVisitedProfileId(int visitedProfileId) {
		this.visitedProfileId = visitedProfileId;
	}

}
