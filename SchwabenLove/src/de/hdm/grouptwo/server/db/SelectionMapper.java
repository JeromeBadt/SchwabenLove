package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Selection;

/**
 * Implementation of a mapper class for Selection. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * 
 * @author Thies, DenisThierry, JeromeBadt
 */

public class SelectionMapper implements DataMapper<Selection> {
	private static SelectionMapper selectionMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private SelectionMapper() {
	}

	/**
	 * SelectionMapper should be instantiated by this method to ensure that only
	 * a single instance exists.
	 * 
	 * @return The <code>SelectionMapper</code> instance
	 */
	public static SelectionMapper selectionMapper() {
		if (selectionMapper == null) {
			selectionMapper = new SelectionMapper();
		}

		return selectionMapper;
	}

	/**
	 * Insert a <code>Selection</code> object into the DB.
	 * 
	 * @param s
	 *            The <code>Selection</code> object to be inserted
	 * @return The inserted Selection (returned because it gets an assigned id)
	 */
	@Override
	public Selection insert(Selection s) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(fk_property_id) AS maxId "
							+ "FROM selection ");

			if (rs.next()) {
				// Set id to max + 1
				s.setId(rs.getInt("maxId") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO selection (fk_property_id) "
						+ "VALUES (" + s.getId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s;
	}

	/**
	 * Update a <code>Selection</code> object in the DB. <br>
	 * Empty since Selection only has on id and no other attributes to update.
	 * 
	 * @param s
	 *            The <code>Selection</code> object to be updated
	 */
	@Override
	public void update(Selection s) {
	}

	/**
	 * Delete a <code>Selection</code> object from the DB.
	 * 
	 * @param s
	 *            The <code>Selection</code> object to be deleted
	 */
	@Override
	public void delete(Selection s) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM selection WHERE fk_property_id="
					+ s.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Selection</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>Selection</code> objects
	 */
	@Override
	public ArrayList<Selection> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Selection> result = new ArrayList<Selection>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT fk_property_id "
					+ "FROM selection");

			while (rs.next()) {
				Selection s = new Selection();
				s.setId(rs.getInt("fk_property_id"));

				result.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Find <code>Selection</code> object with a specific ID in the DB.
	 * 
	 * @param id
	 *            The id by which to find the object
	 * @return <code>Selection</code> object with specified ID or null if not
	 *         found
	 */
	@Override
	public Selection findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT fk_property_id "
					+ "FROM selection WHERE fk_property_id=" + id);

			if (rs.next()) {
				Selection s = new Selection();
				s.setId(rs.getInt("fk_property_id"));

				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
