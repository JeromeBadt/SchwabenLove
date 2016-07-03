package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
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

	private SearchProfileDropBox searchProfileDropBox = new SearchProfileDropBox();
	private SearchProfilePopup inputBox = new SearchProfilePopup();

	CheckBox genderMaleCB = new CheckBox("m");
	CheckBox genderFemaleCB = new CheckBox("w");
	TextBox minAgeTextBox = new TextBox();
	TextBox maxAgeTextBox = new TextBox();
	TextBox physiqueTextBox = new TextBox();
	TextBox minHeightTextBox = new TextBox();
	TextBox maxHeightTextBox = new TextBox();
	TextBox hairColorTextBox = new TextBox();
	TextBox professionTextBox = new TextBox();
	CheckBox smokerYesCB = new CheckBox("Ja");
	CheckBox smokerNoCB = new CheckBox("Nein");
	TextBox educationTextBox = new TextBox();
	TextBox religionTextBox = new TextBox();

	public MatchesPage() {
		super("Partnervorschläge");
		initWidget(lPanel);
		lPanel.setStyleName("matches-page");
		matchesPanel.setStyleName("matches");

		lPanel.add(matchesPanel);
		lPanel.add(searchProfilePanel);

		initSearchProfilePanel();
	}

	@Override
	public void updatePage() {
		matchesPanel.clear();
		lPanel.setWidgetTopHeight(matchesPanel, 0, Unit.PX, 0, Unit.PX);

		loadMatches(searchProfileDropBox.getSelected());
	}

	private void initSearchProfilePanel() {
		Image addIcon = new Image("images/icons/add.png");
		addIcon.addStyleName("img-button");
		addIcon.setWidth("24px");
		addIcon.setTitle("Neues Suchprofil erstellen");
		addIcon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				inputBox.center();
			}
		});

		Image deleteIcon = new Image("images/icons/delete.png");
		deleteIcon.addStyleName("img-button");
		deleteIcon.setWidth("24px");
		deleteIcon.setTitle("Suchprofil löschen");
		deleteIcon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final SearchProfile selected = searchProfileDropBox
						.getSelected();

				if (searchProfileDropBox.getItemCount() <= 1) {
					Window.alert("Sie benötigen mindestens ein Suchprofil");
				} else {
					administrationService.deleteSearchProfile(selected,
							new AsyncCallback<Void>() {
								public void onSuccess(Void result) {
									searchProfileDropBox.remove(selected);
								}

								public void onFailure(Throwable caught) {
									ClientsideSettings.getLogger().log(
											Level.WARNING, caught.getMessage());
								}
							});
				}
			}
		});

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
		genderPanel.add(genderMaleCB);
		genderPanel.add(genderFemaleCB);

		HorizontalPanel agePanel = new HorizontalPanel();
		minAgeTextBox.setWidth("35px");
		maxAgeTextBox.setWidth("35px");
		agePanel.add(minAgeTextBox);
		agePanel.add(new Label("-"));
		agePanel.add(maxAgeTextBox);
		agePanel.add(new Label("Jahre"));

		HorizontalPanel heightPanel = new HorizontalPanel();
		minHeightTextBox.setWidth("35px");
		maxHeightTextBox.setWidth("35px");
		heightPanel.add(minHeightTextBox);
		heightPanel.add(new Label("-"));
		heightPanel.add(maxHeightTextBox);
		heightPanel.add(new Label("cm"));

		HorizontalPanel smokerPanel = new HorizontalPanel();
		smokerPanel.setHeight("24px");
		smokerPanel.add(smokerYesCB);
		smokerPanel.add(smokerNoCB);

		inputPanel.add(genderPanel);
		inputPanel.add(agePanel);
		inputPanel.add(physiqueTextBox);
		inputPanel.add(heightPanel);
		inputPanel.add(hairColorTextBox);
		inputPanel.add(professionTextBox);
		inputPanel.add(smokerPanel);
		inputPanel.add(educationTextBox);
		inputPanel.add(religionTextBox);

		// Label infoObjectsLabel = new Label("Info Objekte");
		// infoObjectsLabel.addStyleName("search-profile-header");
		// infoObjectsLabel.addStyleName("info-object-label");

		hPanel.add(labelPanel);
		hPanel.add(inputPanel);
		vPanel.add(hPanel);
		// vPanel.add(infoObjectsLabel);

		Button saveButton = new Button("Suchprofil speichern");
		saveButton.setStyleName("search-profile-save-button");
		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				saveSearchProfile();
			}
		});

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
				.setWidgetTopHeight(vPanel, 34, Unit.PX, 263, Unit.PX);
		searchProfilePanel.setWidgetTopHeight(saveButton, 307, Unit.PX, 24,
				Unit.PX);
		lPanel.setWidgetTopHeight(searchProfilePanel, 0, Unit.PX, 356, Unit.PX);

		administrationService
				.getSearchProfiles(new AsyncCallback<ArrayList<SearchProfile>>() {
					public void onSuccess(ArrayList<SearchProfile> result) {
						for (SearchProfile sp : result) {
							searchProfileDropBox.add(sp);
						}
						loadSearchProfile(result.get(0));
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}

	private void loadMatches(SearchProfile searchProfile) {
		administrationService.getMatchesBySearchProfile(
				searchProfile, new AsyncCallback<ArrayList<Profile>>() {
					public void onSuccess(ArrayList<Profile> result) {
						showMatches(result);
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}

	private void loadSearchProfile(SearchProfile sp) {
		if (sp.getGender() == null) {
			genderMaleCB.setValue(true);
			genderFemaleCB.setValue(true);
		} else {
			genderMaleCB.setValue((sp.getGender().equals("m")));
			genderFemaleCB.setValue(sp.getGender().equals("w"));
		}
		minAgeTextBox.setText(sp.getMinAge() == 0 ? null : Integer.toString(sp
				.getMinAge()));
		maxAgeTextBox.setText(sp.getMaxAge() == 0 ? null : Integer.toString(sp
				.getMaxAge()));
		physiqueTextBox.setText(sp.getPhysique());
		minHeightTextBox.setText(sp.getMinHeight() == 0 ? null : Integer
				.toString(sp.getMinHeight()));
		maxHeightTextBox.setText(sp.getMaxHeight() == 0 ? null : Integer
				.toString(sp.getMaxHeight()));
		hairColorTextBox.setText(sp.getHairColor());
		professionTextBox.setText(sp.getProfession());
		if (sp.getSmoker() == null) {
			smokerYesCB.setValue(true);
			smokerNoCB.setValue(true);
		} else {
			smokerYesCB.setValue(sp.getSmoker().equals("TRUE"));
			smokerNoCB.setValue(sp.getSmoker().equals("FALSE"));
		}
		educationTextBox.setText(sp.getEducation());
		religionTextBox.setText(sp.getReligion());
	}

	private void saveSearchProfile() {
		SearchProfile sp = searchProfileDropBox.getSelected();
		StringBuilder error = new StringBuilder();

		if (genderMaleCB.getValue() && genderFemaleCB.getValue()) {
			sp.setGender(null);
		} else if (genderMaleCB.getValue()) {
			sp.setGender("m");
		} else if (genderFemaleCB.getValue()) {
			sp.setGender("w");
		} else {
			error.append("Sie müssen nach mindestens einem Geschlecht suchen.");
		}
		sp.setMinAge(getIntOrNull(minAgeTextBox.getText()));
		sp.setMaxAge(getIntOrNull(maxAgeTextBox.getText()));
		sp.setPhysique(getStringOrNull(physiqueTextBox.getText()));
		sp.setMinHeight(getIntOrNull(minHeightTextBox.getText()));
		sp.setMaxHeight(getIntOrNull(maxHeightTextBox.getText()));
		sp.setHairColor(getStringOrNull(hairColorTextBox.getText()));
		sp.setProfession(getStringOrNull(professionTextBox.getText()));
		if (smokerYesCB.getValue() && smokerNoCB.getValue()) {
			sp.setSmoker(null);
		} else if (smokerYesCB.getValue()) {
			sp.setSmoker("TRUE");
		} else if (smokerNoCB.getValue()) {
			sp.setSmoker("FALSE");
		} else {
			error.append("Sie müssen nach mindestens einem Rauchertyp suchen.");
		}

		ClientsideSettings.getLogger().log(Level.INFO,
				"Smoker: " + sp.getSmoker());

		sp.setEducation(getStringOrNull(educationTextBox.getText()));
		sp.setReligion(getStringOrNull(religionTextBox.getText()));

		if (error.length() != 0) {
			Window.alert(error.toString());
			return;
		}

		final SearchProfile cSearchProfile = sp;
		administrationService.updateSearchProfile(sp,
				new AsyncCallback<Void>() {
					public void onSuccess(Void result) {
						loadMatches(cSearchProfile);
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}

	/**
	 * Check if an int is empty.
	 * 
	 * @param check
	 *            the int to check
	 * @return 0 if it's empty, otherwise the original int
	 */
	private int getIntOrNull(String check) {
		return check.length() == 0 ? 0 : Integer.parseInt(check);
	}

	/**
	 * Check if a string is empty.
	 * 
	 * @param check
	 *            the string to check
	 * @return null if it's empty, otherwise the original string
	 */
	private String getStringOrNull(String check) {
		return check.length() == 0 ? null : check;
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

	private class MatchProfileWidget extends ProfileWidget {
		private int profileId = 0;
		private boolean state = false;

		private Image bookmarkIcon = new Image();

		private MatchProfileWidget(Profile profile) {
			super(profile);
			profileId = profile.getId();

			administrationService.checkBookmarked(profileId,
					new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							state = result;
							if (state) {
								bookmarkIcon
										.setUrl("images/icons/bookmark2.png");
							} else {
								bookmarkIcon
										.setUrl("images/icons/bookmark.png");
							}
						}

						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().log(Level.WARNING,
									caught.getMessage());
						}
					});

			bookmarkIcon.addStyleName("img-button");
			bookmarkIcon.setWidth("24px");
			bookmarkIcon.setTitle("Profil merken");
			bookmarkIcon.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					bookmarkChange();
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
									updatePage();
								}

								public void onFailure(Throwable caught) {
									ClientsideSettings.getLogger().log(
											Level.WARNING, caught.getMessage());
								}
							});
				}
			});

			rightPanel.add(bookmarkIcon);
			rightPanel.add(blockIcon);

			rightPanel.setWidgetLeftWidth(bookmarkIcon, 20, Unit.PCT, 24,
					Unit.PX);
			rightPanel
					.setWidgetRightWidth(blockIcon, 20, Unit.PCT, 24, Unit.PX);
			rightPanel.setWidgetTopHeight(bookmarkIcon, 0, Unit.PCT, 24,
					Unit.PX);
			rightPanel.setWidgetTopHeight(blockIcon, 0, Unit.PCT, 24, Unit.PX);
		}

		private void bookmarkChange() {
			if (state) {
				administrationService.deleteBookmark(profileId,
						new AsyncCallback<Void>() {
							public void onSuccess(Void result) {
								bookmarkIcon
										.setUrl("images/icons/bookmark.png");
								state = !state;
							}

							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().log(
										Level.WARNING, caught.getMessage());
							}
						});
			} else {
				administrationService.addBookmarkByProfileId(
						profileId, new AsyncCallback<Void>() {
							public void onSuccess(Void result) {
								bookmarkIcon
										.setUrl("images/icons/bookmark2.png");
								state = !state;
							}

							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().log(
										Level.WARNING, caught.getMessage());
							}
						});
			}
		}
	}

	private class SearchProfileDropBox extends ListBox {
		private ArrayList<SearchProfile> searchProfiles = new ArrayList<SearchProfile>();
		private SearchProfile selected = null;

		private SearchProfileDropBox() {
			addChangeHandler(new ChangeHandler() {
				public void onChange(ChangeEvent event) {
					selected = searchProfiles.get(((ListBox) event.getSource())
							.getSelectedIndex());
					loadSearchProfile(selected);
					loadMatches(selected);
				}
			});
		}

		/**
		 * Add a search profile and the associated item.
		 * 
		 * @param searchProfile
		 *            the search profile to add
		 */
		private void add(SearchProfile searchProfile) {
			searchProfiles.add(searchProfile);
			addItem(searchProfile.getName());
		}

		/**
		 * Remove a search profile and the associated item.
		 * 
		 * @param searchProfile
		 *            the searchProfile to remove
		 */
		private void remove(SearchProfile searchProfile) {
			int i = searchProfiles.indexOf(searchProfile);
			searchProfiles.remove(i);
			removeItem(i);
			selectItem(0);
		}

		/**
		 * Select an item and fire a change event.
		 * 
		 * @param index
		 *            the item to select
		 */
		private void selectItem(int index) {
			setItemSelected(index, true);
			DomEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
		}

		/**
		 * Get the selected searchProfile.
		 */
		private SearchProfile getSelected() {
			return searchProfiles.get(getSelectedIndex());
		}
	}

	private class SearchProfilePopup extends DialogBox {
		private TextBox nameInput = new TextBox();

		private SearchProfilePopup() {
			setText("Neues Suchprofil erstellen");
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setModal(false);

			LayoutPanel lPanel = new LayoutPanel();
			Label label = new Label("Name:");
			nameInput.setWidth("150px");
			nameInput.setMaxLength(45);

			Button cancelButton = new Button("Abbrechen");
			cancelButton.setHeight("32px");
			cancelButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					SearchProfilePopup.this.hide();
				}
			});

			Button okButton = new Button("OK");
			okButton.setHeight("32px");
			okButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					String name = nameInput.getText().isEmpty() ? "Standard"
							: nameInput.getText();

					SearchProfile searchProfile = new SearchProfile();
					searchProfile.setName(name);
					administrationService.createSearchProfile(searchProfile,
							new AsyncCallback<SearchProfile>() {
								public void onSuccess(SearchProfile result) {
									searchProfileDropBox.add(result);
									searchProfileDropBox
											.selectItem(searchProfileDropBox
													.getItemCount() - 1);
								}

								public void onFailure(Throwable caught) {
									ClientsideSettings.getLogger().log(
											Level.WARNING,
											caught.getMessage());
								}
							});

					SearchProfilePopup.this.hide();
				}
			});

			lPanel.add(label);
			lPanel.add(nameInput);
			lPanel.add(cancelButton);
			lPanel.add(okButton);

			lPanel.setWidgetTopHeight(label, 12, Unit.PX, 24, Unit.PX);
			lPanel.setWidgetLeftWidth(label, 8, Unit.PX, 60, Unit.PX);
			lPanel.setWidgetTopHeight(nameInput, 10, Unit.PX, 24, Unit.PX);
			lPanel.setWidgetRightWidth(nameInput, 8, Unit.PX, 160, Unit.PX);
			lPanel.setWidgetBottomHeight(cancelButton, 8, Unit.PX, 32, Unit.PX);
			lPanel.setWidgetRightWidth(cancelButton, 57, Unit.PX, 85, Unit.PX);
			lPanel.setWidgetBottomHeight(okButton, 8, Unit.PX, 32, Unit.PX);
			lPanel.setWidgetRightWidth(okButton, 8, Unit.PX, 41, Unit.PX);
			lPanel.setSize("236px", "90px");

			setWidget(lPanel);
		}
	}
}
