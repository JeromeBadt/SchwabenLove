package de.hdm.grouptwo.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;
import de.hdm.grouptwo.shared.report.UnviewedMatchesReport;

@RemoteServiceRelativePath("report")
public interface ReportService extends RemoteService {

	public void init();
	
	// public AdministrationService getAdministrationService();
	
	public MatchesBySearchprofileReport getMatchesBySearchprofileReport(Profile p);
	
	public UnviewedMatchesReport getUnviewedMatches(Profile p);
}
