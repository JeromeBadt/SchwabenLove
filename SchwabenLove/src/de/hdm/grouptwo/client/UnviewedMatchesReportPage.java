package de.hdm.grouptwo.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.LayoutPanel;

import de.hdm.grouptwo.shared.AdministrationServiceAsync;
import de.hdm.grouptwo.shared.ReportServiceAsync;

public class UnviewedMatchesReportPage extends ContentPage{
	private Menu menu = null;
	private LayoutPanel lPanel = new LayoutPanel();
	private LayoutPanel matchesPanel = new LayoutPanel();
	
	private ReportServiceAsync reportService = ClientsideSettings.getReportService();
	private AdministrationServiceAsync administrationService = ClientsideSettings
			.getAdministrationService();

	public UnviewedMatchesReportPage(Menu menu) {
		super("WTF");
		initWidget(lPanel);
		lPanel.setStyleName("matches-page");
		matchesPanel.setStyleName("matches");
		
		this.menu = menu;
		
		lPanel.add(matchesPanel);
	}

	@Override
	public void updatePage() {
		// TODO Auto-generated method stub
		matchesPanel.clear();
		lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, 0, Unit.PX);
	}

}
