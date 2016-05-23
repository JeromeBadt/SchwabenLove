package de.hdm.grouptwo.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;

import de.hdm.grouptwo.shared.AdministrationService;
import de.hdm.grouptwo.shared.AdministrationServiceAsync;
import de.hdm.grouptwo.shared.LoginService;
import de.hdm.grouptwo.shared.LoginServiceAsync;

/**
 * Class for relevant client-side settings and services.
 * 
 * @author Thies, JeromeBadt
 * 
 */

public class ClientsideSettings {
	private static AdministrationServiceAsync administrationService = null;
	private static LoginServiceAsync loginService = null;
	// private static ReportGeneratorAsync reportGenerator = null;
	private static final Logger log = Logger
			.getLogger("SchwabenLove Web Client");

	/**
	 * Private constructor to prevent initialization.
	 */
	private ClientsideSettings() {
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

	/*
	 * public static ReportGeneratorAsync getReportGenerator() { if
	 * (reportGenerator == null) { reportGenerator =
	 * GWT.create(ReportGenerator.class); } return reportGenerator; }
	 */

	/**
	 * Get the application wide logger. The log is created on the client-side.
	 * 
	 * @return The logger instance for the server-side
	 */
	public static Logger getLogger() {
		return log;
	}
}
