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
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private ProfileMapper() {
	}

	/**
	 * ProfileMapper should be instantiated by this method to ensure that only a
	 * single instance exists.
	 * 
	 * @return The <code>ProfileMapper</code> instance
	 */
	public static ProfileMapper profileMapper() {
		if (profileMapper == null) {
			profileMapper = new ProfileMapper();
		}
		return profileMapper;
	}

	/**
	 * Insert a <code>Profile</code> object into the DB.
	 * 
	 * @param p
	 *            The <code>Profile</code> object to be inserted
	 * @return The inserted Profile (returned because it gets an assigned id)
	 */
	@Override
	public Profile insert(Profile p) {
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
						+ ",'"
						+ p.getEmail()
						+ "','"
						+ p.getFirstName()
						+ "','"
						+ p.getLastName()
						+ "','"
						+ p.getGender()
						+ "','"
						+ p.getBirthdate()
						+ "','"
						+ p.getLocation()
						+ "',"
						+ p.getHeight()
						+ ",'"
						+ p.getPhysique()
						+ "','"
						+ p.getHairColor()
						+ "','"
						+ p.getSmoker()
						+ "','"
						+ p.getEducation()
						+ "','"
						+ p.getProfession() + "','" + p.getReligion() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * Update a <code>Profile</code> object in the DB.
	 * 
	 * @param p
	 *            The <code>Profile</code> object to be updated
	 */
	@Override
	public void update(Profile p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE profile SET email='" + p.getEmail()
					+ "',first_name='" + p.getFirstName() + "',last_name='"
					+ p.getLastName() + "',gender='" + p.getGender()
					+ "',birthdate='" + p.getBirthdate() + "',location='"
					+ p.getLocation() + "', height=" + p.getHeight()
					+ ",physique='" + p.getPhysique() + "',hair_color='"
					+ p.getHairColor() + "',smoker='" + p.getSmoker()
					+ "',education='" + p.getEducation() + "',profession='"
					+ p.getProfession() + "',religion='" + p.getReligion()
					+ "' WHERE profile_id=" + p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>Profile</code> object from the DB.
	 * 
	 * @param p
	 *            The <code>Profile</code> object to be deleted
	 */
	@Override
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
	 * Find all <code>Profile</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>Profile</code> objects
	 */
	@Override
	public ArrayList<Profile> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT profile_id, email, first_name, "
							+ "last_name, gender, birthdate, TIMESTAMPDIFF"
							+ "(YEAR, birthdate, CURDATE()) AS age, location, "
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
				p.setAge(rs.getInt("age"));
				p.setLocation(rs.getString("location"));
				p.setHeight(rs.getInt("height"));
				p.setPhysique(rs.getString("physique"));
				p.setHairColor(rs.getString("hair_color"));
				p.setSmoker(rs.getBoolean("smoker"));
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
	 * @return <code>Profile</code> object with specified ID or null if not
	 *         found
	 */
	@Override
	public Profile findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT profile_id, email, first_name, "
							+ "last_name, gender, birthdate, TIMESTAMPDIFF"
							+ "(YEAR, birthdate, CURDATE()) AS age, location, "
							+ "height, physique, hair_color, smoker, "
							+ "education, profession, religion FROM profile "
							+ "WHERE profile_id=" + id);

			if (rs.next()) {
				Profile p = new Profile();
				p.setId(rs.getInt("profile_id"));
				p.setEmail(rs.getString("email"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setGender(rs.getString("gender"));
				p.setBirthdate(rs.getDate("birthdate"));
				p.setAge(rs.getInt("age"));
				p.setLocation(rs.getString("location"));
				p.setHeight(rs.getInt("height"));
				p.setPhysique(rs.getString("physique"));
				p.setHairColor(rs.getString("hair_color"));
				p.setSmoker(rs.getBoolean("smoker"));
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
	 * Find <code>Profile</code> object with a specific email adress in the DB.
	 * 
	 * @return <code>Profile</code> object with specified email adress or null
	 *         if not found
	 */
	public Profile findByEmail(String email) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT profile_id, email, first_name, "
							+ "last_name, gender, birthdate, TIMESTAMPDIFF"
							+ "(YEAR, birthdate, CURDATE()) AS age, location, "
							+ "height, physique, hair_color, smoker, "
							+ "education, profession, religion FROM profile "
							+ "WHERE email = " + email);

			if (rs.next()) {
				Profile p = new Profile();
				p.setId(rs.getInt("profile_id"));
				p.setEmail(rs.getString("email"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setGender(rs.getString("gender"));
				p.setBirthdate(rs.getDate("birthdate"));
				p.setAge(rs.getInt("age"));
				p.setLocation(rs.getString("location"));
				p.setHeight(rs.getInt("height"));
				p.setPhysique(rs.getString("physique"));
				p.setHairColor(rs.getString("hair_color"));
				p.setSmoker(rs.getBoolean("smoker"));
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
	 * Find a <code>Profile</code> object by a specific visitId in the DB.
	 * 
	 * @return The found <code>Profile</code> object
	 */
	public Profile findByVisitId(int visitId) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT profile_id, email, first_name, "
							+ "last_name, gender, birthdate, TIMESTAMPDIFF"
							+ "(YEAR, birthdate, CURDATE()) AS age, location, "
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
				p.setAge(rs.getInt("age"));
				p.setLocation(rs.getString("location"));
				p.setHeight(rs.getInt("height"));
				p.setPhysique(rs.getString("physique"));
				p.setHairColor(rs.getString("hair_color"));
				p.setSmoker(rs.getBoolean("smoker"));
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
