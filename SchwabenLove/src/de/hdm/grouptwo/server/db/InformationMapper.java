package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Information;

/**
 * Implementation of a mapper class for Information. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * <p>
 * 
 * TODO: DeleteInformationOf
 * 
 * @author JeromeBadt, Thies
 */

public class InformationMapper implements DataMapper<Information> {
	private static InformationMapper informationMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>
	 */
	private InformationMapper() {
	}

	/**
	 * InformationMapper should be instantiated by this method to ensure that
	 * only a single instance exists.
	 * 
	 * @return The <code>InformationMapper</code> instance.
	 */
	public static InformationMapper informationMapper() {
		if (informationMapper == null) {
			informationMapper = new InformationMapper();
		}

		return informationMapper;
	}

	/**
	 * Insert a <code>Information</code> object into the DB
	 * 
	 * <p>
	 * TODO: else block for inserting first object into DB?
	 * 
	 * @param i
	 *            The <code>Information</code> object to be inserted
	 */
	public void insert(Information i) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(information_id) AS maxid FROM information");

			if (rs.next()) {
				// Set id to max + 1
				i.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO information "
						+ "(information_id, input_text, fk_profile_id, "
						+ "fk_property_id, fk_search_profile_id) VALUES ("
						+ i.getId() + ",'" + i.getInputText() + "',"
						+ i.getProfileId() + "," + i.getPropertyId() + ","
						+ i.getSearchProfileId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update a <code>Information</code> object in the DB
	 * 
	 * @param i
	 *            The <code>Information</code> object to be updated
	 */
	public void update(Information i) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE information SET input_text='"
					+ i.getInputText() + "',fk_profile_id=" + i.getProfileId()
					+ ",fk_property_id=" + i.getPropertyId()
					+ ",fk_search_profile_id=" + i.getSearchProfileId()
					+ " WHERE information_id=" + i.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>Information</code> object from the DB
	 * 
	 * @param i
	 *            The <code>Information</code> object to be deleted
	 */
	public void delete(Information i) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM information WHERE information_id="
					+ i.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Information</code> objects in the DB
	 * 
	 * @return result ArrayList of all <code>Information</code> objects
	 */
	public ArrayList<Information> findAll() {
		Connection con = DBConnection.connection();
		ArrayList<Information> result = new ArrayList<Information>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT information_id, input_text, "
							+ "fk_profile_id, fk_property_id, "
							+ "fk_search_profile_id FROM information");

			while (rs.next()) {
				Information i = new Information();
				i.setId(rs.getInt("information_id"));
				i.setInputText(rs.getString("input_text"));
				i.setProfileId(rs.getInt("fk_profile_id"));
				i.setPropertyId(rs.getInt("fk_property_id"));
				i.setSearchProfileId(rs.getInt("fk_search_profile_id"));

				result.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>Information</code> object with a specific ID in the DB.
	 * 
	 * @return result <code>Information</code> object with specified ID or null
	 *         if not found
	 */
	public Information findById(int id) {
		return findBy("information_id", id).get(0);
	}

	/**
	 * Find all <code>Information</code> objects which belong to a specific
	 * profile in the DB
	 * 
	 * @return result ArrayList of found <code>Information</code> objects
	 */
	public ArrayList<Information> findByProfileId(int profileId) {
		return findBy("fk_profile_id", profileId);
	}

	/**
	 * Find all <code>Information</code> objects which refer to a specific
	 * property in the DB
	 * 
	 * @return result ArrayList of found <code>Information</code> objects
	 */
	public ArrayList<Information> findByPropertyId(int propertyId) {
		return findBy("fk_property_id", propertyId);
	}

	/**
	 * Find all <code>Information</code> objects which belong to a specific
	 * search profile in the DB
	 * 
	 * @return result ArrayList of found <code>Information</code> objects
	 */
	public ArrayList<Information> findBySearchProfileId(int searchProfileId) {
		return findBy("fk_search_profile_id", searchProfileId);
	}

	/**
	 * Helper method to find all <code>Information</code> objects via a specific
	 * key and value
	 * 
	 * @return result ArrayList of found <code>Information</code> objects
	 */
	private ArrayList<Information> findBy(String key, int value) {
		Connection con = DBConnection.connection();
		ArrayList<Information> result = new ArrayList<Information>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT information_id, input_text, "
							+ "fk_profile_id, fk_property_id, fk_search_profile_id "
							+ "FROM information WHERE " + key + "=" + value);

			while (rs.next()) {
				Information i = new Information();
				i.setId(rs.getInt("information_id"));
				i.setInputText(rs.getString("input_text"));
				i.setProfileId(rs.getInt("fk_profile_id"));
				i.setPropertyId(rs.getInt("fk_property_id"));
				i.setSearchProfileId(rs.getInt("fk_search_profile_id"));

				result.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
