package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.BookmarkList;

/**
 * Mapper class to persist BookmarList Objects in the database
 * TODO Info: keine update methode, da nicht benötigt!?
 * 
 * @author joshuahill
 *
 */
public class BookmarkListMapper {

	private static BookmarkListMapper bookmarkListMapper = null;
	
	/**
	 * private constructor to prevent initialization with <code>new</new>
	 */
	private BookmarkListMapper() {
		
	}
	
	/**
	 * Singleton
	 * @return
	 */
	public static BookmarkListMapper bookmarkListMapper() {
		if (bookmarkListMapper == null) {
			bookmarkListMapper = new BookmarkListMapper();
		}

		return bookmarkListMapper;
	}
	
	/**
	 * Add new bookmarklist to database
	 * 
	 * @param bl BookmarkList object
	 * @return bl BookmarkList object
	 */
	public BookmarkList insert(BookmarkList bl) {
		// Establish database connection
		Connection con = DBConnection.connection();
				
			try {
				// new SQL statement
				Statement stmt = con.createStatement();
				// new ResultSet
				ResultSet rs = stmt.executeQuery("SELECT MAX(bookmark_id) AS maxId FROM Bookmarklist");
				if(rs.next()) {
					// maxId + 1 for new entry
					bl.setId(rs.getInt("maxId") + 1);
					// new SQL statement
					stmt = con.createStatement();
					// insert into DB
					stmt.executeUpdate("INSERT INTO Bookmarklist (fk_profile_bookmark_list_possession, bookmark_id) " +
							"VALUES ('" + bl.getProfileId() + "', '" + bl.getId() + "')");
					}
				}
			// Error handling
			catch (SQLException e) {
				e.printStackTrace();
			}
			return bl;
	}
	
	/**
	 * Delete Bookmarklist from database
	 * 
	 * @param bl BookmarkList Object
	 */
	public void delete(BookmarkList bl) {
		// Establish database connection
		Connection con = DBConnection.connection();
		
		try {
			// New SQL Statement 
			Statement stmt = con.createStatement();
			// Execute SQL query
			stmt.executeUpdate("DELETE FROM Bookmarklist WHERE bookmark_id = '" + bl.getId() + "'");
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Find all bookmarklists in database
	 * 
	 * @return result ArrayList of BookmarkLists
	 */
	public ArrayList<BookmarkList> findAll() {
		// Establish database connection
		Connection con = DBConnection.connection();
		// Create ArrayList for results
		ArrayList<BookmarkList> result = new ArrayList<BookmarkList>();
		
		try {
			// New SQL statement
			Statement stmt = con.createStatement();
			// Execute SQL query
			ResultSet rs = stmt.executeQuery("SELECT *" +
					" FROM Bookmarklist;");
			while(rs.next()) {
				// New bookmark 
				BookmarkList bl = new BookmarkList();
				// Set bookmarklist properties
				bl.setId(rs.getInt("bookmark_id"));
				bl.setProfileId(rs.getInt("fk_profile_bookmark_list_possession"));
				// Add bookmarklist to ArrayList
				result.add(bl);
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
	 * Find Bookmarklist of a profile
	 * 
	 * @param profileID 
	 * @return bl BookmarkList object
	 */
	public BookmarkList findByProfile(int profileId) {
		// Establish database connection
		Connection con = DBConnection.connection();
		
		try {
			// New SQL statement
			Statement stmt = con.createStatement();
			// Execute SQL query
			ResultSet rs = stmt.executeQuery("SELECT *" +
					" FROM Bookmarklist WHERE fk_profile_bookmark_list_possession = '" + profileId + "'");
					while(rs.next()) {
						// New bookmarklist 
						BookmarkList bl = new BookmarkList();
						// Set bookmark properties
						bl.setId(rs.getInt("bookmark_id"));
						bl.setProfileId(rs.getInt("fk_profile_bookmark_list_possession"));
						// return the corresponding bookmarklist
						return bl;
					}
				}
				// Error handling
				catch (SQLException e) {
					e.printStackTrace();
				}
				// Return null if nothing found
				return null;
	}
	
}
