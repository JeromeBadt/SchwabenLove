package de.hdm.grouptwo.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;
import de.hdm.grouptwo.shared.report.UnviewedMatchesReport;

public interface ReportServiceAsync {

	void init(AsyncCallback<Void> callback);

	// void getAdministrationService(AsyncCallback<AdministrationService> callback);

	void getMatchesBySearchprofileReport(Profile p,
			AsyncCallback<MatchesBySearchprofileReport> callback);

	void getUnviewedMatches(Profile p,
			AsyncCallback<UnviewedMatchesReport> callback);

}
