package de.hdm.grouptwo.shared.bo;

/**
 * <code>Property</code> is a superclass of <code>Description</code> and
 * <code>Selection</code>. Properties are provided by the system and can be
 * optionally used by a profile to add further information to it.
 * 
 * @author DenisThierry, Jerome Badt
 */

public class Property extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private String explanation;

	/**
	 * Get explanation.
	 * @return
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * Set explanation.
	 * @param explanation
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
