package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.*;

/**
 * Mapper class to persist description Objects in database
 * @author DenisThierry
 */

public class DescriptionMapper {
	
	private static DescriptionMapper descriptionMapper = null;
	protected DescriptionMapper() {
	}
	public static DescriptionMapper descriptionMapper() {
		if (descriptionMapper == null) {
			descriptionMapper = new DescriptionMapper();
		}
		return descriptionMapper;
	}
	public ArrayList<Description> findAll() {
		Connection con = DBConnection.connection();
		ArrayList<Description> result = new ArrayList<Description>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT property_id "
			+ "FROM Description");
			while (rs.next()) {
				Description d = new Description();
				d.setId(rs.getInt("property_id"));

			result.add(d);
			}
		}
	catch (SQLException e) {
		e.printStackTrace();
	}
		return result;
	}
		
	public Description insert(Description d) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(property_id) AS maxid "
			          + "FROM Description ");
			if (rs.next()) {
				d.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO Description (property_id) "
			            + "VALUES ('" + d.getId() + "')");
			}
		}
		catch (SQLException e) {
		      e.printStackTrace();
	}
		return d;
}
	public Description update(Description d) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("UPDATE Description " + "SET property_id=\"" + d.getId() +  
		           "WHERE property_id=" + d.getId());
		  }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return d;
	}
	public void delete(Description d) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM Description " + "WHERE property_id=" + d.getId());
			}
	    catch (SQLException e) {
	      e.printStackTrace();
	      }
	  }
	
}
