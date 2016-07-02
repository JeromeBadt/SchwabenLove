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

	/**
	 * Return the input text.
	 * @return
	 */
	public String getInputText() {
		return inputText;
	}

	/**
	 * Set the input text.
	 * @param inputText
	 */
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}

	/**
	 * Return the profile id.
	 * @return
	 */
	public int getProfileId() {
		return profileId;
	}
	
	/**
	 * Set the profile id.
	 * @param profileId
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	/**
	 * Return the property id.
	 * @return
	 */
	public int getPropertyId() {
		return propertyId;
	}

	/**
	 * Set the property id.
	 * @param propertyId
	 */
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * Return the id of the searchprofile.
	 * @return
	 */
	public int getSearchProfileId() {
		return searchProfileId;
	}

	/**
	 * Set the id of the searchprofile.
	 * @param searchProfileId
	 */
	public void setSearchProfileId(int searchProfileId) {
		this.searchProfileId = searchProfileId;
	}
}
