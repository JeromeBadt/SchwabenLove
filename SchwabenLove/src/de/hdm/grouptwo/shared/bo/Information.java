package de.hdm.grouptwo.shared.bo;
/** 
 * 
 * @author manuelruss
 *
 */
public class Information extends BusinessObject {

	
	private static final long serialVersionUID = 1L;
	private String inputText;
	private int profileId;
	private int propertyId;
	private int searchProfileId;
	
	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int profileId) {
		this.profileId = profileId;
	}

	public int getSearchProfileId() {
		return searchProfileId;
	}

	public void setSearchProfileId(int searchProfileId) {
		this.searchProfileId = searchProfileId;
	}

}
