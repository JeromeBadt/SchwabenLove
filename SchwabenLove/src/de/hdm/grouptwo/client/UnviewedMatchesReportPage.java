package de.hdm.grouptwo.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.ReportServiceAsync;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.report.HTMLReportWriter;
import de.hdm.grouptwo.shared.report.UnviewedMatchesReport;

public class UnviewedMatchesReportPage extends ContentPage{

	private ScrollPanel sPanel = new ScrollPanel();
	
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
		
		final VerticalPanel vPanel = new VerticalPanel();
		vPanel.setWidth("90%");
			
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
						writer.process(report);
						logger.log(Level.SEVERE, "Report verarbeitet");
						vPanel.add(new HTML(writer.getReportText()));
						logger.log(Level.SEVERE, "zu vPanel hinzugefügt");
						
						sPanel.add(vPanel);
						logger.log(Level.SEVERE, "zu sPanel hinzugefügt");
						
					}
					
				});
			}
			
		});
		
	}
	
}
