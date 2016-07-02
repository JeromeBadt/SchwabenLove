package de.hdm.grouptwo.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.ReportServiceAsync;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.SearchProfile;
import de.hdm.grouptwo.shared.report.HTMLReportWriter;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;

public class MatchesBySearchprofileReportPage extends ContentPage {
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hTop = new HorizontalPanel();
	private HorizontalPanel hMain = new HorizontalPanel();
	
	private ScrollPanel sPanel = new ScrollPanel();
	
	private ArrayList<SearchProfile> searchProfiles = new ArrayList<SearchProfile>();
	private SearchProfile activeSearchProfile = new SearchProfile();
	
	private ReportServiceAsync reportService = ClientsideSettings.getReportService();
	
	
	public MatchesBySearchprofileReportPage(LoginInfo loginInfo) {	
		super("Partnervorschläge anhand von Suchprofil");
	
		// set loginInfo for reportService
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
		
		// Initialize root panel for content
		initWidget(sPanel);
		
		// Get all SearchProfiles for logged in User
		administrationService.getSearchProfiles(new AsyncCallback<ArrayList<SearchProfile>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<SearchProfile> result) {
				// TODO Auto-generated method stub
				searchProfiles = result;
				loadSearchProfilePicker(searchProfiles);
				
			}
			
		});	
		
		loadReport(activeSearchProfile);
		
		sPanel.add(vPanel);
		

	}
	
	public void loadSearchProfilePicker(ArrayList<SearchProfile> searchProfiles) {
		ListBox lb = new ListBox();
		lb.setVisibleItemCount(1);
		
		final ArrayList<SearchProfile> searchProfilesFinal = searchProfiles;
		
		// populate ListBox with SearchProfiles
		for(SearchProfile sp : searchProfiles) {
			lb.addItem(sp.getName());
		}
		
		activeSearchProfile = searchProfilesFinal.get(0);
		
		// Add Functionality to ListBox
		lb.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				activeSearchProfile = searchProfilesFinal.get(((ListBox) event.getSource()).getSelectedIndex());
				loadReport(activeSearchProfile);
				
			}
			
		});
		
		Label lbl = new Label();
		lbl.setText("Suchprofil auswählen");
		hTop.add(lbl);
		// Add ListBox to vPanel
		hTop.add(lb);
		vPanel.add(hTop);
		
		
	}

	@Override
	public void updatePage() {
		
//		hTop.setWidth("90%");
//		hMain.setWidth("90%");
		// final VerticalPanel vPanel = new VerticalPanel();
		// vPanel.setWidth("90%");
		
		
		
		
	}
	
	public void loadReport(SearchProfile activeSearchProfile) {
		reportService.getMatchesBySearchprofileReport(activeSearchProfile, new AsyncCallback<MatchesBySearchprofileReport>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(MatchesBySearchprofileReport result) {
				hMain.clear();
				// TODO Auto-generated method stub
				HTMLReportWriter writer = new HTMLReportWriter();
				final MatchesBySearchprofileReport report = result;
				writer.process(result);
				hMain.add(new HTML(writer.getReportText()));
				vPanel.add(hMain);
				// rootP.add(hMain);
			}
			
		});
	}
	
	

}
