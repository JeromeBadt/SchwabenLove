package de.hdm.grouptwo.client;

import com.google.gwt.core.client.GWT;

import de.hdm.grouptwo.shared.SchwabenAdministration;
import de.hdm.grouptwo.shared.SchwabenAdministrationAsync;

/**
 * Class for relevant client-side settings and services.
 * 
 * @author Thies, JeromeBadt
 * 
 */

public class ClientsideSettings {
	private static SchwabenAdministrationAsync schwabenAdministration = null;

	// private static ReportGeneratorAsync reportGenerator = null;

	/**
	 * Private constructor to prevent initialization.
	 */
	private ClientsideSettings() {
	}

	/**
	 * Creates and returns the application wide service for
	 * SchwabenAdministration. Ensures that only a single instance exists.
	 * 
	 * @return The the SchwabenAdministration service
	 */
	public static SchwabenAdministrationAsync getSchwabenAdministration() {
		if (schwabenAdministration == null) {
			schwabenAdministration = GWT.create(SchwabenAdministration.class);
		}

		return schwabenAdministration;
	}

	/*
	 * public static ReportGeneratorAsync getReportGenerator() { if
	 * (reportGenerator == null) { reportGenerator =
	 * GWT.create(ReportGenerator.class); } return reportGenerator; }
	 */
}
