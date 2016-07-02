package de.hdm.grouptwo.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.AdministrationServiceAsync;
import de.hdm.grouptwo.shared.ReportServiceAsync;
import de.hdm.grouptwo.shared.bo.LoginInfo;

public class UnviewedMatchesReportPage extends ContentPage{
	private Menu menu = null;
//	private LayoutPanel lPanel = new LayoutPanel();
//	private LayoutPanel matchesPanel = new LayoutPanel();
	
	private ScrollPanel sPanel = new ScrollPanel();
	
	private ReportServiceAsync reportService = ClientsideSettings.getReportService();
	private AdministrationServiceAsync administrationService = ClientsideSettings
			.getAdministrationService();

	public UnviewedMatchesReportPage(Menu menu, LoginInfo loginInfo) {
		super("Ungesehene Partnervorschläge");
//		initWidget(lPanel);
//		lPanel.setStyleName("matches-page");
//		matchesPanel.setStyleName("matches");
		
		initWidget(sPanel);
		
		
		this.menu = menu;
		
//		lPanel.add(matchesPanel);
	}

	@Override
	public void updatePage() {
		// TODO Auto-generated method stub
//		matchesPanel.clear();
//		lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, 0, Unit.PX);
		
		final VerticalPanel vPanel = new VerticalPanel();
		vPanel.setWidth("90%");
	
		reportService.testMethod(new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				vPanel.clear();
				Label lbl = new Label();
				lbl.setText("RPC FAILURE");
				vPanel.add(lbl);
				sPanel.add(vPanel);
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				vPanel.clear();
				Label lbl = new Label();
				lbl.setText(result);
				vPanel.add(lbl);
				sPanel.add(vPanel);
			}
			
		});
		
		
	}

}
