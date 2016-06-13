package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Property;

/**
 * Implementation of a mapper class for Visit. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * <p>
 * 
 * TODO: DeleteVisitsOf
 * 
 * @author Thies, DenisThierry, JoergJarmer
 */

public class PropertyMapper implements DataMapper<Property> {
	private static PropertyMapper propertyMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private PropertyMapper() {
	}

	/**
	 * PropertyMapper should be instantiated by this method to ensure that only
	 * a single instance exists.
	 * 
	 * @return The <code>PropertyMapper</code> instance
	 */
	public static PropertyMapper propertyMapper() {
		if (propertyMapper == null) {
			propertyMapper = new PropertyMapper();
		}

		return propertyMapper;
	}

	/**
	 * Insert a <code>Property</code> object into the DB.
	 * 
	 * @param p
	 *            The <code>Property</code> object to be inserted
	 * @return The inserted Property (returned because it gets an assigned id)
	 */
	@Override
	public Property insert(Property p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(property_id) AS maxid "
							+ "FROM property ");

			if (rs.next()) {
				// Set id to max + 1
				p.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO property (property_id, "
						+ "explanation) VALUES (" + p.getId() + ",'"
						+ p.getExplanation() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * Update a <code>Property</code> object in the DB.
	 * 
	 * @param p
	 *            The <code>Property</code> object to be updated
	 */
	@Override
	public void update(Property p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE property SET explanation='"
					+ p.getExplanation() + "' WHERE property_id=" + p.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>Property</code> object from the DB.
	 * 
	 * @param p
	 *            The <code>Property</code> object to be deleted
	 */
	@Override
	public void delete(Property p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM property WHERE property_id="
					+ p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Property</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>Property</code> objects
	 */
	@Override
	public ArrayList<Property> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Property> result = new ArrayList<Property>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT property_id, explanation "
					+ "FROM property");

			while (rs.next()) {
				Property p = new Property();
				p.setId(rs.getInt("property_id"));
				p.setExplanation(rs.getString("explanation"));

				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>Property</code> object with a specific ID in the DB.
	 * 
	 * @param id
	 *            The id by which to find the object
	 * @return <code>Property</code> object with specified ID or null if not
	 *         found
	 */
	@Override
	public Property findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT visit_id, "
					+ "fk_visitor_profile_id, fk_visited_profile_id "
					+ "FROM visit WHERE visit_id=" + id);

			if (rs.next()) {
				Property p = new Property();
				p.setId(rs.getInt("property_id"));
				p.setExplanation(rs.getString("explanation"));

				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}