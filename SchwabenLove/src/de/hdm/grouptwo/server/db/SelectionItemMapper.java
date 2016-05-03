package de.hdm.grouptwo.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.grouptwo.shared.bo.*;

/**
 * Mapper class to persist selection items Objects in database
 * @author DenisThierry
 */

public class SelectionItemMapper {
	private static SelectionItemMapper selectionItemMapper = null;
	protected SelectionItemMapper() {
	}
	public static SelectionItemMapper selectionItemMapper() {
		if (selectionItemMapper == null) {
			selectionItemMapper = new SelectionItemMapper();
		}
		return selectionItemMapper;
	}
	public ArrayList<SelectionItem> findAll() {
		Connection con = DBConnection.connection();
		ArrayList<SelectionItem> result = new ArrayList<SelectionItem>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT selection_item_id, name, fk_selection "
			+ "FROM SelectionItem");
			while (rs.next()) {
				SelectionItem si = new SelectionItem();
				si.setId(rs.getInt("selection_item_id"));
				si.setName(rs.getString("name"));
				si.setProperty_id(rs.getInt("property_id"));
				
			result.add(si);
			}
		}
	catch (SQLException e) {
		e.printStackTrace();
	}
		return result;
	}

	public ArrayList<SelectionItem> findByProperty(int property_id) {
		Connection con = DBConnection.connection();
		ArrayList<SelectionItem> result = new ArrayList<SelectionItem>();
		try {
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT property_id, name, fk_selection FROM SelectionItem "
		    	+	"WHERE fk_selection= '" + property_id + "'");
		      
		      while (rs.next()) {
		    	  SelectionItem si = new SelectionItem();
		    	  si.setId(rs.getInt("selection_item_id"));
		    	  si.setName(rs.getString("name"));
		    	  si.setProperty_id(rs.getInt("fk_selection"));
		      result.add(si);
		      }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
		
	public SelectionItem insert(SelectionItem si) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(selection_item_id) AS maxid "
			          + "FROM SelectionItem ");
			if (rs.next()) {
				si.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO SelectionItem (selection_item_id, name, fk_selection) "
			            + "VALUES ('" + si.getId() + "','" + si.getName() + "','" + si.getProperty_id() + "')");
			}
		}
		catch (SQLException e) {
		      e.printStackTrace();
	}
		return si;
}
	public SelectionItem update(SelectionItem si) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("UPDATE SelectionItem " + "SET selection_item_id=\"" + si.getId() + "\", name=\"" + si.getName() + "\", fk_property=\""
		               + si.getProperty_id() + 
		           "WHERE selection_item_id=" + si.getId());
		  }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return si;
	}
	public void delete(SelectionItem si) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM SelectionItem " + "WHERE selection_item_id=" + si.getId());
			}
	    catch (SQLException e) {
	      e.printStackTrace();
	      }
	  }
}