package de.hdm.grouptwo.shared.bo;

import java.util.ArrayList;

public class Selection extends Property {

	/**
	 * Selection reaches for previously defined selectionItems
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

	private ArrayList<SelectionItem> selectionItems;

	public ArrayList<SelectionItem> getSelectionItems() {
		return selectionItems;
	}

	public void setSelectionItems(ArrayList<SelectionItem> selectionItems) {
		this.selectionItems = selectionItems;
	}
}
