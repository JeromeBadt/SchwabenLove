package de.hdm.grouptwo.shared.bo;

/**
 * <code>SelectionItem</code> represents an item of a selection and is
 * predefined by the system.
 * 
 * @author DenisThierry, JeromeBadt
 */

public class SelectionItem extends BusinessObject {

	private static final long serialVersionUID = 1L;

	// The text of a selection item
	private String name;
	// The selection which this item belongs to
	private int selectionId;

	/**
	 * Get the name.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set a name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the selection id.
	 * @return
	 */
	public int getSelectionId() {
		return selectionId;
	}

	/**
	 * Set a selection id.
	 * @param selectionId
	 */
	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}
}
