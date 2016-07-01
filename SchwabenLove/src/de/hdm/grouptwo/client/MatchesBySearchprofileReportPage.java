package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.AdministrationServiceAsync;
import de.hdm.grouptwo.shared.ReportServiceAsync;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.SearchProfile;
import de.hdm.grouptwo.shared.report.HTMLReportWriter;
import de.hdm.grouptwo.shared.report.MatchesBySearchprofileReport;
import de.hdm.grouptwo.shared.report.SimpleReport;

public class MatchesBySearchprofileReportPage extends ContentPage {
//	private LayoutPanel lPanel = new LayoutPanel();
//	private LayoutPanel matchesPanel = new LayoutPanel();
//	private LayoutPanel searchProfilePanel = new LayoutPanel();
	
	private ScrollPanel sPanel = new ScrollPanel();
	
//	private ArrayList<SearchProfile> searchProfiles = new ArrayList<SearchProfile>();
//	private SearchProfile activeSearchProfile = null;
//	private LoginInfo loginInfo = null;
	
	private ReportServiceAsync reportService = ClientsideSettings.getReportService();
//	private AdministrationServiceAsync administrationService = ClientsideSettings
//			.getAdministrationService();
	
	
	
	public MatchesBySearchprofileReportPage() {	
		super("Partnervorschläge anhand von Suchprofil");
		// initWidget(lPanel);
		// this.loginInfo = loginInfo;
		// lPanel.setStyleName("matches-page");
		// matchesPanel.setStyleName("matches");
		
		// lPanel.add(matchesPanel);
		//lPanel.add(searchProfilePanel);
		initWidget(sPanel);
		
		
		
	}

	@Override
	public void updatePage() {
		
		final VerticalPanel vPanel = new VerticalPanel();
		vPanel.setWidth("90%");
		sPanel.add(vPanel);
		
		reportService.getMatchesBySearchprofileReport(new AsyncCallback<MatchesBySearchprofileReport>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(MatchesBySearchprofileReport result) {
				// TODO Auto-generated method stub
				HTMLReportWriter writer = new HTMLReportWriter();
				final MatchesBySearchprofileReport report = result;
				writer.process(result);
				vPanel.add(new HTML(writer.getReportText()));
			}
			
		});
		
		
		// TODO Auto-generated method stub
//		matchesPanel.clear();
//		lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, 0, Unit.PX);
//		reportService.getMatchesBySearchprofileReport(loginInfo, new AsyncCallback<MatchesBySearchprofileReport>(){
//		reportService.getMatchesBySearchprofileReport(new AsyncCallback<MatchesBySearchprofileReport>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Label lbl = new Label();
//				lbl.setText("TEST FAILURE");
//				matchesPanel.add(lbl);
//			}
//
//			@Override
//			public void onSuccess(MatchesBySearchprofileReport result) {
//				// TODO Auto-generated method stub
//				HTMLReportWriter writer = new HTMLReportWriter();
//				final MatchesBySearchprofileReport report = result;
//				writer.process(result);
//				Label lbl = new Label();
//				lbl.setText("TEST");
//				matchesPanel.add(lbl);
//				matchesPanel.add(new HTML(writer.getReportText()));
//			}
//			
//		});
		
//		reportService.testMethod(new AsyncCallback<String>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Label lbl = new Label();
//				lbl.setText("RPC FAILURE");
//				VerticalPanel vpanel = new VerticalPanel();
//				HorizontalPanel hpanel = new HorizontalPanel();
//				
//				hpanel.add(lbl);
//				vpanel.add(hpanel);
//				matchesPanel.add(vpanel);
//			}
//
//			@Override
//			public void onSuccess(String result) {
//				// TODO Auto-generated method stub
//				Label lbl = new Label();
//				lbl.setText(result);
//				VerticalPanel vpanel = new VerticalPanel();
//				HorizontalPanel hpanel = new HorizontalPanel();
//				
//				hpanel.add(lbl);
//				vpanel.add(hpanel);
//				matchesPanel.add(vpanel);
//			}
//			
//		});
		
		
//		administrationService
//		.getSearchProfiles(new AsyncCallback<ArrayList<SearchProfile>>() {
//			public void onSuccess(ArrayList<SearchProfile> result) {
//				showSearchProfiles(result);
//			}
//
//			public void onFailure(Throwable caught) {
//				ClientsideSettings.getLogger().log(Level.WARNING,
//						caught.getMessage());
//			}
//		});
		
	}
	
