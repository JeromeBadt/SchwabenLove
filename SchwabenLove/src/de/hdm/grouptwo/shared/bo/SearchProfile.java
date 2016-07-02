package de.hdm.grouptwo.shared.bo;

/**
 * A <code>SearchProfile</code> object represents a certain set of search
 * criteria attributes used to filter matches. It can also refer to
 * <code>Information</code> objects to further influence the search results. <br>
 * All non-inherited attributes of SearchProfile can be <code>null</code> to
 * indicate no search preference.
 * 
 * @author DenisThierry, JeromeBadt
 */

public class SearchProfile extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private String name;
	private String gender;
	private int minAge;
	private int maxAge;
	private String hairColor;
	private String physique;
	private int minHeight;
	private int maxHeight;
	private String smoker;
	private String education;
	private String profession;
	private String religion;

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
	 * Get the gender.
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Set a gender.
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Get the minimum age.
	 * @return
	 */
	public int getMinAge() {
		return minAge;
	}

	/**
	 * Set a minimum age.
	 * @param minAge
	 */
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	/**
	 * Get the maximum age.
	 * @return
	 */
	public int getMaxAge() {
		return maxAge;
	}

	/**
	 * Set the maximum age.
	 * @param maxAge
	 */
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	/**
	 * Get the haircolor.
	 * @return
	 */
	public String getHairColor() {
		return hairColor;
	}

	/**
	 * Set a haircolor.
	 * @param hairColor
	 */
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	/**
	 * Get the physique.
	 * @return
	 */
	public String getPhysique() {
		return physique;
	}

	/**
	 * Set a physique.
	 * @param physique
	 */
	public void setPhysique(String physique) {
		this.physique = physique;
	}

	/**
	 * Get the minimum height.
	 * @return
	 */
	public int getMinHeight() {
		return minHeight;
	}

	/**
	 * Set a minimum height.
	 * @param minHeight
	 */
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	/**
	 * Get the maximum height.
	 * @return
	 */
	public int getMaxHeight() {
		return maxHeight;
	}

	/**
	 * Set the maximum height.
	 * @param maxHeight
	 */
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	/**
	 * Get the smoker attribute.
	 * @return
	 */
	public String getSmoker() {
		return smoker;
	}
	
	/**
	 * Set a smoker attribute.
	 * @param smoker
	 */
	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	/**
	 * Get the education. 
	 * @return
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * Set an education.
	 * @param education
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * Get the profession.
	 * @return
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * Set a profession.
	 * @param profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * Get the religion.
	 * @return
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * Set a religion.
	 * @param religion
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
}
