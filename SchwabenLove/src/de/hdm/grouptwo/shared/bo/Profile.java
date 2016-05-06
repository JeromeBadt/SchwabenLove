package de.hdm.grouptwo.shared.bo;

import java.util.Date;

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
	private String location;
	private int height;
	private String pysique;
	private String hairColor;
	private String smoker;
	private String education;
	private String profession;
	private String religion;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getPysique() {
		return pysique;
	}

	public void setPysique(String pysique) {
		this.pysique = pysique;
	}

	public String getSmoker() {
		return smoker;
	}

	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
