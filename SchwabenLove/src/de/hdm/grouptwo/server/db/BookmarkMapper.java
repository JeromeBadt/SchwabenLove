package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Bookmark;

/**
 * Implementation of a mapper class for Bookmark. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * 
 * @author Joshuahill, Thies
 */

public class BookmarkMapper implements DataMapper<Bookmark> {
	private static BookmarkMapper bookmarkMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private BookmarkMapper() {
	}

	/**
	 * BookmarkMapper should be instantiated by this method to ensure that only
	 * a single instance exists.
	 * 
	 * @return The <code>BookmarkMapper</code> instance
	 */
	public static BookmarkMapper bookmarkMapper() {
		if (bookmarkMapper == null) {
			bookmarkMapper = new BookmarkMapper();
		}

		return bookmarkMapper;
	}

	/**
	 * Insert a <code>Bookmark</code> object into the DB.
	 * 
	 * @param b
	 *            The <code>Bookmark</code> object to be inserted
	 * @return The inserted Bookmark (returned because it gets an assigned id)
	 */
	@Override
	public Bookmark insert(Bookmark b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt.executeQuery("SELECT MAX(bookmark_id) AS "
					+ "maxId FROM bookmark");

			if (rs.next()) {
				// Set id to max + 1
				b.setId(rs.getInt("maxId") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO bookmark (bookmark_id, "
						+ "fk_profile_id, fk_bookmark_list_id) VALUES ("
						+ b.getId() + "," + b.getProfileId() + ","
						+ b.getBookmarkListId() + ")");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}

	/**
	 * Update a <code>Bookmark</code> object in the DB.
	 * 
	 * @param b
	 *            The <code>Bookmark</code> object to be updated
	 */
	@Override
	public void update(Bookmark b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE bookmark SET fk_profile_id="
					+ b.getProfileId() + ",fk_bookmark_list_id="
					+ b.getBookmarkListId() + " WHERE bookmark_id=" + b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>Bookmark</code> object from the DB.
	 * 
	 * @param b
	 *            The <code>Bookmark</code> object to be deleted
	 */
	@Override
	public void delete(Bookmark b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM bookmark WHERE bookmark_id="
					+ b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Bookmark</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>Bookmark</code> objects
	 */
	@Override
	public ArrayList<Bookmark> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Bookmark> result = new ArrayList<Bookmark>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT bookmark_id, "
					+ "fk_profile_id, fk_bookmark_list_id FROM bookmark;");

			while (rs.next()) {
				Bookmark b = new Bookmark();
				b.setId(rs.getInt("bookmark_id"));
				b.setProfileId(rs.getInt("fk_profile_id"));
				b.setBookmarkListId(rs.getInt("fk_bookmark_list_id"));

				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>Bookmark</code> object with a specific ID in the DB.
	 * 
	 * @param id
	 *            The id by which to find the object
	 * @return <code>Bookmark</code> object with specified ID or null if not
	 *         found
	 */
	@Override
	public Bookmark findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT bookmark_id, "
					+ "fk_profile_id, fk_bookmark_list_id "
					+ "FROM bookmark WHERE bookmark_id=" + id);

			if (rs.next()) {
				Bookmark b = new Bookmark();
				b.setId(rs.getInt("bookmark_id"));
				b.setProfileId(rs.getInt("fk_profile_id"));
				b.setBookmarkListId(rs.getInt("fk_bookmark_list_id"));

				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Find all <code>Bookmark</code> objects which reference a specific profile
	 * in the DB.
	 * 
	 * @param profileId
	 *            The profile id by which to find the objects
	 * @return ArrayList of found <code>Bookmark</code> objects
	 */
	public ArrayList<Bookmark> findByProfileId(int profileId) {
		Connection con = DBConnection.connection();

		ArrayList<Bookmark> result = new ArrayList<Bookmark>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT bookmark_id, "
					+ "fk_profile_id, fk_bookmark_list_id FROM bookmark WHERE "
					+ "fk_profile_id=" + profileId);

			while (rs.next()) {
				Bookmark b = new Bookmark();
				b.setId(rs.getInt("bookmark_id"));
				b.setProfileId(rs.getInt("fk_profile_id"));
				b.setBookmarkListId(rs.getInt("fk_bookmark_list_id"));

				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find all <code>Bookmark</code> objects which belong to a specific
	 * bookmark list in the DB.
	 * 
	 * @param bookmarkListId
	 *            The bookmark list id by which to find the objects
	 * @return ArrayList of found <code>Bookmark</code> objects
	 */
	public ArrayList<Bookmark> findByBookmarkListId(int bookmarkListId) {
		Connection con = DBConnection.connection();

		ArrayList<Bookmark> result = new ArrayList<Bookmark>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT bookmark_id, "
					+ "fk_profile_id, fk_bookmark_list_id FROM bookmark WHERE "
					+ "fk_bookmark_list_id=" + bookmarkListId);

			while (rs.next()) {
				Bookmark b = new Bookmark();
				b.setId(rs.getInt("bookmark_id"));
				b.setProfileId(rs.getInt("fk_profile_id"));
				b.setBookmarkListId(rs.getInt("fk_bookmark_list_id"));

				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
