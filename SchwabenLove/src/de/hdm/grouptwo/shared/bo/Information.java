package de.hdm.grouptwo.shared.bo;

/**
 * <code>Information</code> objects refer to a certain
 * <code>Property<code> object and can be used to refine a
 * <code>Profile<code> or be added as a further criterium to a <code>SearchProfile<code>.
 * 
 * @author ManuelRuss
 */

public class Information extends BusinessObject {

	private static final long serialVersionUID = 1L;

	// inputText can be null if the user didn't input anything
	private String inputText;
	private int profileId;
	private int propertyId;
	// searchProfileId is null when refering to a profile information object
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

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getSearchProfileId() {
		return searchProfileId;
	}

	public void setSearchProfileId(int searchProfileId) {
		this.searchProfileId = searchProfileId;
	}
}
