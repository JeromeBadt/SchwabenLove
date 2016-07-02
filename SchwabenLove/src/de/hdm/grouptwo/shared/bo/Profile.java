package de.hdm.grouptwo.shared.bo;

import java.sql.Date;

/**
 * <code>Profile</code> objects are the representation of users and the
 * centerpieces of the SchwabenLove platform. Profiles have a set of attributes
 * but can be further refined by <code>Information</code> objects.
 * 
 * @author ManuelRuss, DucNguyen, JeromeBadt
 */

public class Profile extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private Date birthdate;
	private int age;
	private String location;
	private int height;
	private String physique;
	private String hairColor;
	private String smoker;
	private String education;
	private String profession;
	private String religion;

	/**
	 * Get email adress.
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email adress.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set the first name.
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the first name.
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * The the last name.
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the last name.
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get the gender.
	 * @return
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Set the gender.
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Get the birthdate.
	 * @return
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * Set the birthdate.
	 * @param birthdate
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Get the age.
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Set the age.
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Get the location.
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Set the location.
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Get the haircolor.
	 * @return
	 */
	public String getHairColor() {
		return hairColor;
	}

	/**
	 * Set the haircolor.
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
	 * Set the physique.
	 * @param physique
	 */
	public void setPhysique(String physique) {
		this.physique = physique;
	}

	/**
	 * Return smoker attribute.
	 * @return
	 */
	public String getSmoker() {
		return smoker;
	}

	/**
	 * Set smoker attribute.
	 * @param smoker
	 */
	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	/**
	 * Get the height.
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height. 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get education. 
	 * @return
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * Set education
	 * @param education
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * Get profession.
	 * @return
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * Set profession.
	 * @param profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * Get Religion.
	 * @return
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * Set Religion.
	 * @param religion
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
}
