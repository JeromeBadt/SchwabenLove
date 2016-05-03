package de.hdm.grouptwo.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.grouptwo.shared.bo.SimilarityDegree;

public class SimilarityDegreeMapper {
	private static SimilarityDegreeMapper similarityDegreeMapper = null;

	protected SimilarityDegreeMapper() {
	}

	public static SimilarityDegreeMapper similarityDegreeMapper() {
		if (similarityDegreeMapper == null) {
			similarityDegreeMapper = new SimilarityDegreeMapper();
		}

		return similarityDegreeMapper;
	}

	public SimilarityDegree insert(SimilarityDegree similarityDegree) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	
	    	ResultSet rs = stmt.executeQuery("SELECT MAX(similarity_degree_id) AS maxid FROM accounts");
	
	    	if (rs.next()) {
	    		similarityDegree.setId(rs.getInt("maxid") + 1);
	
		        stmt = con.createStatement();
		        stmt.executeUpdate("INSERT INTO similarity_degree "
		        + "(id, score, fk_profile_reference, fk_profile_comparison) "
		        + "VALUES (" + similarityDegree.getId() + "," + similarityDegree.getScore() ","
		        + similarityDegree.getReferenceProfileId() + "," + similarityDegree.getComparisonProfileId()
		        + ")");
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
	
		    return similarityDegree;
	}
	/*
	 * + insert( in similarityDegree: SimilarityDegree): SmilarityDegree
	 * 
	 * + update( in similarityDegree: SimilarityDegree): SmilarityDegree
	 * 
	 * + delete( in similarityDegree: SimilarityDegree): void
	 * 
	 * + findAll(): ArrayList<SimilarityDegree>
	 * 
	 * + findByProfile( in profile: Int): ArrayList<SmilarityDegree>
	 */
}
