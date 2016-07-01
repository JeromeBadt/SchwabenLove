package de.hdm.grouptwo.server;

import javax.servlet.ServletException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.grouptwo.shared.AdministrationService;
import de.hdm.grouptwo.shared.ReportService;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;
import de.hdm.grouptwo.shared.report.UnviewedMatchesReport;

public class ReportServiceImpl extends RemoteServiceServlet implements
		ReportService { 
	
	private static final long serialVersionUID = 1L;
	private AdministrationService administrationService = null;
	
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
	public void init() throws ServletException {
		AdministrationServiceImpl admin = new AdministrationServiceImpl();
		admin.init();
		this.administrationService = admin;
	}
	
	/**
	 * Method to get the AdministrationService Object
	 * 
	 * @return AdministrationService
	 */
	protected AdministrationService getAdministrationService() {
		return this.administrationService;
	}
	
	/**
	 * Method to get all matches by searchprofile, sorted by similarity degree
	 * 
	 * @param Profile
	 * @return MatchesBySearchprofileReport
	 */
	public MatchesBySearchprofileReport getMatchesBySearchprofileReport(Profile p) {
		MatchesBySearchprofileReport report = new MatchesBySearchprofileReport();
		// Do something
		return report;
	}
	
	/**
	 * Method to get alle unviewed Matches
	 * 
	 * @param Profile
	 * @return
	 */
	public UnviewedMatchesReport getUnviewedMatches(Profile p) {
		UnviewedMatchesReport report = new UnviewedMatchesReport();
		// Do something
		return report;
	}
	
	
	

}
