package de.hdm.grouptwo.client;

import com.google.gwt.user.client.ui.LayoutPanel;

import de.hdm.grouptwo.shared.ReportServiceAsync;

public class MatchesBySearchprofileReportPage extends ContentPage {
	private LayoutPanel lPanel = new LayoutPanel();
	private LayoutPanel matchesPanel = new LayoutPanel();

	private ReportServiceAsync reportService = ClientsideSettings.getReportService();
	
	public MatchesBySearchprofileReportPage() {
		super("Partnervorschläge anhand von Suchprofil anzeigen");
		initWidget(lPanel);
		lPanel.setStyleName("matches-page");
		matchesPanel.setStyleName("matches");
		
		lPanel.add(matchesPanel);
	}

	@Override
	public void updatePage() {
		// TODO Auto-generated method stub
		
	}
	
	

}
