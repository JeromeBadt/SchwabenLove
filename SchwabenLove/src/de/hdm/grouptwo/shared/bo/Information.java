package de.hdm.grouptwo.shared.bo;
/** 
 * 
 * @author manuelruss
 *
 */
public class Information extends BusinessObject {

	
	private static final long serialVersionUID = 1L;
	private String inputText;
	private Profile owner;
	private Property property;
	
	

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}
