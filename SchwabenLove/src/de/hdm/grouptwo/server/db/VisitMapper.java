package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.grouptwo.shared.bo.Visit;

/**
 * Mapper class to persist visit objects into database
 * 
 * @author manuelruss
 *
 */

public class VisitMapper {

	private static VisitMapper visitMapper = null;
	
	protected VisitMapper() {
		
	}

	  public static VisitMapper visitMapper() {
		    if (visitMapper == null) {
		      visitMapper = new VisitMapper();
		    }

		    return visitMapper;
		  }
	  
	  
	  public Visit insert(Visit c) {
			// Establish database connection
		    Connection con = DBConnection.connection();

		    try {
				// New SQL statement
		      Statement stmt = con.createStatement();

				// Execute SQL query
		      ResultSet rs = stmt.executeQuery("SELECT MAX(visit_id) AS maxid"
		          + "FROM visit ");

		      if (rs.next()) {
				// Increase id by +1
		        c.setId(rs.getInt("maxid") + 1);
		        // New SQL statement
		        stmt = con.createStatement();
		        // Insert into DB
		        stmt.executeUpdate("INSERT INTO visit (visit_id) "
		            + "VALUES (" + c.getId() + "')");
		      }
		    }
		    // Error handling
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return c;
		  }
	  
	  
	  public Visit update(Visit v) {
		  // Establish DB Connection
		    Connection con = DBConnection.connection();

		    try {
		    	// New SQL Statement
		      Statement stmt = con.createStatement();
		      // Execute SQL Statement
		      stmt.executeUpdate("UPDATE visit " + v.getId()
		          + "\" " + "WHERE id=" + v.getId());

		    }
		    // Error handling
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return v;
		  }

	  
	  
	  public void delete(Visit c) {
			// Establish DB Connection
		    Connection con = DBConnection.connection();

		    try {
				// New SQL statement
		      Statement stmt = con.createStatement();
				// Execute SQL query
		      stmt.executeUpdate("DELETE FROM visit " + "WHERE visit_id=" + c.getId());
		    }
			// Error handling
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  
	  
	  public Vector<Visit> findAll() {
			// Establish database connection
		    Connection con = DBConnection.connection();

		    Vector<Visit> result = new Vector<Visit>();

		    try {
				// New SQL statement
		      Statement stmt = con.createStatement();
				// Execute SQL query
		      ResultSet rs = stmt
		          .executeQuery("SELECT visit_id FROM visit ");

		      while (rs.next()) {
					// New visit Object
		        Visit v = new Visit();
				// Set properties of visit
		        v.setId(rs.getInt("visit_id"));		       

		        result.addElement(v);
		      }
		    }
			// Error handling
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }
			// Return null if nothing found
		    return result;
		  }
	  
	  
		public Visit findByProfile(int profileId) {
			// Establish database connection
			Connection con = DBConnection.connection();
			
			try {
				// New SQL statement
				Statement stmt = con.createStatement();
				// Execute SQL query
				ResultSet rs = stmt.executeQuery("SELECT *" +
						"FROM block WHERE profile_id = '" + profileId + "'");
						while(rs.next()) {
							// Create new visit Object
							Visit vi = new Visit();
							// Set properties of block
							vi.setId(rs.getInt("block_id"));
							return vi;
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

	

