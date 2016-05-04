package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.SearchProfile;

/**
 * Mapper class to persist information Objects in the database
 *
 * @author JoergJarmer
 */
public class SearchProfileMapper {

	private static SearchProfileMapper searchProfileMapper = null;

	/**
	 * private constructor to prevent initialization with <code>new</code>
	 */
	private SearchProfileMapper() {

	}

	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static SearchProfileMapper searchProfileMapper() {
		if (searchProfileMapper == null) {
			searchProfileMapper = new SearchProfileMapper();
		}

		return searchProfileMapper;
	}

	/**
	 * Add new searchProfile to database
	 * 
	 * @param sp
	 *            searchProfile object
	 * @return sp searchProfile object
	 */
	public SearchProfile insert(SearchProfile sp) {
		// Establish database connection
		Connection con = DBConnection.connection();

		try {
			// new SQL statement
			Statement stmt = con.createStatement();
			// new ResultSet
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(search_profile_id) AS maxId FROM SearchProfile");
			if (rs.next()) {
				// maxId + 1 for new entry
				sp.setId(rs.getInt("maxId") + 1);
				// new SQL statement
				stmt = con.createStatement();
				// insert into DB
				stmt.executeUpdate("INSERT INTO search_Profile (search_profile_id, "
						+ "gender, min_age, max_age, hair_color, physique, "
						+ "min_height, max_height, smoker, education, "
						+ "profession, religion) VALUES ('"
						+ sp.getId()
						+ "', '"
						+ sp.getGender()
						+ "', '"
						+ sp.getMinAge()
						+ "', '"
						+ sp.getMaxAge()
						+ "', '"
						+ sp.getHairColor()
						+ "', '"
						+ sp.getPhysique()
						+ "', '"
						+ sp.getMinHeight()
						+ "', '"
						+ sp.getMaxHeight()
						+ "', '"
						+ sp.getSmoker()
						+ "', '"
						+ sp.getEducation()
						+ "', '"
						+ sp.getProfession()
						+ "', '" + sp.getReligion());
			}
		}

		// Error handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}

	/**
	 * Update SearchProfile from database
	 * 
	 * @param sp
	 *            SearchProfile Object
	 * @return sp SearchProfile Object
	 */
	public SearchProfile update(SearchProfile sp) {
		// Establish database connection
		Connection con = DBConnection.connection();

		try {
			// New SQL Statement
			Statement stmt = con.createStatement();
			// Execute SQL query
			stmt.executeQuery("UPDATE search_profile "
					+ "SET search_profile_id=\""
					+ sp.getId()
					+ "\", gender=\""
					+ sp.getGender()
					+ "\", min_age=\""
					+ sp.getMinAge()
					+ "\", max_age=\""
					+ sp.getMaxAge()
					+ "\", hair_color=\""
					+ sp.getHairColor()
					+ "\", physique=\""
					+ sp.getPhysique()
					+ "\", min_height=\""
					+ sp.getMinHeight()
					+ "\", max_height=\""
					+ sp.getMaxHeight()
					+ "\", smoker=\""
					+ sp.getSmoker()
					+ "\", education=\""
					+ sp.getEducation()
					+ "\", profession=\""
					+ sp.getProfession()
					+ "\", religion=\""
					+ sp.getReligion()
					+ "WHERE search_profile_id=" + sp.getId());
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}

	/**
	 * Delete SearchProfile from database
	 * 
	 * @param sp
	 *            SearchProfile Object
	 */
	public void delete(SearchProfile sp) {
		// Establish database connection
		Connection con = DBConnection.connection();

		try {
			// New SQL Statement
			Statement stmt = con.createStatement();
			// Execute SQL query
			stmt.executeUpdate("DELETE FROM search_profile WHERE search_profile_id = '"
					+ sp.getId() + "'");
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all searchprofiles in database
	 * 
	 * @return result ArrayList of Searchprofiles
	 */
	public ArrayList<SearchProfile> findAll() {
		// Establish database connection
		Connection con = DBConnection.connection();
		// Create ArrayList for results
		ArrayList<SearchProfile> result = new ArrayList<SearchProfile>();

		try {
			// New SQL statement
			Statement stmt = con.createStatement();
			// Execute SQL query
			ResultSet rs = stmt.executeQuery("SELECT *"
					+ " FROM search_profile;");
			while (rs.next()) {
				// New searchprofile
				SearchProfile sp = new SearchProfile();
				// Set searchprofile properties
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
				// Add searchprofle to ArrayList
				result.add(sp);
			}
		}
		// Error handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Return ArrayList of BookmarkLists
		return result;
	}

	/**
	 * Find searchprofiles by informationId in database
	 * 
	 * @return result ArrayList of Searchprofiles
	 */
	public ArrayList<SearchProfile> findByInformationId(int informationId) {
		// Establish database connection
		Connection con = DBConnection.connection();
		// Create ArrayList for results
		ArrayList<SearchProfile> result = new ArrayList<SearchProfile>();

		try {
			// New SQL statement
			Statement stmt = con.createStatement();
			// Execute SQL query
			ResultSet rs = stmt
					.executeQuery("SELECT search_profile_id, gender, min_age, max_age,"
							+ " hair_color, physique, min_height, max_height, smoker,"
							+ " education, profession, religion"
							+ " FROM search_profile"
							+ " JOIN information"
							+ " ON fk_search_profile_id = search_profile_id"
							+ " WHERE information_id = '" + informationId + "'");
			while (rs.next()) {
				// New searchprofile
				SearchProfile sp = new SearchProfile();
				// Set searchprofile properties
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
				// Add searchprofle to ArrayList
				result.add(sp);
			}
		}
		// Error handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Return ArrayList of Searchprofiles
		return result;
	}
}
