package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.SearchProfile;

public class MatchesPage extends ContentPage {
	private LayoutPanel lPanel = new LayoutPanel();
	private LayoutPanel matchesPanel = new LayoutPanel();
	private LayoutPanel searchProfilePanel = new LayoutPanel();

	private ArrayList<SearchProfile> searchProfiles = new ArrayList<SearchProfile>();
	private SearchProfile activeSearchProfile = null;

	public MatchesPage() {
		super("Partnervorschläge");
		initWidget(lPanel);
		lPanel.setStyleName("matches-page");
		matchesPanel.setStyleName("matches");

		lPanel.add(matchesPanel);
		lPanel.add(searchProfilePanel);
	}

	@Override
	public void updatePage() {
		matchesPanel.clear();
		lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, 0, Unit.PX);

		administrationService
				.getSearchProfiles(new AsyncCallback<ArrayList<SearchProfile>>() {
					public void onSuccess(ArrayList<SearchProfile> result) {
						showSearchProfiles(result);
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}

	@Override
	public void onResize() {
		if (getElement().getClientWidth() >= 920) {
			int offset = (getElement().getClientWidth() - 910) / 2;
			lPanel.setWidgetLeftWidth(matchesPanel, offset, Unit.PX, 590,
					Unit.PX);
			lPanel.setWidgetRightWidth(searchProfilePanel, offset, Unit.PX,
					300, Unit.PX);
		} else {
			lPanel.setWidgetLeftRight(matchesPanel, 0, Unit.PX, 330, Unit.PX);
			lPanel.setWidgetRightWidth(searchProfilePanel, 10, Unit.PX, 300,
					Unit.PX);
		}
	}

	private void showSearchProfiles(ArrayList<SearchProfile> searchProfiles) {
		this.searchProfiles = searchProfiles;

		ListBox searchProfileDropBox = new ListBox();
		searchProfileDropBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				updateActiveSearchProfile(((ListBox) event.getSource())
						.getSelectedItemText());
			}
		});

		for (SearchProfile sp : searchProfiles) {
			searchProfileDropBox.addItem(sp.getName());
		}

		Image addIcon = new Image("images/icons/add.png");
		addIcon.setWidth("24px");
		Image deleteIcon = new Image("images/icons/delete.png");
		deleteIcon.setWidth("24px");

		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setStyleName("search-profile-panel-inner");
		Label attributeLabel = new Label("Attribute");
		attributeLabel.addStyleName("search-profile-header");
		attributeLabel.addStyleName("attribute-label");
		vPanel.add(attributeLabel);

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleName("search-profile-panel-h");

		VerticalPanel labelPanel = new VerticalPanel();
		labelPanel.setWidth("119px");

		labelPanel.add(new Label("Geschlecht:"));
		labelPanel.add(new Label("Alter:"));
		labelPanel.add(new Label("Körperbau:"));
		labelPanel.add(new Label("Größe:"));
		labelPanel.add(new Label("Haarfarbe:"));
		labelPanel.add(new Label("Beruf:"));
		labelPanel.add(new Label("Raucher:"));
		labelPanel.add(new Label("Bildung:"));
		labelPanel.add(new Label("Religion:"));

		VerticalPanel inputPanel = new VerticalPanel();
		inputPanel.setWidth("159px");
		inputPanel.setStyleName("search-profile-input-panel");

		HorizontalPanel genderPanel = new HorizontalPanel();
		genderPanel.setHeight("24px");
		CheckBox maleCheckBox = new CheckBox("m");
		CheckBox femaleCheckBox = new CheckBox("w");
		genderPanel.add(maleCheckBox);
		genderPanel.add(femaleCheckBox);

		HorizontalPanel agePanel = new HorizontalPanel();
		TextBox minAgeTextBox = new TextBox();
		minAgeTextBox.setWidth("35px");
		TextBox maxAgeTextBox = new TextBox();
		maxAgeTextBox.setWidth("35px");
		agePanel.add(minAgeTextBox);
		agePanel.add(new Label("-"));
		agePanel.add(maxAgeTextBox);
		agePanel.add(new Label("Jahre"));

		TextBox physiqueTextBox = new TextBox();

		HorizontalPanel heightPanel = new HorizontalPanel();
		TextBox minHeightTextBox = new TextBox();
		minHeightTextBox.setWidth("35px");
		TextBox maxHeightTextBox = new TextBox();
		maxHeightTextBox.setWidth("35px");
		heightPanel.add(minHeightTextBox);
		heightPanel.add(new Label("-"));
		heightPanel.add(maxHeightTextBox);
		heightPanel.add(new Label("cm"));

		TextBox hairColorTextBox = new TextBox();

		TextBox professionTextBox = new TextBox();

