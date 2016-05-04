package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.SimilarityDegree;

/**
 * Implementation of a mapper class for SimilarityDegree. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * <p>
 * 
 * @author JeromeBadt, Thies
 */
public class SimilarityDegreeMapper {
	private static SimilarityDegreeMapper similarityDegreeMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>
	 */
	private SimilarityDegreeMapper() {
	}

	/**
	 * SimilarityDegreeMapper should be instantiated by this method to ensure
	 * that only a single instance exists.
	 * <p>
	 * 
	 * @return The <code>SimilarityDegreeMapper</code> instance.
	 */
	public static SimilarityDegreeMapper similarityDegreeMapper() {
		if (similarityDegreeMapper == null) {
			similarityDegreeMapper = new SimilarityDegreeMapper();
		}

		return similarityDegreeMapper;
	}

	/**
	 * Insert a <code>SimilarityDegree</code> object into the DB
	 * 
	 * <p>TODO: else block for inserting first object into DB?
	 * 
	 * @param s
	 *            The <code>SimilarityDegree</code> object to be inserted
	 */
	public void insert(SimilarityDegree s) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(similarity_degree_id) AS maxid FROM similarity_degree");

			if (rs.next()) {
				// Set id to max + 1
				s.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO similarity_degree "
						+ "(similarity_degree_id, score, fk_profile_reference, fk_profile_comparison) VALUES ("
						+ s.getId() + "," + s.getScore() + ","
						+ s.getReferenceProfileId() + ","
						+ s.getComparisonProfileId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update a <code>SimilarityDegree</code> object in the DB
	 * 
	 * @param s
	 *            The <code>SimilarityDegree</code> object to be updated
	 */
	public void update(SimilarityDegree s) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE similarity_degree SET score="
					+ s.getScore() + ",fk_profile_reference="
					+ s.getReferenceProfileId() + ",fk_profile_comparison="
					+ s.getComparisonProfileId()
					+ " WHERE similarity_degree_id=" + s.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>SimilarityDegree</code> object from the DB
	 * 
	 * @param s
	 *            The <code>SimilarityDegree</code> object to be deleted
	 */
	public void delete(SimilarityDegree s) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM similarity_degree "
					+ "WHERE similarity_degree_id=" + s.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>SimilarityDegree</code> objects in the DB
	 * 
	 * @return result ArrayList of all <code>SimilarityDegree</code> objects
	 */
	public ArrayList<SimilarityDegree> findAll() {
		Connection con = DBConnection.connection();
		ArrayList<SimilarityDegree> result = new ArrayList<SimilarityDegree>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT similarity_degree_id, score, "
							+ "fk_profile_reference, fk_profile_comparison "
							+ "FROM similarity_degree");

			while (rs.next()) {
				SimilarityDegree s = new SimilarityDegree();
				s.setId(rs.getInt("similarity_degree_id"));
				s.setScore(rs.getInt("score"));
				s.setReferenceProfileId(rs.getInt("fk_profile_reference"));
				s.setComparisonProfileId(rs.getInt("fk_profile_comparison"));

				result.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find all <code>SimilarityDegree</code> objects with a specific reference
	 * profile in the DB
	 * 
	 * @return result ArrayList of found <code>SimilarityDegree</code> objects
	 */
	public ArrayList<SimilarityDegree> findByReferenceProfileId(
			int referenceProfileId) {
		Connection con = DBConnection.connection();
		ArrayList<SimilarityDegree> result = new ArrayList<SimilarityDegree>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT similarity_degree_id, score, "
							+ "fk_profile_reference, fk_profile_comparison "
							+ "FROM similarity_degree "
							+ "WHERE fk_profile_reference="
							+ referenceProfileId);

			while (rs.next()) {
				SimilarityDegree s = new SimilarityDegree();
				s.setId(rs.getInt("similarity_degree_id"));
				s.setScore(rs.getInt("score"));
				s.setReferenceProfileId(rs.getInt("fk_profile_reference"));
				s.setComparisonProfileId(rs.getInt("fk_profile_comparison"));

				result.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
