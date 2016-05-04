package de.hdm.grouptwo.shared.bo;

public class SelectionItem extends BusinessObject {

	/**
	 * Name is the text of a selectionItem, 
	 * SelectionItem is a Item which is predefined by the system
	 * 
	 * @author DenisThierry 
	 */
	private static final long serialVersionUID = 1L;

	private int selection_item_id;
	private String name;
	private int fk_selection;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSelection_item_id() {
		return selection_item_id;
	}

	public void setSelection_item_id(int selection_item_id) {
		this.selection_item_id = selection_item_id;
	}

	public int getFk_selection() {
		return fk_selection;
	}

	public void setFk_selection(int fk_selection) {
		this.fk_selection = fk_selection;
	}
}
