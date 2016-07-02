package de.hdm.grouptwo.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.AdministrationServiceAsync;
import de.hdm.grouptwo.shared.ReportServiceAsync;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.SearchProfile;
import de.hdm.grouptwo.shared.report.HTMLReportWriter;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;
import de.hdm.grouptwo.shared.report.UnviewedMatchesReport;

public class UnviewedMatchesReportPage extends ContentPage{
	private Menu menu = null;
//	private LayoutPanel lPanel = new LayoutPanel();
//	private LayoutPanel matchesPanel = new LayoutPanel();
	
	private ScrollPanel sPanel = new ScrollPanel();
//	private VerticalPanel vPanel = new VerticalPanel();
	
	private Profile p = new Profile();
	
	private ReportServiceAsync reportService = ClientsideSettings.getReportService();
	
	Logger logger = Logger.getLogger("GUI Logger");

	public UnviewedMatchesReportPage(Menu menu, LoginInfo loginInfo) {
		super("Ungesehene Partnervorschläge");
		
		p.setEmail(loginInfo.getEmailAddress());

		reportService.setupAdministration(loginInfo.getEmailAddress(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		
		initWidget(sPanel);
		// this.menu = menu;
		

		
		
		
		
		
		
	}

	@Override
	public void updatePage() {
	
		// TODO Auto-generated method stub
//		matchesPanel.clear();
//		lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, 0, Unit.PX);
		
		final VerticalPanel vPanel = new VerticalPanel();
		vPanel.setWidth("90%");
	
//		reportService.testMethod(new AsyncCallback<String>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				vPanel.clear();
//				Label lbl = new Label();
//				lbl.setText("RPC FAILURE");
//				vPanel.add(lbl);
//				sPanel.add(vPanel);
//			}
//
//			@Override
//			public void onSuccess(String result) {
//				// TODO Auto-generated method stub
//				vPanel.clear();
//				Label lbl = new Label();
//				lbl.setText(result);
//				vPanel.add(lbl);
//				sPanel.add(vPanel);
//			}
//			
//		});
		
		
		administrationService.getProfile(new AsyncCallback<Profile>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Profile result) {
				// TODO Auto-generated method stub
				p = result; 
				
				logger.log(Level.SEVERE, "USER EMAIL still early = " + p.getEmail());
				logger.log(Level.SEVERE, "USER ID stell early = " + p.getId());
				reportService.getUnviewedMatches(p, new AsyncCallback<UnviewedMatchesReport>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						logger.log(Level.SEVERE, "UnviewedMatchesReport fehlgeschlagen");
					}

					@Override
					public void onSuccess(UnviewedMatchesReport result) {
						// TODO Auto-generated method stub
						logger.log(Level.SEVERE, "UnviewedMatchesReport erfolgreich");
						vPanel.clear();
						logger.log(Level.SEVERE, "vPanel gecleart");
						// TODO Auto-generated method stub
						HTMLReportWriter writer = new HTMLReportWriter();
						logger.log(Level.SEVERE, "Report erstellt");
						final UnviewedMatchesReport report = result;
						writer.process(result);
						logger.log(Level.SEVERE, "Report verarbeitet");
						// logger.log(Level.SEVERE, writer.toString());
						vPanel.add(new HTML(writer.getReportText()));
						logger.log(Level.SEVERE, "zu vPanel hinzugefügt");
						
						sPanel.add(vPanel);
						logger.log(Level.SEVERE, "zu sPanel hinzugefügt");
						// rootP.add(hMain);
						
					}
					
				});
			}
			
		});
		
	}
	
//	public void loadReport() {
//		Logger logger = Logger.getLogger("NameOfYourLogger");
//		logger.log(Level.SEVERE, "USER EMAIL EARLY = " + p.getEmail());
//		
//		reportService.getUnviewedMatches(p, new AsyncCallback<UnviewedMatchesReport>() {
//			
//								@Override
//								public void onFailure(Throwable caught) {
//									// TODO Auto-generated method stub
//									
//								}
//			
//								@Override
//								public void onSuccess(UnviewedMatchesReport result) {
//									// TODO Auto-generated method stub
//									vPanel.clear();
//									// TODO Auto-generated method stub
//									HTMLReportWriter writer = new HTMLReportWriter();
//									final UnviewedMatchesReport report = result;
//									writer.process(result);
//									vPanel.add(new HTML(writer.getReportText()));
//									sPanel.add(vPanel);
//									
//								}
//								
//							});
//	}
}
