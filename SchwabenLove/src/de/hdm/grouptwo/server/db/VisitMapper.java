package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Information;
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
	 * Private constructor to prevent initialization with <code>new</code>
	 */
	protected VisitMapper() {
	}

	/**
	 * VisitMapper should be instantiated by this method to ensure that only a
	 * single instance exists.
	 * <p>
	 * 
	 * @return The <code>VisitMapper</code> instance.
	 */
	public static VisitMapper visitMapper() {
		if (visitMapper == null) {
			visitMapper = new VisitMapper();
		}

		return visitMapper;
	}

	/**
	 * Insert a <code>Visit</code> object into the DB
	 * 
	 * <p>
	 * TODO: else block for inserting first object into DB?
	 * 
	 * @param i
	 *            The <code>Visit</code> object to be inserted
	 */
	public void insert(Visit v) {
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
						+ "fk_profile_visitor, fk_profile_visited) "
						+ "VALUES (" + v.getId() + ","
						+ v.getVisitorProfileId() + ","
						+ v.getVisitedProfileId() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Visit t) {
		// TODO Auto-generated method stub

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
	 * Delete a <code>Visit</code> object from the DB
	 * 
	 * @param i
	 *            The <code>Visit</code> object to be deleted
	 */
	public void delete(Visit v) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM visit " + "WHERE visit_id="
					+ v.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Visit</code> objects in the DB
	 * 
	 * @return result ArrayList of all <code>Visit</code> objects
	 */
	public ArrayList<Visit> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Visit> result = new ArrayList<Visit>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT visit_id, "
					+ "fk_profile_visitor, fk_profile_visited FROM visit");

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

	/**
	 * Find <code>Visit</code> objects with a specific ID in the DB.
	 * 
	 * @return result <code>Visit</code> objects with specified ID or null if not found
	 */
	public Visit findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT visit_id, "
					+ "fk_profile_visitor, fk_profile_visited FROM visit");

			if (rs.next()) {
				Visit v = new Visit();
				v.setId(rs.getInt("visit_id"));
				v.setVisitorProfileId(rs.getInt("fk_profile_visitor"));
				v.setVisitedProfileId(rs.getInt("fk_profile_visited"));

				return v;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Find all <code>Visit</code> objects in the DB
	 * 
	 * @return result ArrayList of found <code>Visit</code> objects
	 */
	public ArrayList<Visit> findByVisitorProfileId(int visitorProfileId) {
		Connection con = DBConnection.connection();

		ArrayList<Visit> result = new ArrayList<Visit>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT visit_id, "
					+ "fk_profile_visitor, fk_profile_visited FROM visit "
					+ "WHERE fk_profile_visitor = " + visitorProfileId);

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
