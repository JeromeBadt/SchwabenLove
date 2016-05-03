package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.grouptwo.shared.bo.Block;

/**
 * Mapper class to persist block objects into database 
 * 
 * @author manuelruss
 *
 */

public class BlockMapper {
	
	private static BlockMapper blockMapper = null;
	
	protected BlockMapper() {
		
	}

	  public static BlockMapper blockMapper() {
		    if (blockMapper == null) {
		      blockMapper = new BlockMapper();
		    }

		    return blockMapper;
		  }
	  
	  
	  public Block insert(Block c) {
			// Establish database connection
		    Connection con = DBConnection.connection();

		    try {
				// New SQL statement
		      Statement stmt = con.createStatement();

				// Execute SQL query
		      ResultSet rs = stmt.executeQuery("SELECT MAX(block_id) AS maxid"
		          + "FROM block ");

		      if (rs.next()) {
				// Increase id by +1
		        c.setId(rs.getInt("maxid") + 1);
		        // New SQL statement
		        stmt = con.createStatement();
		        // Insert into DB
		        stmt.executeUpdate("INSERT INTO block (block_id) "
		            + "VALUES (" + c.getId() + "')");
		      }
		    }
		    // Error handling
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return c;
		  }

	  
	  
	  public void delete(Block c) {
			// Establish database connection
		    Connection con = DBConnection.connection();

		    try {
				// New SQL statement
		      Statement stmt = con.createStatement();
				// Execute SQL query
		      stmt.executeUpdate("DELETE FROM block " + "WHERE block_id=" + c.getId());
		    }
			// Error handling
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  
	  
	  public Vector<Block> findAll() {
			// Establish database connection
		    Connection con = DBConnection.connection();

		    Vector<Block> result = new Vector<Block>();

		    try {
				// New SQL statement
		      Statement stmt = con.createStatement();
				// Execute SQL query
		      ResultSet rs = stmt
		          .executeQuery("SELECT block_id FROM block");

		      while (rs.next()) {
					// New block Object
		        Block b = new Block();
				// Set properties of block
		        b.setId(rs.getInt("block_id"));		       

		        result.add(b);
		      }
		    }
			// Error handling
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
			// Return null if nothing found
		    return result;
		  }
	  
	  
		public Block findByProfile(int profileId) {
			// Establish database connection
			Connection con = DBConnection.connection();
			
			try {
				// New SQL statement
				Statement stmt = con.createStatement();
				// Execute SQL query
				ResultSet rs = stmt.executeQuery("SELECT *" +
						"FROM block WHERE profile_id = '" + profileId + "'");
						while(rs.next()) {
							// Create new block Object
							Block bl = new Block();
							// Set properties of block
							bl.setId(rs.getInt("block_id"));
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
