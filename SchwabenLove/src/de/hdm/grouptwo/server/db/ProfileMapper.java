package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Profile;

/**
 * Implementation of a mapper class for Profile. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * <p>
 * 
 * TODO: findBySearchProfile
 * 
 * @author Thies, DucNguyen, JeromeBadt
 */

public class ProfileMapper implements DataMapper<Profile> {
	private static ProfileMapper profileMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>
	 */
	private ProfileMapper() {
	}

	/**
	 * ProfileMapper should be instantiated by this method to ensure that only a
	 * single instance exists.
	 * 
	 * @return The <code>ProfileMapper</code> instance.
	 */
	public static ProfileMapper profileMapper() {
		if (profileMapper == null) {
			profileMapper = new ProfileMapper();
		}
		return profileMapper;
	}

	/**
	 * Insert a <code>Profile</code> object into the DB
	 * 
	 * <p>
	 * TODO: else block for inserting first object into DB?
	 * 
	 * @param p
	 *            The <code>Profile</code> object to be inserted
	 */
	public void insert(Profile p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt.executeQuery("Select MAX(profile_id) AS maxid "
					+ "FROM profile ");

			if (rs.next()) {
				// Set id to max + 1
				p.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO profile (profile_id, email, "
						+ "first_name, last_name, gender, birthdate, "
						+ "location, height, physique, hair_color, smoker, "
						+ "education, profession, religion) VALUES ("
						+ p.getId()
						+ ","
						+ p.getEmail()
						+ ",'"
						+ p.getFirstName()
						+ "','"
						+ p.getLastName()
						+ "',"
						+ p.getGender()
						+ ","
						+ p.getBirthdate()
						+ ",'"
						+ p.getLocation()
						+ "',"
						+ p.getHeight()
						+ ","
						+ p.getPysique()
						+ ","
						+ p.getHairColor()
						+ ",'"
						+ p.getSmoker()
						+ "','"
						+ p.getEducation()
						+ "','"
						+ p.getProfession() + "','" + p.getReligion() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update a <code>Profile</code> object in the DB
	 * 
	 * @param p
	 *            The <code>Profile</code> object to be updated
	 */
	public void update(Profile p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE profile SET email=" + p.getEmail()
					+ ",first_name='" + p.getFirstName() + "',last_name='"
					+ p.getLastName() + "',gender=" + p.getGender()
					+ ",birthdate=" + p.getBirthdate() + ",location='"
					+ p.getLocation() + "', height=" + p.getHeight()
					+ ",physique=" + p.getPysique() + ",hair_color="
					+ p.getHairColor() + ",smoker='" + p.getSmoker()
					+ "',education='" + p.getEducation() + "',profession='"
					+ p.getProfession() + "',religion='" + p.getReligion()
					+ "' WHERE profile_id=" + p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>Profile</code> object from the DB
	 * 
	 * @param p
	 *            The <code>Profile</code> object to be deleted
	 */
	public void delete(Profile p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM profile " + "WHERE profile_id="
					+ p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Profile</code> objects in the DB
	 * 
	 * @return result ArrayList of all <code>Profile</code> objects
	 */
	public ArrayList<Profile> findAll() {
		Connection con = DBConnection.connection();
		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT profile_id, email, first_name, "
							+ "last_name, gender, birthdate, location, "
							+ "height, physique, hair_color, smoker, "
							+ "education, profession, religion FROM profile");

			while (rs.next()) {
				Profile p = new Profile();
				p.setId(rs.getInt("profile_id"));
				p.setEmail(rs.getString("email"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setGender(rs.getString("gender"));
				p.setBirthdate(rs.getDate("birthdate"));
				p.setLocation(rs.getString("location"));
				p.setHeight(rs.getInt("height"));
				p.setPysique(rs.getString("physique"));
				p.setHairColor(rs.getString("hair_color"));
				p.setSmoker(rs.getString("smoker"));
				p.setEducation(rs.getString("education"));
				p.setProfession(rs.getString("profession"));
				p.setReligion(rs.getString("religion"));

				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>Profile</code> object with a specific ID in the DB.
	 * 
	 * @return result <code>Profile</code> object with specified ID or null if
	 *         not found
	 */
	public Profile findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT profile_id, email, first_name, "
							+ "last_name, gender, birthdate, location, "
							+ "height, physique, hair_color, smoker, "
							+ "education, profession, religion FROM profile");

			if (rs.next()) {
				Profile p = new Profile();
				p.setId(rs.getInt("profile_id"));
				p.setEmail(rs.getString("email"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setGender(rs.getString("gender"));
				p.setBirthdate(rs.getDate("birthdate"));
				p.setLocation(rs.getString("location"));
				p.setHeight(rs.getInt("height"));
				p.setPysique(rs.getString("physique"));
				p.setHairColor(rs.getString("hair_color"));
				p.setSmoker(rs.getString("smoker"));
				p.setEducation(rs.getString("education"));
				p.setProfession(rs.getString("profession"));
				p.setReligion(rs.getString("religion"));

				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Find a <code>Profile</code> object by a specific visitId in the DB
	 * 
	 * @return result The <code>Profile</code> object
	 */
	public Profile findByVisitId(int visitId) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT profile_id, email, first_name, "
							+ "last_name, gender, birthdate, location, "
							+ "height, physique, hair_color, smoker, "
							+ "education, profession, religion FROM profile "
							+ "JOIN visit ON fk_profile_visitor=profile_id "
							+ "WHERE visit_id=" + visitId);

			while (rs.next()) {
				Profile p = new Profile();
				p.setId(rs.getInt("profile_id"));
				p.setEmail(rs.getString("email"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setGender(rs.getString("gender"));
				p.setBirthdate(rs.getDate("birthdate"));
				p.setLocation(rs.getString("location"));
				p.setHeight(rs.getInt("height"));
				p.setPysique(rs.getString("physique"));
				p.setHairColor(rs.getString("hair_color"));
				p.setSmoker(rs.getString("smoker"));
				p.setEducation(rs.getString("education"));
				p.setProfession(rs.getString("profession"));
				p.setReligion(rs.getString("religion"));

				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
