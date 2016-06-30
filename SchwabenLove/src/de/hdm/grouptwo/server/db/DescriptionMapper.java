package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Description;

/**
 * Implementation of a mapper class for Description. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * 
 * @author Thies, DenisThierry, JoergJarmer
 */

public class DescriptionMapper implements DataMapper<Description> {
	private static DescriptionMapper descriptionMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private DescriptionMapper() {
	}

	/**
	 * DescriptionMapper should be instantiated by this method to ensure that
	 * only a single instance exists.
	 * 
	 * @return The <code>DescriptionMapper</code> instance
	 */
	public static DescriptionMapper descriptionMapper() {
		if (descriptionMapper == null) {
			descriptionMapper = new DescriptionMapper();
		}
		return descriptionMapper;
	}

	/**
	 * Insert a <code>Description</code> object into the DB.
	 * 
	 * @param d
	 *            The <code>Description</code> object to be inserted
	 * @return The inserted Description (returned because it gets an assigned
	 *         id)
	 */
	@Override
	public Description insert(Description d) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(fk_property_id) AS maxId "
							+ "FROM description");

			if (rs.next()) {
				// Set id to max + 1
				d.setId(rs.getInt("maxId") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO description (fk_property_id) "
						+ "VALUES (" + d.getId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return d;
	}

	/**
	 * Update a <code>Description</code> object in the DB. <br>
	 * Empty since Description only has on id and no other attributes to update.
	 * 
	 * @param d
	 *            The <code>Description</code> object to be updated
	 */
	@Override
	public void update(Description d) {
	}

	/**
	 * Delete a <code>Description</code> object from the DB.
	 * 
	 * @param d
	 *            The <code>Description</code> object to be deleted
	 */
	@Override
	public void delete(Description d) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM description WHERE fk_property_id="
					+ d.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Description</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>Description</code> objects
	 */
	@Override
	public ArrayList<Description> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Description> result = new ArrayList<Description>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT fk_property_id "
					+ "FROM description");

			while (rs.next()) {
				Description d = new Description();
				d.setId(rs.getInt("fk_property_id"));

				result.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>Description</code> object with a specific ID in the DB.
	 * 
	 * @param id
	 *            The id by which to find the object
	 * @return <code>Description</code> object with specified ID or null if not
	 *         found
	 */
	@Override
	public Description findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT fk_property_id, "
					+ "FROM description WHERE fk_property_id=" + id);

			if (rs.next()) {
				Description d = new Description();
				d.setId(rs.getInt("fk_property_id"));

				return d;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
