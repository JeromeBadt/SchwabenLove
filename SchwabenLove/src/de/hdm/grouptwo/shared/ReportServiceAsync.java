package de.hdm.grouptwo.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.SearchProfile;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;
import de.hdm.grouptwo.shared.report.UnviewedMatchesReport;

public interface ReportServiceAsync {

	public void init(AsyncCallback<Void> asyncCallback);

	public void getMatchesBySearchprofileReport(SearchProfile searchProfile,
			AsyncCallback<MatchesBySearchprofileReport> asyncCallback);
	
	public void getUnviewedMatches(Profile p,
			AsyncCallback<UnviewedMatchesReport> asyncCallback);

	void setupAdministration(String email, AsyncCallback<Void> callback);

}
