package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.BookmarkList;

/**
 * Implementation of a mapper class for BookmarList. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * 
 * @author Thies, JoshuaHill, JoergJarmer
 */

public class BookmarkListMapper implements DataMapper<BookmarkList> {
	private static BookmarkListMapper bookmarkListMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private BookmarkListMapper() {
	}

	/**
	 * BookmarkListMapper should be instantiated by this method to ensure that
	 * only a single instance exists.
	 * 
	 * @return The <code>BookmarkListMapper</code> instance
	 */
	public static BookmarkListMapper bookmarkListMapper() {
		if (bookmarkListMapper == null) {
			bookmarkListMapper = new BookmarkListMapper();
		}

		return bookmarkListMapper;
	}

	/**
	 * Insert a <code>BookmarkList</code> object into the DB.
	 * 
	 * <p>
	 * TODO: else block for inserting first object into DB?
	 * 
	 * @param bl
	 *            The <code>BookmarkList</code> object to be inserted
	 */
	public void insert(BookmarkList bl) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt.executeQuery("SELECT MAX(bookmark_id) AS "
					+ "maxId FROM bookmark_list");

			if (rs.next()) {
				// Set id to max + 1
				bl.setId(rs.getInt("maxId") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO bookmark_list ("
						+ "bookmark_list_id, fk_profile_id) VALUES ("
						+ bl.getId() + "," + bl.getProfileId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update a <code>BookmarkList</code> object in the DB.
	 * 
	 * @param bl
	 *            The <code>BookmarkList</code> object to be updated
	 */
	public void update(BookmarkList bl) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE bookmark_list SET bookmark_list_id="
					+ bl.getId() + ",fk_profile_id=" + bl.getProfileId()
					+ " WHERE bookmark_list_id=" + bl.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>BookmarkList</code> object from the DB.
	 * 
	 * @param bl
	 *            The <code>BookmarkList</code> object to be deleted
	 */
	public void delete(BookmarkList bl) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM bookmarklist WHERE "
					+ "bookmark_list_id=" + bl.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>BookmarkList</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>BookmarkList</code> objects
	 */
	public ArrayList<BookmarkList> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<BookmarkList> result = new ArrayList<BookmarkList>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT bookmark_list_id, fk_profile_id"
							+ " FROM bookmark_list");

			while (rs.next()) {
				BookmarkList bl = new BookmarkList();
				bl.setId(rs.getInt("bookmark_list_id"));
				bl.setProfileId(rs.getInt("fk_profile_id"));

				result.add(bl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>BookmarkList</code> object with a specific ID in the DB.
	 * 
	 * @param id
	 *            The id by which to find the object
	 * @return <code>BookmarkList</code> object with specified ID or null if not
	 *         found
	 */
	public BookmarkList findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT bookmark_list_id, fk_profile_id"
							+ "FROM bookmark_list WHERE bookmark_list_id=" + id);

			if (rs.next()) {
				BookmarkList bl = new BookmarkList();
				bl.setId(rs.getInt("bookmark_list_id"));
				bl.setProfileId(rs.getInt("fk_profile_id"));

				return bl;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Find the <code>BookmarkList</code> object for a specific profile in the
	 * DB.
	 * 
	 * @param profileId
	 *            The profile id by which to find the object
	 * @return The found <code>BookmarkList</code> object
	 */
	public BookmarkList findByProfile(int profileId) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT bookmark_list_id, fk_profile_id "
							+ "FROM bookmark_list WHERE fk_profile_id="
							+ profileId);

			while (rs.next()) {
				BookmarkList bl = new BookmarkList();
				bl.setId(rs.getInt("bookmark_list"));
				bl.setProfileId(rs.getInt("fk_profile_id"));

				return bl;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
