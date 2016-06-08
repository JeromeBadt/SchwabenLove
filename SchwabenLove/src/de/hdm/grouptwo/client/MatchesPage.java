package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.SearchProfile;

public class MatchesPage extends ContentPage {
	LayoutPanel lPanel = new LayoutPanel();
	LayoutPanel matchesPanel = new LayoutPanel();
	LayoutPanel searchProfilePanel = new LayoutPanel();

	public MatchesPage() {
		super("Partnervorschläge");
		initWidget(lPanel);
		lPanel.setStyleName("matches-page");
		matchesPanel.setStyleName("matches");

		lPanel.add(matchesPanel);
		lPanel.add(searchProfilePanel);

		lPanel.setWidgetLeftRight(matchesPanel, 0, Unit.PX, 310, Unit.PX);
		lPanel.setWidgetRightWidth(searchProfilePanel, 0, Unit.PX, 300, Unit.PX);

		initSearchProfilePanel();
	}

	private void initSearchProfilePanel() {
		searchProfilePanel.getElement().getStyle()
				.setBorderStyle(BorderStyle.SOLID);
		searchProfilePanel.getElement().getStyle().setBorderWidth(1, Unit.PX);
	}
	
	private void loadSearchProfiles(ArrayList<SearchProfile> searchProfiles) {
		ListBox searchProfileDropBox = new ListBox();
		for(SearchProfile sp : searchProfiles) {
			// add missing search profile name to db
			searchProfileDropBox.addItem(Integer.toString(sp.getId()));
		}
		
		Image addIcon = new Image("images/icons/add.png");
		Image deleteIcon = new Image("images/icons/delete.png");
		
		searchProfilePanel.add(searchProfileDropBox);
		searchProfilePanel.add(addIcon);
		searchProfilePanel.add(deleteIcon);
		
	}

	@Override
	public void updatePage() {
		matchesPanel.clear();
		lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, 0, Unit.PX);
		getMatchesByProfileId(1);

		administrationService.getSearchProfiles(new AsyncCallback<ArrayList<SearchProfile>>() {
			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().log(Level.WARNING,
						caught.getMessage());
			}

			public void onSuccess(ArrayList<SearchProfile> result) {
				loadSearchProfiles(result);
			}
		});
	}

	public void getMatchesByProfileId(int profileId) {
		administrationService.getMatchesByProfileId(profileId,
				new AsyncCallback<ArrayList<Profile>>() {
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}

					public void onSuccess(ArrayList<Profile> result) {
						showMatches(result);
					}
				});
	}

	public void showMatches(ArrayList<Profile> matches) {
		matchesPanel.clear();

		int offset = 0;
		for (Profile match : matches) {
			MatchProfileWidget profileWidget = new MatchProfileWidget(match);

			matchesPanel.add(profileWidget);
			matchesPanel.setWidgetTopHeight(profileWidget, offset, Unit.PX,
					118, Unit.PX);
			offset += 128;
		}
		lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, offset, Unit.PX);
	}

	private class MatchProfileWidget extends ProfileWidget {
		MatchProfileWidget(Profile profile) {
			super(profile);

			Image bookmarkIcon = new Image("images/icons/bookmark.png");
			bookmarkIcon.setWidth("24px");
			bookmarkIcon.setTitle("Profil merken");
			rightPanel.add(bookmarkIcon);
			Image blockIcon = new Image("images/icons/block.png");
			blockIcon.setWidth("24px");
			blockIcon.setTitle("Profil blocken");
			rightPanel.add(blockIcon);
			LayoutPanel heartPanel = new LayoutPanel();
			rightPanel.add(heartPanel);
			Image heartIcon = new Image("images/icons/heart.png");
			heartIcon.setWidth("72px");
			heartPanel.add(heartIcon);
			Label similarityDegreeLbl = new Label("120");
			similarityDegreeLbl.setStyleName("similarity-degree-label");
			heartPanel.add(similarityDegreeLbl);

			rightPanel.setWidgetLeftWidth(bookmarkIcon, 20, Unit.PCT, 24,
					Unit.PX);
			rightPanel
					.setWidgetRightWidth(blockIcon, 20, Unit.PCT, 24, Unit.PX);
			rightPanel.setWidgetTopHeight(bookmarkIcon, 0, Unit.PCT, 24,
					Unit.PX);
			rightPanel.setWidgetTopHeight(blockIcon, 0, Unit.PCT, 24, Unit.PX);
			rightPanel.setWidgetTopBottom(heartPanel, 22, Unit.PX, 0, Unit.PX);
			heartPanel.setWidgetLeftRight(heartIcon, 14, Unit.PX, 14, Unit.PX);
		}
	}
}