//	private void showSearchProfiles(ArrayList<SearchProfile> searchProfiles) {
//		this.searchProfiles = searchProfiles;
//
//		ListBox searchProfileDropBox = new ListBox();
//		searchProfileDropBox.addChangeHandler(new ChangeHandler() {
//			public void onChange(ChangeEvent event) {
////				updateActiveSearchProfile(((ListBox) event.getSource())
////						.getSelectedItemText());
//			}
//		});
//		
//		for (SearchProfile sp : searchProfiles) {
//			searchProfileDropBox.addItem(sp.getName());
//		}
//		
//		Image addIcon = new Image("images/icons/add.png");
//		addIcon.setWidth("24px");
//		Image deleteIcon = new Image("images/icons/delete.png");
//		deleteIcon.setWidth("24px");
//
//		VerticalPanel vPanel = new VerticalPanel();
//		vPanel.setStyleName("search-profile-panel-inner");
//		Label attributeLabel = new Label("Attribute");
//		attributeLabel.addStyleName("search-profile-header");
//		attributeLabel.addStyleName("attribute-label");
//		vPanel.add(attributeLabel);
//
//		HorizontalPanel hPanel = new HorizontalPanel();
//		hPanel.setStyleName("search-profile-panel-h");
//
//		VerticalPanel labelPanel = new VerticalPanel();
//		labelPanel.setWidth("119px");
//
//		labelPanel.add(new Label("Geschlecht:"));
//		labelPanel.add(new Label("Alter:"));
//		labelPanel.add(new Label("Körperbau:"));
//		labelPanel.add(new Label("Größe:"));
//		labelPanel.add(new Label("Haarfarbe:"));
//		labelPanel.add(new Label("Beruf:"));
//		labelPanel.add(new Label("Raucher:"));
//		labelPanel.add(new Label("Bildung:"));
//		labelPanel.add(new Label("Religion:"));
//
//		VerticalPanel inputPanel = new VerticalPanel();
//		inputPanel.setWidth("159px");
//		inputPanel.setStyleName("search-profile-input-panel");
//
//		HorizontalPanel genderPanel = new HorizontalPanel();
//		genderPanel.setHeight("24px");
//		CheckBox maleCheckBox = new CheckBox("m");
//		CheckBox femaleCheckBox = new CheckBox("w");
//		genderPanel.add(maleCheckBox);
//		genderPanel.add(femaleCheckBox);
//
//		HorizontalPanel agePanel = new HorizontalPanel();
//		TextBox minAgeTextBox = new TextBox();
//		minAgeTextBox.setWidth("35px");
//		TextBox maxAgeTextBox = new TextBox();
//		maxAgeTextBox.setWidth("35px");
//		agePanel.add(minAgeTextBox);
//		agePanel.add(new Label("-"));
//		agePanel.add(maxAgeTextBox);
//		agePanel.add(new Label("Jahre"));
//
//		TextBox physiqueTextBox = new TextBox();
//
//		HorizontalPanel heightPanel = new HorizontalPanel();
//		TextBox minHeightTextBox = new TextBox();
//		minHeightTextBox.setWidth("35px");
//		TextBox maxHeightTextBox = new TextBox();
//		maxHeightTextBox.setWidth("35px");
//		heightPanel.add(minHeightTextBox);
//		heightPanel.add(new Label("-"));
//		heightPanel.add(maxHeightTextBox);
//		heightPanel.add(new Label("cm"));
//
//		TextBox hairColorTextBox = new TextBox();
//
//		TextBox professionTextBox = new TextBox();
//
//		HorizontalPanel smokerPanel = new HorizontalPanel();
//		smokerPanel.setHeight("24px");
//		CheckBox yesCheckBox = new CheckBox("ja");
//		CheckBox noCheckBox = new CheckBox("nein");
//		smokerPanel.add(yesCheckBox);
//		smokerPanel.add(noCheckBox);
//
//		TextBox educationTextBox = new TextBox();
//
//		TextBox religionTextBox = new TextBox();
//
//		inputPanel.add(genderPanel);
//		inputPanel.add(agePanel);
//		inputPanel.add(physiqueTextBox);
//		inputPanel.add(heightPanel);
//		inputPanel.add(hairColorTextBox);
//		inputPanel.add(professionTextBox);
//		inputPanel.add(smokerPanel);
//		inputPanel.add(educationTextBox);
//		inputPanel.add(religionTextBox);
//
//		Label infoObjectsLabel = new Label("Info Objekte");
//		infoObjectsLabel.addStyleName("search-profile-header");
//		infoObjectsLabel.addStyleName("info-object-label");
//
//		hPanel.add(labelPanel);
//		hPanel.add(inputPanel);
//		vPanel.add(hPanel);
//		vPanel.add(infoObjectsLabel);
//
//		Button saveButton = new Button("Suchprofil speichern");
//		saveButton.setStyleName("search-profile-save-button");
//
//		searchProfilePanel.add(searchProfileDropBox);
//		searchProfilePanel.add(addIcon);
//		searchProfilePanel.add(deleteIcon);
//		searchProfilePanel.add(vPanel);
//		searchProfilePanel.add(saveButton);
//
//		searchProfileDropBox.setStyleName("search-profile-dropbox");
//
//		searchProfilePanel.setWidgetRightWidth(addIcon, 34, Unit.PX, 24,
//				Unit.PX);
//		searchProfilePanel.setWidgetRightWidth(deleteIcon, 0, Unit.PX, 24,
//				Unit.PX);
//		searchProfilePanel
//				.setWidgetTopHeight(vPanel, 34, Unit.PX, 288, Unit.PX);
//		searchProfilePanel.setWidgetTopHeight(saveButton, 332, Unit.PX, 24,
//				Unit.PX);
//		lPanel.setWidgetTopHeight(searchProfilePanel, 0, Unit.PX, 356, Unit.PX);
//		
//		// updateActiveSearchProfile(searchProfileDropBox.getSelectedItemText());
//	}
		
		
//		private void updateActiveSearchProfile(String searchProfileName) {
//			for (SearchProfile sp : searchProfiles) {
//				if (sp.getName() == searchProfileName) {
//					activeSearchProfile = sp;
//				}
//			}
//		}
	
	

}
