package de.hdm.grouptwo.shared.bo;

import java.util.ArrayList;

public class Selection extends Property {

	/**
	 * Selection reaches for previously defined selectionItems
	 * @author DenisThierry 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<SelectionItem> selectionItems;

	public ArrayList<SelectionItem> getSelectionItems() {
		return selectionItems;
	}

	public void setSelectionItems(ArrayList<SelectionItem> selectionItems) {
		this.selectionItems = selectionItems;
	}
}