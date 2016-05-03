package de.hdm.grouptwo.server.db;

import java.sql.*;

import de.hdm.grouptwo.shared.bo.*;

/**
 * Mapper class to persist Profile objects in database
 * 
 * @author DucNguyen
 *
 */
public class ProfileMapper extends DBConnection{
	
	private static ProfileMapper profileMapper = null;
	protected ProfileMapper(){}	
	
		
	public static ProfileMapper profileMapper() {
	    if (profileMapper == null) {
	      profileMapper = new ProfileMapper();
	    }
	    return profileMapper;
	  }

	public Profile insert (Profile p){
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select MAX(profile_id) AS maxid " + "FROM Profile ");
			
			if (rs.next()) {
			p.setId(rs.getInt("") +1);
			
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO Profile (profile_id, email, first_name, last_name, gender,"
					+ " birthdate, location, height, physique, hair_color, smoker, education, religion) " 
					+ "VALUES (" + p.getId()+ ", "+ p.getEmail()+","+ p.getFirstName()+","+ p.getLastName()+","
					+ p.getGender()+","+ p.getBirthdate()+","+ p.getLocation()+","+ p.getHeight()+","+ p.getHairColor()+"," 
					+ p.getSmoker()+","+ p.getEducation()+"," +p.getReligion()+"')");				
		}
	}
			catch (SQLException e){
				e.printStackTrace();
			}
		return p;
	}
	
	public Profile update (Profile p){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE Profile " + "SET email =\"" + p.getEmail()+"\", first_name =\"" + p.getFirstName()+"\""
					+ ", last_name=\"" + p.getLastName()+"\", gender=\""+ p.getGender()+"\", birthdate=\"" + p.getBirthdate()+"\""
					+ ", location=\"" + p.getLocation()+"\", height=\""+ p.getHeight()+"\" ,physique=\"" + p.getPysique()+"\""
					+ ", hair_color=\"" +p.getHairColor()+"\", smoker=\"" + p.getSmoker()+"\" , education=\"" +p.getEducation()+"\""
					+ ", religion=\"" + p.getReligion()+"\"" + " WHERE profile_id = \""+ p.getId()); 
		}
		catch (SQLException e){
        	e.printStackTrace();
        }
		return p;
	}
	
	
	public void delete (Profile p){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Profile " + "WHERE profile_id=" + p.getId());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Profile findAll (Profile p){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("SELECT * "+" FROM Profile");
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return p;
	}
	
	public Profile findByEmail (Profile p){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("Select * " + " FROM Profile "+" WHERE email=" + p.getEmail());
		}
		catch (SQLException e){
			e.printStackTrace();
		}
			
		return p;
	}
		
}
