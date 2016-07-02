package de.hdm.grouptwo.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.SearchProfile;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;
import de.hdm.grouptwo.shared.report.SimpleReport;
import de.hdm.grouptwo.shared.report.UnviewedMatchesReport;

public interface ReportServiceAsync {

	public void init(AsyncCallback<Void> asyncCallback);

	// void getAdministrationService(AsyncCallback<AdministrationService> asyncCallback);

//	void getMatchesBySearchprofileReport(LoginInfo loginInfo,
//			AsyncCallback<MatchesBySearchprofileReport> asyncCallback);

	public void getMatchesBySearchprofileReport(SearchProfile searchProfile,
			AsyncCallback<MatchesBySearchprofileReport> asyncCallback);
	
	public void getUnviewedMatches(Profile p,
			AsyncCallback<UnviewedMatchesReport> asyncCallback);

	public void testMethod(AsyncCallback<String> callback);

	public void setupAdministration(String email, AsyncCallback<Void> callback);

}
