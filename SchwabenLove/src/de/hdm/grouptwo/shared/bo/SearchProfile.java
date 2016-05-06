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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getPhysique() {
		return physique;
	}

	public void setPhysique(String physique) {
		this.physique = physique;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getSmoker() {
		return smoker;
	}

	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
}
