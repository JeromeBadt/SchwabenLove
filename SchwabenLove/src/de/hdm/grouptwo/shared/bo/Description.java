package de.hdm.grouptwo.shared.bo;

public class Description extends Property {

	/**
	 * Description is a free text written by the users
	 * @author DenisThierry 
	 */
	private static final long serialVersionUID = 1L;

	private int property_id;

	public int getProperty_id() {
		return property_id;
	}

	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}
}
