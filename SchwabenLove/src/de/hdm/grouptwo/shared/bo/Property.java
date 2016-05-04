package de.hdm.grouptwo.shared.bo;

public class Property extends BusinessObject {

	/**
	 * Superclass of description and selection
	 * @author DenisThierry 
	 */
	private static final long serialVersionUID = 1L;
	private int property_id;
	private String explanation;

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public int getProperty_id() {
		return property_id;
	}

	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	} 
	

}

