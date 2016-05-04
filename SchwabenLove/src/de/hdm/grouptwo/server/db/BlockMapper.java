package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.Block;

/**
 * Implementation of a mapper class for Block. <br>
 * In the spirit of the MVC pattern mapper classes are used to move data between
 * objects and a database while keeping them independent of each other.
 * <p>
 * 
 * @author Thies, ManuelRuss, JeromeBadt
 */

public class BlockMapper {
	private static BlockMapper blockMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>
	 */
	protected BlockMapper() {
	}

	/**
	 * BlockMapper should be instantiated by this method to ensure that only a
	 * single instance exists.
	 * <p>
	 * 
	 * @return The <code>BlockMapper</code> instance.
	 */
	public static BlockMapper blockMapper() {
		if (blockMapper == null) {
			blockMapper = new BlockMapper();
		}

		return blockMapper;
	}

	/**
	 * Insert a <code>Block</code> object into the DB
	 * 
	 * <p>
	 * TODO: else block for inserting first object into DB?
	 * 
	 * @param i
	 *            The <code>Block</code> object to be inserted
	 */
	public void insert(Block b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt.executeQuery("SELECT MAX(block_id) AS maxid"
					+ "FROM block ");

			if (rs.next()) {
				// Set id to max + 1
				b.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO block (block_id, "
						+ "fk_profile_blocker, fk_profile_blocked) VALUES ("
						+ b.getId() + "," + b.getBlockerProfileId() + ","
						+ b.getBlockedProfileId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>Block</code> object from the DB
	 * 
	 * @param i
	 *            The <code>Block</code> object to be deleted
	 */
	public void delete(Block b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM block " + "WHERE block_id="
					+ b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Block</code> objects in the DB
	 * 
	 * @return result ArrayList of all <code>Block</code> objects
	 */
	public ArrayList<Block> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Block> result = new ArrayList<Block>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT block_id, "
					+ "fk_profile_blocker, fk_profile_blocked FROM block");

			while (rs.next()) {
				Block b = new Block();
				b.setId(rs.getInt("block_id"));
				b.setBlockerProfileId(rs.getInt("fk_profile_blocker"));
				b.setBlockedProfileId(rs.getInt("fk_profile_blocked"));

				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find all <code>Block</code> objects of a specific blocker profile in the
	 * DB
	 * 
	 * @return result ArrayList of found <code>Information</code> objects
	 */
	public ArrayList<Block> findByBlockerProfileId(int blockerProfileId) {
		Connection con = DBConnection.connection();

		ArrayList<Block> result = new ArrayList<Block>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT block_id, "
					+ "fk_profile_blocker, fk_profile_blocked FROM block "
					+ "WHERE fk_profile_blocker=" + blockerProfileId);

			while (rs.next()) {
				Block b = new Block();
				b.setId(rs.getInt("block_id"));
				b.setBlockerProfileId(rs.getInt("fk_profile_blocker"));
				b.setBlockedProfileId(rs.getInt("fk_profile_blocked"));

				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