		HorizontalPanel smokerPanel = new HorizontalPanel();
		smokerPanel.setHeight("24px");
		CheckBox yesCheckBox = new CheckBox("ja");
		CheckBox noCheckBox = new CheckBox("nein");
		smokerPanel.add(yesCheckBox);
		smokerPanel.add(noCheckBox);

		TextBox educationTextBox = new TextBox();

		TextBox religionTextBox = new TextBox();

		inputPanel.add(genderPanel);
		inputPanel.add(agePanel);
		inputPanel.add(physiqueTextBox);
		inputPanel.add(heightPanel);
		inputPanel.add(hairColorTextBox);
		inputPanel.add(professionTextBox);
		inputPanel.add(smokerPanel);
		inputPanel.add(educationTextBox);
		inputPanel.add(religionTextBox);

		Label infoObjectsLabel = new Label("Info Objekte");
		infoObjectsLabel.addStyleName("search-profile-header");
		infoObjectsLabel.addStyleName("info-object-label");

		hPanel.add(labelPanel);
		hPanel.add(inputPanel);
		vPanel.add(hPanel);
		vPanel.add(infoObjectsLabel);

		Button saveButton = new Button("Suchprofil speichern");
		saveButton.setStyleName("search-profile-save-button");

		searchProfilePanel.add(searchProfileDropBox);
		searchProfilePanel.add(addIcon);
		searchProfilePanel.add(deleteIcon);
		searchProfilePanel.add(vPanel);
		searchProfilePanel.add(saveButton);

		searchProfileDropBox.setStyleName("search-profile-dropbox");

		searchProfilePanel.setWidgetRightWidth(addIcon, 34, Unit.PX, 24,
				Unit.PX);
		searchProfilePanel.setWidgetRightWidth(deleteIcon, 0, Unit.PX, 24,
				Unit.PX);
		searchProfilePanel
				.setWidgetTopHeight(vPanel, 34, Unit.PX, 288, Unit.PX);
		searchProfilePanel.setWidgetTopHeight(saveButton, 332, Unit.PX, 24,
				Unit.PX);
		lPanel.setWidgetTopHeight(searchProfilePanel, 0, Unit.PX, 356, Unit.PX);

		updateActiveSearchProfile(searchProfileDropBox.getSelectedItemText());
	}

	private void updateActiveSearchProfile(String searchProfileName) {
		for (SearchProfile sp : searchProfiles) {
			if (sp.getName() == searchProfileName) {
				activeSearchProfile = sp;
			}
		}

		administrationService.getMatchesBySearchProfile(
				activeSearchProfile, new AsyncCallback<ArrayList<Profile>>() {
					public void onSuccess(ArrayList<Profile> result) {
						showMatches(result);
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}

	private void showMatches(ArrayList<Profile> matches) {
		matchesPanel.clear();

		if (matches.isEmpty()) {
			Label emptyLabel = new Label("Keine Treffer.");
			emptyLabel.setStyleName("info");
			matchesPanel.add(emptyLabel);

			lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, 50, Unit.PX);
		} else {
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

		// Adjust the layout when the browser event loop returns (wait for
		// scrollbar to render)
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				onResize();
			}
		});
	}

	private class MatchProfileWidget extends ProfileWidget {
		MatchProfileWidget(Profile profile) {
			super(profile);

			final int profileId = profile.getId();

			Image bookmarkIcon = new Image("images/icons/bookmark.png");
			bookmarkIcon.addStyleName("img-button");
			bookmarkIcon.setWidth("24px");
			bookmarkIcon.setTitle("Profil merken");
			bookmarkIcon.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					administrationService.addBookmarkByProfileId(
							profileId, new AsyncCallback<Void>() {
								public void onSuccess(Void result) {
									// bookmarkIcon
									// .setUrl("images/icons/bookmark2.png");
								}

								public void onFailure(Throwable caught) {
									ClientsideSettings.getLogger().log(
											Level.WARNING,
											caught.getMessage());
								}
							});
				}
			});

			Image blockIcon = new Image("images/icons/block.png");
			blockIcon.addStyleName("img-button");
			blockIcon.setWidth("24px");
			blockIcon.setTitle("Profil blocken");
			blockIcon.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					administrationService.addBlockByProfileId(profileId,
							new AsyncCallback<Void>() {
								public void onSuccess(Void result) {
									// Remove from list
								}

								public void onFailure(Throwable caught) {
									ClientsideSettings.getLogger().log(
											Level.WARNING,
											caught.getMessage());
								}
							});
				}
			});

			LayoutPanel heartPanel = new LayoutPanel();
			Image heartIcon = new Image("images/icons/heart.png");
			heartIcon.setWidth("72px");
			Label similarityDegreeLbl = new Label("120");
			similarityDegreeLbl.setStyleName("similarity-degree-label");

			heartPanel.add(heartIcon);
			heartPanel.add(similarityDegreeLbl);

			rightPanel.add(bookmarkIcon);
			rightPanel.add(blockIcon);
			rightPanel.add(heartPanel);

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
