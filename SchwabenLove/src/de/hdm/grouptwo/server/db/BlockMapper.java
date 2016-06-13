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
 * TODO: DeleteBlocksOf
 * 
 * @author Thies, ManuelRuss, JeromeBadt
 */

public class BlockMapper implements DataMapper<Block> {
	private static BlockMapper blockMapper = null;

	/**
	 * Private constructor to prevent initialization with <code>new</code>.
	 */
	protected BlockMapper() {
	}

	/**
	 * BlockMapper should be instantiated by this method to ensure that only a
	 * single instance exists.
	 * 
	 * @return The <code>BlockMapper</code> instance
	 */
	public static BlockMapper blockMapper() {
		if (blockMapper == null) {
			blockMapper = new BlockMapper();
		}

		return blockMapper;
	}

	/**
	 * Insert a <code>Block</code> object into the DB.
	 * 
	 * @param b
	 *            The <code>Block</code> object to be inserted
	 * @return The inserted Block (returned because it gets an assigned id)
	 */
	@Override
	public Block insert(Block b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Query DB for current max id
			ResultSet rs = stmt.executeQuery("SELECT MAX(block_id) AS maxid "
					+ "FROM block");

			if (rs.next()) {
				// Set id to max + 1
				b.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO block (block_id, "
						+ "fk_blocker_profile_id, fk_blocked_profile_id) VALUES ("
						+ b.getId() + "," + b.getBlockerProfileId() + ","
						+ b.getBlockedProfileId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}

	/**
	 * Update a <code>Block</code> object in the DB.
	 * 
	 * @param b
	 *            The <code>Block</code> object to be updated
	 */
	@Override
	public void update(Block b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE block SET fk_blocker_profile_id="
					+ b.getBlockerProfileId() + ",fk_blocked_profile_id="
					+ b.getBlockedProfileId() + " WHERE block_id=" + b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete a <code>Block</code> object from the DB.
	 * 
	 * @param b
	 *            The <code>Block</code> object to be deleted
	 */
	@Override
	public void delete(Block b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM block WHERE block_id=" + b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all <code>Block</code> objects in the DB.
	 * 
	 * @return ArrayList of all <code>Block</code> objects
	 */
	@Override
	public ArrayList<Block> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Block> result = new ArrayList<Block>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT block_id, "
					+ "fk_blocker_profile_id, fk_blocked_profile_id "
					+ "FROM block");

			while (rs.next()) {
				Block b = new Block();
				b.setId(rs.getInt("block_id"));
				b.setBlockerProfileId(rs.getInt("fk_blocker_profile_id"));
				b.setBlockedProfileId(rs.getInt("fk_blocked_profile_id"));

				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find <code>Block</code> object with a specific ID in the DB.
	 * 
	 * @return <code>Block</code> object with specified ID or null if not found
	 */
	@Override
	public Block findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT block_id, "
					+ "fk_blocker_profile_id, fk_blocked_profile_id "
					+ "FROM block WHERE block_id=" + id);

			if (rs.next()) {
				Block b = new Block();
				b.setId(rs.getInt("block_id"));
				b.setBlockerProfileId(rs.getInt("fk_blocker_profile_id"));
				b.setBlockedProfileId(rs.getInt("fk_blocked_profile_id"));

				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Find all <code>Block</code> objects of a specific blocker profile in the
	 * DB.
	 * 
	 * @return ArrayList of found <code>Block</code> objects
	 */
	public ArrayList<Block> findByBlockerProfileId(int blockerProfileId) {
		Connection con = DBConnection.connection();

		ArrayList<Block> result = new ArrayList<Block>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT block_id, "
							+ "fk_blocker_profile_id, fk_blocked_profile_id "
							+ "FROM block WHERE fk_profile_blocker="
							+ blockerProfileId);

			while (rs.next()) {
				Block b = new Block();
				b.setId(rs.getInt("block_id"));
				b.setBlockerProfileId(rs.getInt("fk_blocker_profile_id"));
				b.setBlockedProfileId(rs.getInt("fk_blocked_profile_id"));

				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Find all <code>Block</code> objects of a specific blocked profile in the
	 * DB.
	 * 
	 * @return ArrayList of found <code>Block</code> objects
	 */
	public ArrayList<Block> findByBlockedProfileId(int blockedProfileId) {
		Connection con = DBConnection.connection();

		ArrayList<Block> result = new ArrayList<Block>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT block_id, "
							+ "fk_blocker_profile_id, fk_blocked_profile_id "
							+ "FROM block WHERE fk_profile_blocked="
							+ blockedProfileId);

			while (rs.next()) {
				Block b = new Block();
				b.setId(rs.getInt("block_id"));
				b.setBlockerProfileId(rs.getInt("fk_blocker_profile_id"));
				b.setBlockedProfileId(rs.getInt("fk_blocked_profile_id"));

				result.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
