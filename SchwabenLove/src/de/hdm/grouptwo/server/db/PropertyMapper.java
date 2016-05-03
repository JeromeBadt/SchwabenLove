package de.hdm.grouptwo.server.db;

import java.sql.*;
import java.util.ArrayList;
import de.hdm.grouptwo.shared.bo.*;


/**
 * Mapper class to persist property objects in database 
 * 
 * @author DenisThierry
 */
public class PropertyMapper {

	private static PropertyMapper propertyMapper = null;
	
	protected PropertyMapper() {
	}
		
	public static PropertyMapper propertyMapper() {
		if (propertyMapper == null) {
			propertyMapper = new PropertyMapper();
		}

		return propertyMapper;
	}
	
	public ArrayList<Property> findAll() {
	Connection con = DBConnection.connection();
	
	ArrayList<Property> result = new ArrayList<Property>();
	
	  try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT property_id, explanation "
          + "FROM property");
	 
	      while (rs.next()) {
	          Property p = new Property();
	          p.setId(rs.getInt("property_id"));
	          p.setExplanation(rs.getString("explanation"));

	        result.add(p);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	  
	    return result;
	  }	
	
	 public Property insert(Property p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT MAX(property_id) AS maxid "
          + "FROM Property ");


		      if (rs.next()) {

		        p.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();


		        stmt.executeUpdate("INSERT INTO Property (property_id, explanation) "
		            + "VALUES ('" + p.getId() + "','" + p.getExplanation() + "')");
		       
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return p;
		  }

	 public Property update(Property p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE Property " + "SET property_id=\""
		              + p.getId() + "\" ,explanation=\"" + p.getExplanation() 
		          + "WHERE property_id=" + p.getId());

		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return p;
		  }
	
	  public void delete(Property p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM Property " + "WHERE property_id=" + p.getId());
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
}