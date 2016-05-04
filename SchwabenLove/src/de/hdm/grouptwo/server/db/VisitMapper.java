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

public class VisitMapper {
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
						+ "fk_profile_visitor, fk_profile_visited) "
						+ "VALUES (" + v.getId() + ","
						+ v.getVisitorProfileId() + ","
						+ v.getVisitedProfileId() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return v;
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
}
