package de.hdm.grouptwo.client;

import de.hdm.grouptwo.shared.ReportServiceAsync;

public class UnviewedMatchesReportPage extends ContentPage{
	
	private ReportServiceAsync reportService = ClientsideSettings.getReportService();

	public UnviewedMatchesReportPage() {
		super("Nicht gesehene Partnervorschläge anzeigen");
		// TODO Serverseitige Methode aufrufen
	}

	@Override
	public void updatePage() {
		// TODO Auto-generated method stub
		
	}

}
