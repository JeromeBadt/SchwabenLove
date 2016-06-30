package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.SearchProfile;

/**
 * Implementation of a mapper class for SearchProfile. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * <p>
 * 
 * @author Thies, JoergJarmer, JeromeBadt
 */

public class SearchProfileMapper implements DataMapper<SearchProfile> {
	private static SearchProfileMapper searchProfileMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private SearchProfileMapper() {

	}

	/**
	 * VisitMapper should be instantiated by this method to ensure that only a
	 * single instance exists.
	 * 
	 * @return The <code>VisitMapper</code> instance
	 */
	public static SearchProfileMapper searchProfileMapper() {
		if (searchProfileMapper == null) {
			searchProfileMapper = new SearchProfileMapper();
		}

		return searchProfileMapper;
	}

	/**
	 * Insert a <code>SearchProfile</code> object into the DB.
	 * 
	 * @param sp
	 *            The <code>SearchProfile</code> object to be inserted
	 * @return The inserted SearchProfile (returned because it gets an assigned
	 *         id)
	 */
	@Override
	public SearchProfile insert(SearchProfile sp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(search_profile_id) AS maxId "
							+ "FROM search_profile");

			if (rs.next()) {
				// Set id to max + 1
				sp.setId(rs.getInt("maxId") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO search_profile ("
						+ "search_profile_id, gender, min_age, max_age, "
						+ "hair_color, physique, min_height, max_height, "
						+ "smoker, education, profession, religion) VALUES ("
						+ sp.getId() + ","
						+ DataMapperHelper.checkNull(sp.getGender()) + ","
						+ DataMapperHelper.checkNull(sp.getMinAge()) + ","
						+ DataMapperHelper.checkNull(sp.getMaxAge()) + ","
						+ DataMapperHelper.checkNull(sp.getHairColor()) + ","
						+ DataMapperHelper.checkNull(sp.getPhysique()) + ","
						+ DataMapperHelper.checkNull(sp.getMinHeight()) + ","
						+ DataMapperHelper.checkNull(sp.getMaxHeight()) + ","
						+ DataMapperHelper.checkNull(sp.getSmoker()) + ","
						+ DataMapperHelper.checkNull(sp.getEducation()) + ","
						+ DataMapperHelper.checkNull(sp.getProfession()) + ","
						+ DataMapperHelper.checkNull(sp.getReligion()) + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sp;
	}

	/**
	 * Update a <code>SearchProfile</code> object in the DB.
	 * 
	 * @param sp
	 *            The <code>SearchProfile</code> object to be updated
	 */
	@Override
	public void update(SearchProfile sp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("UPDATE search_profile " + "SET gender='"
					+ sp.getGender() + "',min_age=" + sp.getMinAge()
					+ ",max_age=" + sp.getMaxAge() + ",hair_color='"
					+ sp.getHairColor() + "',physique='" + sp.getPhysique()
					+ "',min_height=" + sp.getMinHeight() + ",max_height="
					+ sp.getMaxHeight() + ",smoker='" + sp.getSmoker()
					+ "',education='" + sp.getEducation() + "',profession='"
					+ sp.getProfession() + "',religion='" + sp.getReligion()
					+ "' WHERE search_profile_id=" + sp.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>SearchProfile</code> object from the DB.
	 * 
	 * @param sp
	 *            The <code>SearchProfile</code> object to be deleted
	 */
	@Override
	public void delete(SearchProfile sp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM search_profile WHERE "
					+ "search_profile_id=" + sp.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>SearchProfile</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>SearchProfile</code> objects
	 */
	@Override
	public ArrayList<SearchProfile> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<SearchProfile> result = new ArrayList<SearchProfile>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT search_profile_id, "
					+ "gender, min_age, max_age, hair_color, physique, "
					+ "min_height, max_height, smoker, education, profession, "
					+ "religion FROM search_profile");

			while (rs.next()) {
				SearchProfile sp = new SearchProfile();
				sp.setId(rs.getInt("search_profile_id"));
				sp.setGender(rs.getString("gender"));
				sp.setMinAge(rs.getInt("min_age"));
				sp.setMaxAge(rs.getInt("max_age"));
				sp.setHairColor(rs.getString("hair_color"));
				sp.setPhysique(rs.getString("physique"));
				sp.setMinHeight(rs.getInt("min_height"));
				sp.setMaxHeight(rs.getInt("max_height"));
				sp.setSmoker(rs.getString("smoker"));
				sp.setEducation(rs.getString("education"));
				sp.setProfession(rs.getString("profession"));
				sp.setReligion(rs.getString("religion"));

				result.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>SearchProfile</code> object with a specific ID in the DB.
	 * 
	 * @param id
	 *            The id by which to find the object
	 * @return <code>SearchProfile</code> object with specified ID or null if
	 *         not found
	 */
	@Override
	public SearchProfile findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT search_profile_id, "
					+ "gender, min_age, max_age, hair_color, physique, "
					+ "min_height, max_height, smoker, education, profession, "
					+ "religion FROM search_profile WHERE search_profile_id="
					+ id);

			if (rs.next()) {
				SearchProfile sp = new SearchProfile();
				sp.setId(rs.getInt("search_profile_id"));
				sp.setGender(rs.getString("gender"));
				sp.setMinAge(rs.getInt("min_age"));
				sp.setMaxAge(rs.getInt("max_age"));
				sp.setHairColor(rs.getString("hair_color"));
				sp.setPhysique(rs.getString("physique"));
				sp.setMinHeight(rs.getInt("min_height"));
				sp.setMaxHeight(rs.getInt("max_height"));
				sp.setSmoker(rs.getString("smoker"));
				sp.setEducation(rs.getString("education"));
				sp.setProfession(rs.getString("profession"));
				sp.setReligion(rs.getString("religion"));

				return sp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
