package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.grouptwo.shared.AdministrationService;
import de.hdm.grouptwo.shared.AdministrationServiceAsync;
import de.hdm.grouptwo.shared.LoginService;
import de.hdm.grouptwo.shared.LoginServiceAsync;
import de.hdm.grouptwo.shared.ReportService;
import de.hdm.grouptwo.shared.ReportServiceAsync;
import de.hdm.grouptwo.shared.bo.Description;
import de.hdm.grouptwo.shared.bo.Property;
import de.hdm.grouptwo.shared.bo.Selection;

/**
 * Class for relevant client-side settings and services.
 * 
 * @author Thies, JeromeBadt
 * 
 */

public class ClientsideSettings {
	private static AdministrationServiceAsync administrationService = null;
	private static LoginServiceAsync loginService = null;
	private static ReportServiceAsync reportService = null;
	private static final Logger log = Logger
			.getLogger("SchwabenLove Web Client");

	private static ArrayList<Property> properties = null;
	private static ArrayList<Description> descriptions = null;
	private static ArrayList<Selection> selections = null;

	/**
	 * Private constructor to prevent initialization.
	 */
	private ClientsideSettings() {
	}

	public static void setDescriptions(ArrayList<Description> d) {
		descriptions = d;
	}

	public static void setSelections(ArrayList<Selection> s) {
		selections = s;
	}

	public static ArrayList<Property> getProperties() {
		if (properties == null) {
			properties = new ArrayList<Property>();
			properties.addAll(descriptions);
			properties.addAll(selections);
		}

		return properties;
	}

	/**
	 * Creates and returns the application wide LoginService. Ensures that only
	 * a single instance exists.
	 * 
	 * @return The LoginService
	 */
	public static LoginServiceAsync getLoginService() {
		if (loginService == null) {
			loginService = GWT.create(LoginService.class);
		}

		return loginService;
	}

	/**
	 * Creates and returns the application wide AdministrationService. Ensures
	 * that only a single instance exists.
	 * 
	 * @return AdministrationService
	 */
	public static AdministrationServiceAsync getAdministrationService() {
		if (administrationService == null) {
			administrationService = GWT.create(AdministrationService.class);
		}

		return administrationService;
	}

	/**
	 * Creates and returns ReprotService. Ensures that only a single instance
	 * exists
	 * 
	 * @return ReportService
	 */
	public static ReportServiceAsync getReportService() {
		if (reportService == null) {
			reportService = GWT.create(ReportService.class);
		}

		reportService.init(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub

			}

		});

		return reportService;
	}

	/**
	 * Get the application wide logger. The log is created on the client-side.
	 * 
	 * @return The logger instance for the server-side
	 */
	public static Logger getLogger() {
		return log;
	}
}
