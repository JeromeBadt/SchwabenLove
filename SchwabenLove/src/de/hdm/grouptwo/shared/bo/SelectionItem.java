package de.hdm.grouptwo.shared.bo;

public class SelectionItem extends BusinessObject {

	/**
	 * SelectionItem is a Item which is predefined by the system
	 * @author DenisThierry 
	 */
	private static final long serialVersionUID = 1L;

	private String selectionText;

	public String getSelectionText() {
		return selectionText;
	}

	public void setSelectionText(String selectionText) {
		this.selectionText = selectionText;
	}
}
