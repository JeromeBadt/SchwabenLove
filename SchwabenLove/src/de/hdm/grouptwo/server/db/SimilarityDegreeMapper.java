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
 * TODO: DeleteSimilarityDegreesOf
 * 
 * @author JeromeBadt, Thies
 */
public class SimilarityDegreeMapper implements DataMapper<SimilarityDegree> {
	private static SimilarityDegreeMapper similarityDegreeMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private SimilarityDegreeMapper() {
	}

	/**
	 * SimilarityDegreeMapper should be instantiated by this method to ensure
	 * that only a single instance exists.
	 * 
	 * @return The <code>SimilarityDegreeMapper</code> instance
	 */
	public static SimilarityDegreeMapper similarityDegreeMapper() {
		if (similarityDegreeMapper == null) {
			similarityDegreeMapper = new SimilarityDegreeMapper();
		}

		return similarityDegreeMapper;
	}

	/**
	 * Insert a <code>SimilarityDegree</code> object into the DB.
	 * 
	 * <p>
	 * TODO: else block for inserting first object into DB?
	 * 
	 * @param sd
	 *            The <code>SimilarityDegree</code> object to be inserted
	 */
	public void insert(SimilarityDegree sd) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(similarity_degree_id) AS maxid "
							+ "FROM similarity_degree");

			if (rs.next()) {
				// Set id to max + 1
				sd.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO similarity_degree "
						+ "(similarity_degree_id, score, "
						+ "fk_reference_profile_id, fk_comparison_profile_id) "
						+ "VALUES (" + sd.getId() + "," + sd.getScore() + ","
						+ sd.getReferenceProfileId() + ","
						+ sd.getComparisonProfileId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update a <code>SimilarityDegree</code> object in the DB.
	 * 
	 * @param sd
	 *            The <code>SimilarityDegree</code> object to be updated
	 */
	public void update(SimilarityDegree sd) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE similarity_degree SET score="
					+ sd.getScore() + ",fk_reference_profile_id="
					+ sd.getReferenceProfileId() + ",fk_comparison_profile_id="
					+ sd.getComparisonProfileId()
					+ " WHERE similarity_degree_id=" + sd.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>SimilarityDegree</code> object from the DB.
	 * 
	 * @param sd
	 *            The <code>SimilarityDegree</code> object to be deleted
	 */
	public void delete(SimilarityDegree sd) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM similarity_degree "
					+ "WHERE similarity_degree_id=" + sd.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>SimilarityDegree</code> objects in the DB.
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
							+ "fk_reference_profile_id, fk_comparison_profile_id "
							+ "FROM similarity_degree");

			while (rs.next()) {
				SimilarityDegree sd = new SimilarityDegree();
				sd.setId(rs.getInt("similarity_degree_id"));
				sd.setScore(rs.getInt("score"));
				sd.setReferenceProfileId(rs.getInt("fk_reference_profile_id"));
				sd.setComparisonProfileId(rs.getInt("fk_comparison_profile_id"));

				result.add(sd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>SimilarityDegree</code> object with a specific ID in the DB.
	 * 
	 * @return result <code>SimilarityDegree</code> object with specified ID or
	 *         null if not found
	 */
	public SimilarityDegree findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT similarity_degree_id, "
							+ "fk_reference_profile_id, fk_comparison_profile_id "
							+ "FROM similarity_degree WHERE similarity_degree_id="
							+ id);

			if (rs.next()) {
				SimilarityDegree sd = new SimilarityDegree();
				sd.setId(rs.getInt("similarity_degree_id"));
				sd.setScore(rs.getInt("score"));
				sd.setReferenceProfileId(rs.getInt("fk_reference_profile_id"));
				sd.setComparisonProfileId(rs.getInt("fk_comparison_profile_id"));

				return sd;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Find all <code>SimilarityDegree</code> objects with a specific reference
	 * profile in the DB.
	 * 
	 * @param referenceProfileId
	 *            The reference profile id by which to find the objects
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
							+ "fk_reference_profile_id, fk_comparison_profile_id "
							+ "FROM similarity_degree "
							+ "WHERE fk_reference_profile_id="
							+ referenceProfileId);

			while (rs.next()) {
				SimilarityDegree sd = new SimilarityDegree();
				sd.setId(rs.getInt("similarity_degree_id"));
				sd.setScore(rs.getInt("score"));
				sd.setReferenceProfileId(rs.getInt("fk_reference_profile_id"));
				sd.setComparisonProfileId(rs.getInt("fk_comparison_profile_id"));

				result.add(sd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
