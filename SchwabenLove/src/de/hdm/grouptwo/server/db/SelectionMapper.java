package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.*;

/**
 * Mapper class to persist selection Objects in database
 * @author DenisThierry
 */

public class SelectionMapper {

	private static SelectionMapper selectionMapper = null;
	protected SelectionMapper() {
	}
	public static SelectionMapper selectionMapper() {
		if (selectionMapper == null) {
			selectionMapper = new SelectionMapper();
		}
		return selectionMapper;
	}
	public ArrayList<Selection> findAll() {
		Connection con = DBConnection.connection();
		ArrayList<Selection> result = new ArrayList<Selection>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT property_id "
			+ "FROM Selection");
			while (rs.next()) {
				Selection s = new Selection();
				s.setId(rs.getInt("property_id"));
			
				
			result.add(s);
			}
		}
	catch (SQLException e) {
		e.printStackTrace();
	}
		return result;
	}

	public ArrayList<Selection> findBySelectionItem(int selection_item_id) {
		Connection con = DBConnection.connection();
		ArrayList<Selection> result = new ArrayList<Selection>();
		try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT property_id FROM Selection "
		    	+	"WHERE property_id= '" + selection_item_id + "'");
		      
		      while (rs.next()) {
		    	  Selection s = new Selection();
		    	  s.setId(rs.getInt("property_id"));
		      result.add(s);
		      }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Selection insert(Selection s) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(property_id) AS maxid "
			          + "FROM Selection ");
			if (rs.next()) {
				s.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO Selection (property_id) "
			            + "VALUES ('" + s.getId() + "')");
			}
		}
		catch (SQLException e) {
		      e.printStackTrace();
	}
		return s;
}
	public Selection update(Selection s) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("UPDATE Selection " + "SET property_id=\"" + s.getId() +  
		           "WHERE selection_item_id=" + s.getId());
		  }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return s;
	}
	public void delete(Selection s) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM Selection " + "WHERE property_id=" + s.getId());
			}
	    catch (SQLException e) {
	      e.printStackTrace();
	      }
	  }
}
