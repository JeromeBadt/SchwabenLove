package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.SelectionItem;

/**
 * Implementation of a mapper class for SelectionItem. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * 
 * @author Thies, DenisThierry, JeromeBadt
 */

public class SelectionItemMapper implements DataMapper<SelectionItem> {
	private static SelectionItemMapper selectionItemMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	private SelectionItemMapper() {
	}

	/**
	 * SelectionItemMapper should be instantiated by this method to ensure that
	 * only a single instance exists.
	 * 
	 * @return The <code>SelectionItemMapper</code> instance
	 */
	public static SelectionItemMapper selectionItemMapper() {
		if (selectionItemMapper == null) {
			selectionItemMapper = new SelectionItemMapper();
		}

		return selectionItemMapper;
	}

	/**
	 * Insert a <code>SelectionItem</code> object into the DB.
	 * 
	 * <p>
	 * TODO: else block for inserting first object into DB?
	 * 
	 * @param si
	 *            The <code>SelectionItem</code> object to be inserted
	 */
	@Override
	public void insert(SelectionItem si) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt
					.executeQuery("SELECT MAX(selection_item_id) AS maxid "
							+ "FROM selection_item ");

			if (rs.next()) {
				// Set id to max + 1
				si.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO selection_item ("
						+ "selection_item_id, name, fk_selection_id) VALUES ("
						+ si.getId() + ",'" + si.getName() + "',"
						+ si.getSelectionId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update a <code>SelectionItem</code> object in the DB.
	 * 
	 * @param si
	 *            The <code>SelectionItem</code> object to be updated
	 */
	@Override
	public void update(SelectionItem si) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("UPDATE selection_item SET name='" + si.getName()
					+ "',fk_selection_id=" + si.getSelectionId()
					+ " WHERE selection_item_id=" + si.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>SelectionItem</code> object from the DB.
	 * 
	 * @param si
	 *            The <code>SelectionItem</code> object to be deleted
	 */
	@Override
	public void delete(SelectionItem si) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM selection_item "
					+ "WHERE selection_item_id=" + si.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>SelectionItem</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>SelectionItem</code> objects
	 */
	@Override
	public ArrayList<SelectionItem> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<SelectionItem> result = new ArrayList<SelectionItem>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT selection_item_id, name, "
					+ "fk_selection_id FROM selection_item");

			while (rs.next()) {
				SelectionItem si = new SelectionItem();
				si.setId(rs.getInt("selection_item_id"));
				si.setName(rs.getString("name"));
				si.setSelectionId(rs.getInt("fk_selection_id"));

				result.add(si);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>SelectionItem</code> object with a specific ID in the DB.
	 * 
	 * @param id
	 *            The id by which to find the object
	 * @return <code>SelectionItem</code> object with specified ID or null if
	 *         not found
	 */
	@Override
	public SelectionItem findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT selection_item_id, name, "
					+ "fk_selection_id FROM selection_item WHERE "
					+ "selection_item_id=" + id);

			if (rs.next()) {
				SelectionItem si = new SelectionItem();
				si.setId(rs.getInt("selection_item_id"));
				si.setName(rs.getString("name"));
				si.setSelectionId(rs.getInt("fk_selection_id"));

				return si;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Find all <code>SelectionItem</code> objects with a specific visitor
	 * profile in the DB.
	 * 
	 * @param selectionId
	 *            The selection id by which to find the objects
	 * @return ArrayList of found <code>SelectionItem</code> objects
	 */
	public ArrayList<SelectionItem> findBySelection(int selectionId) {
		Connection con = DBConnection.connection();
		ArrayList<SelectionItem> result = new ArrayList<SelectionItem>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT selection_item_id, name, "
					+ "fk_selection_id FROM selection_item WHERE "
					+ "fk_selection_id=" + selectionId);

			while (rs.next()) {
				SelectionItem si = new SelectionItem();
				si.setId(rs.getInt("selection_item_id"));
				si.setName(rs.getString("name"));
				si.setSelectionId(rs.getInt("fk_selection_id"));
				result.add(si);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}