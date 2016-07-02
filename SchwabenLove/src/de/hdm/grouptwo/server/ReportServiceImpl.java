package de.hdm.grouptwo.server;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.grouptwo.client.ClientsideSettings;
import de.hdm.grouptwo.shared.AdministrationService;
import de.hdm.grouptwo.shared.ReportService;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.SearchProfile;
import de.hdm.grouptwo.shared.report.Column;
import de.hdm.grouptwo.shared.report.CompositeParagraph;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;
import de.hdm.grouptwo.shared.report.Row;
import de.hdm.grouptwo.shared.report.SimpleParagraph;
import de.hdm.grouptwo.shared.report.UnviewedMatchesReport;

public class ReportServiceImpl extends RemoteServiceServlet implements
		ReportService { 
	
	private static final long serialVersionUID = 1L;
	private AdministrationService administrationService = null;
	private Profile user = null;
	
	
	/**
	 * No-Argument Constructor
	 * 
	 * @throws IllegalArgumentException
	 */
	public ReportServiceImpl() throws IllegalArgumentException {
		
	  }
	
	/**
	 * Initialization Method
	 */
	public void init() {
		AdministrationServiceImpl admin = new AdministrationServiceImpl();
		this.administrationService = admin;
	}
	
	/**
	 * Method to get the <code>AdministrationService</code> Object
	 * 
	 * @return AdministrationService
	 */
	public AdministrationService getAdministrationService() {
		return this.administrationService;
	}
	
	/**
	 * Method to get a <code>Report</code> Object of all matches by searchprofile, 
	 * sorted by similarity degree
	 * 
	 * @param Profile
	 * @return MatchesBySearchprofileReport
	 */
	//public MatchesBySearchprofileReport getMatchesBySearchprofileReport(LoginInfo loginInfo) {
	public MatchesBySearchprofileReport getMatchesBySearchprofileReport(SearchProfile searchProfile) {
		MatchesBySearchprofileReport report = new MatchesBySearchprofileReport();
	
		// Set Title
		report.setTitle("Matches for Searchprofile " + searchProfile.getName());
		report.setCreated(new Date());
		
		CompositeParagraph header = new CompositeParagraph();
		
		// Set User Name
		header.addSubParagraph(new SimpleParagraph("Report for User: " + user.getFirstName() + " " + user.getLastName()));	
		report.setHeaderData(header);
		
		// New Row for headline
		Row row = new Row();
		row.addColumn(new Column("ID"));
		row.addColumn(new Column("Email"));
		row.addColumn(new Column("First Name"));
		row.addColumn(new Column("Last Name"));
		row.addColumn(new Column("Gender"));
		row.addColumn(new Column("Birthdate"));
		row.addColumn(new Column("Location"));
		row.addColumn(new Column("Height"));
		row.addColumn(new Column("Physique"));
		row.addColumn(new Column("Hair Color"));
		row.addColumn(new Column("Smoker"));
		row.addColumn(new Column("Education"));
		row.addColumn(new Column("Profession"));
		row.addColumn(new Column("Religion"));
		
		// Add headline row
		report.addRow(row);
		
		// Get Matches by SearchProfile
		ArrayList<Profile> matches = this.administrationService.getMatchesBySearchProfile(searchProfile);
			
		for (Profile match : matches) {
			Row matchRow = new Row();
			matchRow.addColumn(new Column(String.valueOf(match.getId())));
			matchRow.addColumn(new Column(String.valueOf(match.getEmail())));
			matchRow.addColumn(new Column(String.valueOf(match.getFirstName())));
			matchRow.addColumn(new Column(String.valueOf(match.getLastName())));
			matchRow.addColumn(new Column(String.valueOf(match.getGender())));
			matchRow.addColumn(new Column(String.valueOf(match.getBirthdate())));
			matchRow.addColumn(new Column(String.valueOf(match.getLocation())));
			matchRow.addColumn(new Column(String.valueOf(match.getHeight())));
			matchRow.addColumn(new Column(String.valueOf(match.getPhysique())));
			matchRow.addColumn(new Column(String.valueOf(match.getHairColor())));
			matchRow.addColumn(new Column(String.valueOf(match.getSmoker())));
			matchRow.addColumn(new Column(String.valueOf(match.getEducation())));
			matchRow.addColumn(new Column(String.valueOf(match.getProfession())));
			matchRow.addColumn(new Column(String.valueOf(match.getReligion())));
			report.addRow(matchRow);
		}
	
		return report;
	}
	
	public String testMethod() {
		return "RPC worked";
	}
	
	/**
	 * Method to get a <code>Report</code> Object of all unviewed Matches
	 * 
	 * @param Profile
	 * @return
	 */
	public UnviewedMatchesReport getUnviewedMatches(Profile p) {
		UnviewedMatchesReport report = new UnviewedMatchesReport();
		
		// Set Title
		report.setTitle("Unviewed Matches");
		report.setCreated(new Date());
		
		CompositeParagraph header = new CompositeParagraph();
		
		// Set User Name
		header.addSubParagraph(new SimpleParagraph("Report for User: " + user.getFirstName() + " " + user.getLastName()));
		
		// New Row for headline
		Row row = new Row();
		row.addColumn(new Column("ID"));
		row.addColumn(new Column("Email"));
		row.addColumn(new Column("First Name"));
		row.addColumn(new Column("Last Name"));
		row.addColumn(new Column("Gender"));
		row.addColumn(new Column("Birthdate"));
		row.addColumn(new Column("Location"));
		row.addColumn(new Column("Height"));
		row.addColumn(new Column("Physique"));
		row.addColumn(new Column("Hair Color"));
		row.addColumn(new Column("Smoker"));
		row.addColumn(new Column("Education"));
		row.addColumn(new Column("Profession"));
		row.addColumn(new Column("Religion"));
		
		// Add headline row
		report.addRow(row);
		
		// Get unviewed matches
		// ArrayList<Profile> matches = this.administrationService.
		return report;
	}
	
	/**
	 * Method to set the correct user for <code>AdministrationService</code> Object
	 * 
	 * @param Email
	 */
	public void setupAdministration(String email) {
		this.administrationService.setProfile(email);
		this.user = this.administrationService.getProfile();
	}
	
	
	

}
