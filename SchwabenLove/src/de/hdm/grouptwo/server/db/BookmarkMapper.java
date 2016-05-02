package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Bookmark;
import de.hdm.grouptwo.shared.bo.BookmarkList;

/**
 * Mapper class to persist bookmark objects on the database
 * 
 * @author joshuahill, thies
 *
 */

public class BookmarkMapper {
	
	private static BookmarkMapper bookmarkMapper = null;
	
	/**
	 * private constructor to prevent initialization with <code>new</code>
	 */
	private BookmarkMapper() {
		
	}
	
	/**
	 * Singleton
	 * @return
	 */
	public static BookmarkMapper bookmarkMapper() {
		if (bookmarkMapper == null) {
			bookmarkMapper = new BookmarkMapper();
		}

		return bookmarkMapper;
	}
	
	/**
	 * Add new bookmark to database
	 * 
	 * @param b Bookmark object 
	 * @return b Bookmark object
	 */
	public Bookmark insert(Bookmark b) {
		// Establish database connection
		Connection con = DBConnection.connection();
		
		try {
			// new SQL statement
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Bookmark (fk_profile, fk_bookmark_list) " +
					"VALUES ('" + b.getProfile_Id()+ "', '" + b.getBookmarkList_Id() + "')");
			
			}
		
		// Error handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * Edit an existing Bookmark of a bookmarklist
	 * 
	 * @param b Bookmark Object
	 * @return b Bookmark Object
	 */
	public Bookmark update(Bookmark b) {
		// Establish database connection
		Connection con = DBConnection.connection();
		
		try {
			// New SQL statement 
			Statement stmt = con.createStatement();
			// Execute SQL query 
			stmt.executeUpdate("UPDATE Bookmark SET fk_profile = '" + b.getProfile_Id() + "', fk_bookmark_list = '" +
					b.getBookmarkList_Id() + "' WHERE fk_bookmark_list = '" + b.getBookmarkList_Id() + "' AND fk_profile = '" +
					b.getProfile_Id() + "'");
		}
		// Error handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * Delete Bookmark from database
	 * 
	 * @param b Bookmark Object
	 */
	public void delete(Bookmark b) {
		// Establish database connection
		Connection con = DBConnection.connection();
		
		try {
			// New SQL Statement 
			Statement stmt = con.createStatement();
			// Execute SQL query
			stmt.executeUpdate("DELETE FROM Bookmark WHERE fk_bookmark_list = '" + b.getBookmarkList_Id() + "' AND fk_profile = '" +
					b.getProfile_Id() + "'");
		}
		// Error Handling
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Find all bookmarks in database
	 * 
	 * @return result ArrayList of Bookmarks
	 */
	public ArrayList<Bookmark> findAll() {
		// Establish database connection
		Connection con = DBConnection.connection();
		// Create ArrayList for results
		ArrayList<Bookmark> result = new ArrayList<Bookmark>();
		
		try {
			// New SQL statement
			Statement stmt = con.createStatement();
			// Execute SQL query
			ResultSet rs = stmt.executeQuery("SELECT *" +
					" FROM Bookmark;");
			while(rs.next()) {
				// New bookmark 
				Bookmark b = new Bookmark();
				// Set bookmark properties
				b.setBookmarkList_Id(rs.getInt("fk_bookmark_list"));
				b.setProfile_Id(rs.getInt("fk_profile"));
				// Add bookmark to ArrayList
				result.add(b);
			}
		}
		// Error handling
		catch (SQLException e) {
			e.printStackTrace();
		}
		// Return ArrayList of Bookmarks
		return result;
	}
	
	/**
	 * Find all Bookmarks belonging to a given BookmarkList
	 * 
	 * @param bl Bookmarklist
	 * @return result ArrayList of Bookmarks
	 */
	public ArrayList<Bookmark> findByBookmarkList(BookmarkList bl) {
		// Establish database connection
		Connection con = DBConnection.connection();
		// Create ArrayList for results
		ArrayList<Bookmark> result = new ArrayList<Bookmark>();
		
		try {
			// New SQL statement
			Statement stmt = con.createStatement();
			// Execute SQL query
			ResultSet rs = stmt.executeQuery("SELECT *" +
					" FROM Bookmark WHERE fk_bookmark_list = '" + bl.getId() + "'");
					while(rs.next()) {
						// New bookmark 
						Bookmark b = new Bookmark();
						// Set bookmark properties
						b.setBookmarkList_Id(rs.getInt("fk_bookmark_list"));
						b.setProfile_Id(rs.getInt("fk_profile"));
						// Add bookmark to ArrayList
						result.add(b);
					}
				}
				// Error handling
				catch (SQLException e) {
					e.printStackTrace();
				}
				// Return ArrayList of Bookmarks
				return result;
	}
	
	
	

}
