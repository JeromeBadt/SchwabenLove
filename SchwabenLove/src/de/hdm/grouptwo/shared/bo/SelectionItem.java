package de.hdm.grouptwo.shared.bo;

public class SelectionItem extends BusinessObject {

	/**
	 * Name is the text of a selectionItem, 
	 * SelectionItem is a Item which is predefined by the system
	 * 
	 * @author DenisThierry 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private int property_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProperty_id() {
		return property_id;
	}

	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}
}
