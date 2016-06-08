package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Visit;

/**
 * Implementation of a mapper class for Visit. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * <p>
 * 
 * TODO: DeleteVisitsOf
 * 
 * @author Thies, ManuelRuss, JeromeBadt
 */

public class VisitMapper implements DataMapper<Visit> {
	private static VisitMapper visitMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private VisitMapper() {
	}

	/**
	 * VisitMapper should be instantiated by this method to ensure that only a
	 * single instance exists.
	 * 
	 * @return The <code>VisitMapper</code> instance
	 */
	public static VisitMapper visitMapper() {
		if (visitMapper == null) {
			visitMapper = new VisitMapper();
		}

		return visitMapper;
	}

	/**
	 * Insert a <code>Visit</code> object into the DB.
	 * 
	 * @param v
	 *            The <code>Visit</code> object to be inserted
	 * @return The inserted Visit (returned because it gets an assigned id)
	 */
	@Override
	public Visit insert(Visit v) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt.executeQuery("SELECT MAX(visit_id) AS maxid"
					+ "FROM visit ");

			if (rs.next()) {
				// Set id to max + 1
				v.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO visit (visit_id, "
						+ "fk_visitor_profile_id, fk_visited_profile_id) "
						+ "VALUES (" + v.getId() + ","
						+ v.getVisitorProfileId() + ","
						+ v.getVisitedProfileId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return v;
	}

	/**
	 * Update a <code>Visit</code> object in the DB.
	 * 
	 * @param v
	 *            The <code>Visit</code> object to be updated
	 */
	@Override
	public void update(Visit v) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE visit SET fk_visitor_profile_id="
					+ v.getVisitorProfileId() + ",fk_visited_profile_id="
					+ v.getVisitedProfileId() + " WHERE visit_id=" + v.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>Visit</code> object from the DB.
	 * 
	 * @param v
	 *            The <code>Visit</code> object to be deleted
	 */
	@Override
	public void delete(Visit v) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM visit WHERE visit_id=" + v.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Visit</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>Visit</code> objects
	 */
	@Override
	public ArrayList<Visit> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Visit> result = new ArrayList<Visit>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT visit_id, "
					+ "fk_visitor_profile_id, fk_visited_profile_id "
					+ "FROM visit");

			while (rs.next()) {
				Visit v = new Visit();
				v.setId(rs.getInt("visit_id"));
				v.setVisitorProfileId(rs.getInt("fk_visitor_profile_id"));
				v.setVisitedProfileId(rs.getInt("fk_visited_profile_id"));

				result.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>Visit</code> object with a specific ID in the DB.
	 * 
	 * @param id
	 *            The id by which to find the object
	 * @return <code>Visit</code> object with specified ID or null if not found
	 */
	@Override
	public Visit findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT visit_id, "
					+ "fk_visitor_profile_id, fk_visited_profile_id "
					+ "FROM visit WHERE visit_id=" + id);

			if (rs.next()) {
				Visit v = new Visit();
				v.setId(rs.getInt("visit_id"));
				v.setVisitorProfileId(rs.getInt("fk_visitor_profile_id"));
				v.setVisitedProfileId(rs.getInt("fk_visited_profile_id"));

				return v;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Find all <code>Visit</code> objects with a specific visitor profile in
	 * the DB.
	 * 
	 * @param visitorProfileId
	 *            The visitor profile id by which to find the objects
	 * @return ArrayList of found <code>Visit</code> objects
	 */
	public ArrayList<Visit> findByVisitorProfileId(int visitorProfileId) {
		Connection con = DBConnection.connection();

		ArrayList<Visit> result = new ArrayList<Visit>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT visit_id, "
					+ "fk_visitor_profile_id, fk_visited_profile_id "
					+ "FROM visit WHERE fk_visitor_profile_id="
					+ visitorProfileId);

			while (rs.next()) {
				Visit v = new Visit();
				v.setId(rs.getInt("visit_id"));
				v.setVisitorProfileId(rs.getInt("fk_profile_visitor"));
				v.setVisitedProfileId(rs.getInt("fk_profile_visited"));

				result.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
