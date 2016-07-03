package de.hdm.grouptwo.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.grouptwo.shared.bo.Description;
import de.hdm.grouptwo.shared.bo.Information;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.Property;
import de.hdm.grouptwo.shared.bo.Selection;
import de.hdm.grouptwo.shared.bo.SelectionItem;
import de.hdm.grouptwo.shared.bo.SimilarityDegree;

public class ProfilePage extends ContentPage {
	private LayoutPanel lPanel = new LayoutPanel();
	private LayoutPanel attrPanel = new LayoutPanel();
	private LayoutPanel infoPanel = new LayoutPanel();

	private LoginInfo loginInfo = null;
	private Profile profile = null;

	private boolean canEdit = false;

	private Label nameLabel = null;
	private Label ageLabel = null;
	private Label genderLabel = null;
	private Label professionLabel = null;
	private Label locationLabel = null;
	private Label educationLabel = null;
	private Label smokerLabel = null;
	private Label religionLabel = null;
	private Label heightLabel = null;
	private Label physiqueLabel = null;
	private Label hairColorLabel = null;

	private TextBox nameInput = new TextBox();
	private TextBox ageInput = new TextBox();
	private LayoutPanel genderPanel = new LayoutPanel();
	private RadioButton genderMaleRB = new RadioButton("gender", "m");
	private RadioButton genderFemaleRB = new RadioButton("gender", "w");
	private TextBox professionInput = new TextBox();
	private TextBox locationInput = new TextBox();
	private ListBox educationList = new ListBox();
	private LayoutPanel smokerPanel = new LayoutPanel();
	private RadioButton smokerYesRB = new RadioButton("smoker", "Ja");
	private RadioButton smokerNoRB = new RadioButton("smoker", "Nein");
	private TextBox religionInput = new TextBox();
	private TextBox heightInput = new TextBox();
	private ListBox physiqueList = new ListBox();
	private ListBox hairColorList = new ListBox();

	private Image attrEditIcon[] = new Image[11];

	private Label similarityDegreeLbl = new Label();
	private Image bookmarkIcon = new Image();
	Image blockIcon = new Image("images/icons/block.png");
	private boolean bookmarkState = false;
	private boolean blockState = false;

	public ProfilePage(LoginInfo loginInfo) {
		super("Profil");

		this.loginInfo = loginInfo;
		canEdit = true;

		initWidget(lPanel);

		administrationService.getProfile(new AsyncCallback<Profile>() {
			public void onSuccess(Profile result) {
				profile = result;
				showProfile();
				showEdit();
			}

			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().log(Level.WARNING,
						caught.getMessage());
			}
		});
	}

	public ProfilePage(Profile profile) {
		super("Profil");

		this.profile = profile;
		canEdit = false;

		initWidget(lPanel);

		Menu.clearMenuItemStyles();
		MainView.getContentPanel().remove(this);

		showProfile();
		showExtra();
	}

	@Override
	public void updatePage() {
	}

	public void showProfile() {
		lPanel.setStyleName("profile-page");
		lPanel.add(attrPanel);
		lPanel.add(infoPanel);

		Image profilePicture = new Image("images/default.png");
		profilePicture.addStyleName("profile-picture");
		profilePicture.setWidth("256px");

		nameLabel = new Label(formatName());
		ageLabel = new Label(Integer.toString(profile.getAge()));
		genderLabel = new Label(profile.getGender());
		professionLabel = new Label(profile.getProfession());
		locationLabel = new Label(profile.getLocation());
		educationLabel = new Label(profile.getEducation());
		smokerLabel = new Label(formatSmoker());
		religionLabel = new Label(profile.getReligion());
		heightLabel = new Label(formatHeight());
		physiqueLabel = new Label(profile.getPhysique());
		hairColorLabel = new Label(profile.getHairColor());

		nameInput.setWidth("140px");
		nameInput.setMaxLength(91);
		nameInput.setText(profile.getFirstName() + " " +
				profile.getLastName());
		nameInput.setVisible(false);

		ageInput.setWidth("140px");
		ageInput.setMaxLength(45);
		ageInput.setText(formateBirthdate());
		ageInput.setVisible(false);

		genderPanel.setWidth("140px");
		genderMaleRB.setValue(genderLabel.getText().equals("m"));
		genderFemaleRB.setValue(genderLabel.getText().equals("w"));
		genderPanel.add(genderMaleRB);
		genderPanel.add(genderFemaleRB);
		genderPanel
				.setWidgetLeftWidth(genderFemaleRB, 60, Unit.PX, 80, Unit.PX);
		genderPanel.setVisible(false);

		professionInput.setWidth("140px");
		professionInput.setMaxLength(45);
		professionInput.setText(professionLabel.getText());
		professionInput.setVisible(false);

		locationInput.setWidth("140px");
		locationInput.setMaxLength(45);
		locationInput.setText(locationLabel.getText());
		locationInput.setVisible(false);

		educationList.setWidth("140px");
		// set selected
		educationList.addItem("Studium");
		educationList.addItem("Fachschule");
		educationList.addItem("Berufsausbildung");
		educationList.addItem("Hochschulreife");
		educationList.addItem("Mittlere Reife");
		educationList.addItem("Hauptschulabschluss");
		educationList.setVisible(false);

		smokerPanel.setWidth("140px");
		smokerYesRB.setValue(smokerLabel.getText().equals("Raucher"));
		smokerNoRB.setValue(smokerLabel.getText().equals("Nichtraucher"));
		smokerPanel.add(smokerYesRB);
		smokerPanel.add(smokerNoRB);
		smokerPanel
				.setWidgetLeftWidth(smokerNoRB, 60, Unit.PX, 80, Unit.PX);
		smokerPanel.setVisible(false);

		religionInput.setWidth("140px");
		religionInput.setMaxLength(45);
		religionInput.setText(religionLabel.getText());
		religionInput.setVisible(false);

		heightInput.setWidth("140px");
		heightInput.setMaxLength(45);
		heightInput.setText(Integer.toString(profile.getHeight()));
		heightInput.setVisible(false);

		physiqueList.setWidth("140px");
		// set selected
		physiqueList.addItem("Dünn");
		physiqueList.addItem("Fit");
		physiqueList.addItem("Muskulös");
		physiqueList.addItem("Normal");
		physiqueList.addItem("Kurvig");
		physiqueList.addItem("Vollschlank");
		physiqueList.setVisible(false);

		hairColorList.setWidth("140px");
		// set selected
		hairColorList.addItem("Blond");
		hairColorList.addItem("Dunkelblond");
		hairColorList.addItem("Rot");
		hairColorList.addItem("Rotbraun");
		hairColorList.addItem("Hellbraun");
		hairColorList.addItem("Braun");
		hairColorList.addItem("Dunkelbraun");
		hairColorList.addItem("Schwarz");
		hairColorList.addItem("Grau");
		hairColorList.setVisible(false);

		attrPanel.add(profilePicture);

		attrPanel.add(nameLabel);
		attrPanel.add(ageLabel);
		attrPanel.add(genderLabel);
		attrPanel.add(professionLabel);
		attrPanel.add(locationLabel);
		attrPanel.add(educationLabel);
		attrPanel.add(smokerLabel);
		attrPanel.add(religionLabel);
		attrPanel.add(heightLabel);
		attrPanel.add(physiqueLabel);
		attrPanel.add(hairColorLabel);

		attrPanel.add(nameInput);
		attrPanel.add(ageInput);
		attrPanel.add(genderPanel);
		attrPanel.add(professionInput);
		attrPanel.add(locationInput);
		attrPanel.add(educationList);
		attrPanel.add(smokerPanel);
		attrPanel.add(religionInput);
		attrPanel.add(heightInput);
		attrPanel.add(physiqueList);
		attrPanel.add(hairColorList);

		attrPanel.setWidgetLeftWidth(nameLabel, 279, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(nameLabel, 0, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(ageLabel, 279, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(ageLabel, 24, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(genderLabel, 279, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(genderLabel, 48, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(professionLabel, 279, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(professionLabel, 72, Unit.PX, 24,
				Unit.PX);
		attrPanel.setWidgetLeftWidth(locationLabel, 279, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(locationLabel, 96, Unit.PX, 24,
				Unit.PX);
		attrPanel
				.setWidgetLeftWidth(educationLabel, 279, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(educationLabel, 120, Unit.PX, 24,
				Unit.PX);
		attrPanel.setWidgetLeftWidth(smokerLabel, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(smokerLabel, 0, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(religionLabel, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(religionLabel, 24, Unit.PX, 24,
				Unit.PX);
		attrPanel.setWidgetLeftWidth(heightLabel, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(heightLabel, 48, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(physiqueLabel, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(physiqueLabel, 72, Unit.PX, 24,
				Unit.PX);
		attrPanel
				.setWidgetLeftWidth(hairColorLabel, 490, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(hairColorLabel, 96, Unit.PX, 24,
				Unit.PX);

		attrPanel.setWidgetLeftWidth(nameInput, 279, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(nameInput, 0, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(ageInput, 279, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(ageInput, 24, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(genderPanel, 279, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(genderPanel, 48, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(professionInput, 279, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(professionInput, 72, Unit.PX, 24,
				Unit.PX);
		attrPanel.setWidgetLeftWidth(locationInput, 279, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(locationInput, 96, Unit.PX, 24,
				Unit.PX);
		attrPanel
				.setWidgetLeftWidth(educationList, 279, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(educationList, 120, Unit.PX, 24,
				Unit.PX);
		attrPanel.setWidgetLeftWidth(smokerPanel, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(smokerPanel, 0, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(religionInput, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(religionInput, 24, Unit.PX, 24,
				Unit.PX);
		attrPanel.setWidgetLeftWidth(heightInput, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(heightInput, 48, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(physiqueList, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(physiqueList, 72, Unit.PX, 24,
				Unit.PX);
		attrPanel
				.setWidgetLeftWidth(hairColorList, 490, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(hairColorList, 96, Unit.PX, 24,
				Unit.PX);

		lPanel.setWidgetLeftRight(infoPanel, 0, Unit.PX, 10, Unit.PX);

		administrationService.getInformationByProfileId(
				profile.getId(),
				new AsyncCallback<ArrayList<Information>>() {
					public void onSuccess(ArrayList<Information> result) {
						showInformation(result);
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}

	private void showInformation(ArrayList<Information> information) {
		int offset = 0;
		for (Property p : ClientsideSettings.getProperties()) {
			for (Information info : information) {
				if (info.getPropertyId() == p.getId()) {
					InformationWidget infoWidget = new InformationWidget(info,
							p);

					infoPanel.add(infoWidget);
					infoPanel.setWidgetTopHeight(infoWidget, offset, Unit.PX,
							96, Unit.PX);
					offset += 110;
				}
			}
		}

		lPanel.setWidgetTopHeight(infoPanel, 286, Unit.PX, offset,
				Unit.PX);
	}

	private void showEdit() {
		Image deleteIcon = new Image("images/icons/trash.png");
		deleteIcon.addStyleName("img-button");
		deleteIcon.setWidth("24px");
		deleteIcon.setTitle("Profil löschen");
		deleteIcon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DeleteProfilePopup deleteProfilePopup = new DeleteProfilePopup();
				deleteProfilePopup.center();
			}
		});

		attrPanel.add(deleteIcon);

		for (int i = 0; i < 11; i++) {
			attrEditIcon[i] = new Image("images/icons/edit.png");
			attrEditIcon[i].addStyleName("img-button");
			attrEditIcon[i].setWidth("16px");
			attrEditIcon[i].setTitle("Attribut editieren");
			attrPanel.add(attrEditIcon[i]);

			if (i < 6) {
				attrPanel.setWidgetLeftWidth(attrEditIcon[i], 435, Unit.PX, 16,
						Unit.PX);
				attrPanel.setWidgetTopHeight(attrEditIcon[i], 24 * i + 3,
						Unit.PX,
						21, Unit.PX);
			} else {
				attrPanel.setWidgetLeftWidth(attrEditIcon[i], 646, Unit.PX, 16,
						Unit.PX);
				attrPanel.setWidgetTopHeight(attrEditIcon[i], 24 * (i - 6) + 3,
						Unit.PX, 21, Unit.PX);
			}
		}

		attrEditIcon[0].addClickHandler(new EditClickHandler(nameLabel,
				nameInput, 0));
		attrEditIcon[1].addClickHandler(new EditClickHandler(ageLabel,
				ageInput, 1));
		attrEditIcon[2].addClickHandler(new EditClickHandler(genderLabel,
				genderPanel, 2));
		attrEditIcon[3].addClickHandler(new EditClickHandler(professionLabel,
				professionInput, 3));
		attrEditIcon[4].addClickHandler(new EditClickHandler(locationLabel,
				locationInput, 4));
		attrEditIcon[5].addClickHandler(new EditClickHandler(educationLabel,
				educationList, 5));
		attrEditIcon[6].addClickHandler(new EditClickHandler(smokerLabel,
				smokerPanel, 6));
		attrEditIcon[7].addClickHandler(new EditClickHandler(religionLabel,
				religionInput, 7));
		attrEditIcon[8].addClickHandler(new EditClickHandler(heightLabel,
				heightInput, 8));
		attrEditIcon[9].addClickHandler(new EditClickHandler(physiqueLabel,
				physiqueList, 9));
		attrEditIcon[10].addClickHandler(new EditClickHandler(hairColorLabel,
				hairColorList, 10));

		attrPanel.setWidgetRightWidth(deleteIcon, 10, Unit.PX, 24, Unit.PX);
	}

	private void showExtra() {
		LayoutPanel rightPanel = new LayoutPanel();

		ClientsideSettings.getAdministrationService()
				.getSimilarityDegreeByProfileId(profile.getId(),
						new AsyncCallback<SimilarityDegree>() {
							public void onSuccess(SimilarityDegree result) {
								showSimilarityDegree(result);
							}

							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().log(
										Level.WARNING, caught.getMessage());
							}
						});

		administrationService.checkBookmarked(profile.getId(),
				new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						bookmarkState = result;
						if (bookmarkState) {
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

		administrationService.checkBlocked(profile.getId(),
				new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean result) {
						blockState = result;
						// Window.alert(Boolean.toString(blockState));
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});

		blockIcon.addStyleName("img-button");
		blockIcon.setWidth("24px");
		blockIcon.setTitle("Profil blocken");
		blockIcon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				blockChange();
			}
		});

		LayoutPanel heartPanel = new LayoutPanel();
		Image heartIcon = new Image("images/icons/heart.png");
		heartIcon.setWidth("72px");
		similarityDegreeLbl.setStyleName("similarity-degree-label");

		heartPanel.add(heartIcon);
		heartPanel.add(similarityDegreeLbl);
		rightPanel.add(heartPanel);
		rightPanel.add(bookmarkIcon);
		rightPanel.add(blockIcon);
		attrPanel.add(rightPanel);

		heartPanel.setWidgetLeftRight(heartIcon, 14, Unit.PX, 14, Unit.PX);
		rightPanel.setWidgetLeftWidth(bookmarkIcon, 20, Unit.PCT, 24,
				Unit.PX);
		rightPanel
				.setWidgetRightWidth(blockIcon, 20, Unit.PCT, 24, Unit.PX);
		rightPanel.setWidgetTopHeight(bookmarkIcon, 0, Unit.PCT, 24,
				Unit.PX);
		rightPanel.setWidgetTopHeight(blockIcon, 0, Unit.PCT, 24, Unit.PX);
		rightPanel.setWidgetTopBottom(heartPanel, 22, Unit.PX, 0, Unit.PX);
		attrPanel.setWidgetRightWidth(rightPanel, 0, Unit.PX, 100, Unit.PX);

	}

	private void showSimilarityDegree(SimilarityDegree similarityDegree) {
		similarityDegreeLbl.setText(Integer.toString(similarityDegree
				.getScore()));
	}

	private void bookmarkChange() {
		if (bookmarkState) {
			administrationService.deleteBookmark(profile.getId(),
					new AsyncCallback<Void>() {
						public void onSuccess(Void result) {
							bookmarkIcon
									.setUrl("images/icons/bookmark.png");
							bookmarkState = !bookmarkState;
						}

						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().log(
									Level.WARNING, caught.getMessage());
						}
					});
		} else {
			administrationService.addBookmarkByProfileId(
					profile.getId(), new AsyncCallback<Void>() {
						public void onSuccess(Void result) {
							bookmarkIcon
									.setUrl("images/icons/bookmark2.png");
							bookmarkState = !bookmarkState;
						}

						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().log(
									Level.WARNING, caught.getMessage());
						}
					});
		}
	}

	private void blockChange() {
		if (!blockState) {
			administrationService.addBlockByProfileId(
					profile.getId(), new AsyncCallback<Void>() {
						public void onSuccess(Void result) {
							blockState = !blockState;
						}

						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().log(
									Level.WARNING, caught.getMessage());
						}
					});
		}
	}

	private String formatName() {
		return profile.getFirstName() + " "
				+ profile.getLastName().substring(0, 1) + ".";
	}

	private String formateBirthdate() {
		return DateTimeFormat.getFormat("dd.MM.yyyy").format(
				profile.getBirthdate());
	}

	private String formatSmoker() {
		return Boolean.parseBoolean(
				profile.getSmoker()) ? "Raucher" : "Nichtraucher";
	}

	private String formatHeight() {
		return new BigDecimal((float) profile.getHeight() / 100).setScale(2,
				BigDecimal.ROUND_HALF_UP) + " m";
	}

	private class InformationWidget extends ResizeComposite {
		private LayoutPanel lPanel = new LayoutPanel();
		private LayoutPanel infoPanel = new LayoutPanel();

		private Information information = null;
		private int type = 0;

		private Label label = new Label();
		private Widget input = null;
		private Image icon = new Image("images/icons/edit.png");

		private InformationWidget(Information i, Property p) {
			information = i;

			initWidget(lPanel);

			Label explanationLabel = new Label(p.getExplanation());
			label.setText(i.getInputText());

			infoPanel.add(label);
			infoPanel.setWidgetLeftRight(label, 6, Unit.PX, 6, Unit.PX);
			infoPanel.setWidgetTopBottom(label, 6, Unit.PX, 6, Unit.PX);

			LayoutPanel sPanel = new LayoutPanel();
			sPanel.addStyleName("info-panel");
			sPanel.add(infoPanel);
			sPanel.setWidgetLeftRight(infoPanel, 0, Unit.PX, 1, Unit.PX);

			lPanel.add(explanationLabel);
			lPanel.add(sPanel);

			lPanel.setWidgetLeftRight(explanationLabel, 0, Unit.PX, 30,
					Unit.PX);
			lPanel.setWidgetTopHeight(sPanel, 24, Unit.PX, 72, Unit.PX);

			if (!canEdit) {
				return;
			}

			if (p instanceof Description) {
				input = new TextArea();
				((TextArea) input).setText(label.getText());
				input.setHeight("56px");
				input.setWidth("100%");
				input.addStyleName("no-resize");

				type = 0;
				loadClickHandler();
			} else if (p instanceof Selection) {
				type = 1;
				input = new ListBox();

				administrationService.getSelectionItems(p.getId(),
						new AsyncCallback<ArrayList<SelectionItem>>() {
							public void onSuccess(
									ArrayList<SelectionItem> result) {
								addListItems(result);
								loadClickHandler();
							}

							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().log(
										Level.WARNING, caught.getMessage());
							}
						});
			}
		}

		private void addListItems(ArrayList<SelectionItem> selectionItems) {
			for (SelectionItem si : selectionItems) {
				((ListBox) input).addItem(si.getName());
			}
		}

		private void loadClickHandler() {
			input.setVisible(false);

			infoPanel.add(input);

			if (type == 0) {
				infoPanel.setWidgetLeftRight(input, 1, Unit.PX, 1, Unit.PX);
				infoPanel.setWidgetTopBottom(input, 1, Unit.PX, 1, Unit.PX);
			} else if (type == 1) {
				infoPanel.setWidgetLeftRight(input, 1, Unit.PX, 1, Unit.PX);
				infoPanel.setWidgetTopHeight(input, 1, Unit.PX, 24, Unit.PX);
			}

			icon.addStyleName("img-button");
			icon.setWidth("16px");
			icon.setTitle("Information editieren");

			lPanel.add(icon);
			lPanel.setWidgetRightWidth(icon, 10, Unit.PX, 16, Unit.PX);
			lPanel.setWidgetTopHeight(icon, 0, Unit.PX, 16, Unit.PX);

			icon.addClickHandler(new ClickHandler() {
				private boolean state = false;

				public void onClick(ClickEvent event) {
					if (state) {
						String inputText = "";
						if (type == 0) {
							inputText = ((TextArea) input).getText();
						} else {
							inputText = ((ListBox) input).getSelectedItemText();
						}
						information.setInputText(inputText);

						administrationService.updateInformation(information,
								new AsyncCallback<Void>() {
									public void onSuccess(Void result) {
										label.setText(information
												.getInputText());
									}

									public void onFailure(Throwable caught) {
										ClientsideSettings.getLogger().log(
												Level.WARNING,
												caught.getMessage());
									}
								});
						icon.setUrl("images/icons/edit.png");
					} else {
						icon.setUrl("images/icons/checkmark.png");
					}

					label.setVisible(state);
					input.setVisible(!state);

					state = !state;
				}
			});
		}
	}

	private class EditClickHandler implements ClickHandler {
		// True if currently editing
		private boolean state = false;
		private Label label = null;
		private Widget input = null;
		private int i = 0;

		private ErrorPopup errorPopup = new ErrorPopup();

		private EditClickHandler(Label label, Widget input, int i) {
			this.label = label;
			this.input = input;
			this.i = i;
		}

		@Override
		public void onClick(ClickEvent event) {
			if (state) {
				if (i == 0) {
					RegExp regExp = RegExp.compile("(.*) (.*)");
					MatchResult m = regExp.exec(nameInput.getText());
					if (m != null && m.getGroupCount() == 3) {
						profile.setFirstName(m.getGroup(1));
						profile.setLastName(m.getGroup(2));
						updateProfile();
					} else {
						errorPopup.showError("Bitte geben Sie Ihren Vor- und "
								+ "Nachnahmen an.");
					}
				} else if (i == 1) {
					RegExp regExp = RegExp
							.compile("(\\d{1,2}).(\\d{1,2}).(\\d{4})$");
					MatchResult m = regExp.exec(ageInput.getText());
					if (m != null && m.getGroupCount() == 4) {
						final int day = Integer.parseInt(m.getGroup(1));
						final int month = Integer.parseInt(m.getGroup(2));
						final int year = Integer.parseInt(m.getGroup(3));

						administrationService.validateDate(day, month, year,
								new AsyncCallback<Boolean>() {
									public void onSuccess(Boolean result) {
										if (result) {
											profile.setBirthdate(java.sql.Date
													.valueOf(year + "-" + month
															+ "-" + day));
											updateProfile();
										} else {
											errorPopup
													.showError("Bitte ein korrektes "
															+ "Geburtsdatum angeben.<br>"
															+ "(Sie müssen mindestens "
															+ "18 Jahre alt sein, um "
															+ "diese Seite zu benutzen)");
										}
									}

									public void onFailure(Throwable caught) {
										ClientsideSettings.getLogger().log(
												Level.WARNING,
												caught.getMessage());
									}
								});
					} else {
						errorPopup.showError("Bitte geben Sie ein gültiges "
								+ "Geburtsdatum an.");
					}
				} else if (i == 2) {
					profile.setGender(genderMaleRB.getValue() ? "m" : "w");
					updateProfile();
				} else if (i == 3) {
					if (professionInput.getText().isEmpty()) {
						errorPopup.showError("Bitte geben Sie einen Beruf an.");
					} else {
						profile.setProfession(professionInput.getText());
						updateProfile();
					}
				} else if (i == 4) {
					if (locationInput.getText().isEmpty()) {
						errorPopup
								.showError("Bitte geben Sie einen Wohnort an.");
					} else {
						profile.setLocation(locationInput.getText());
						updateProfile();
					}
				} else if (i == 5) {
					profile.setEducation(educationList.getSelectedItemText());
					updateProfile();
				} else if (i == 6) {
					profile.setSmoker(Boolean.toString(smokerYesRB.getValue()));
					updateProfile();
				} else if (i == 7) {
					if (religionInput.getText().isEmpty()) {
						errorPopup
								.showError("Bitte geben Sie eine Religion an.");
					} else {
						profile.setReligion(religionInput.getText());
						updateProfile();
					}
				} else if (i == 8) {
					// Validate height
					if (heightInput.getText().length() == 0) {
						errorPopup.showError("Bitte Größe angeben.");
					} else {
						try {
							int height = Integer
									.parseInt(heightInput.getText());

							if (height < 50 || height > 275) {
								errorPopup
										.showError("Bitte eine gültige Größe angeben.");
							}

							profile.setHeight(height);
							updateProfile();
						} catch (NumberFormatException e) {
							errorPopup
									.showError("Bitte eine gültige Größe in cm angeben.");
						}
					}
				} else if (i == 9) {
					profile.setPhysique(physiqueList.getSelectedItemText());
					updateProfile();
				} else if (i == 10) {
					profile.setHairColor(hairColorList.getSelectedItemText());
					updateProfile();
				}
			} else {
				attrEditIcon[i].setUrl("images/icons/checkmark.png");
				label.setVisible(state);
				input.setVisible(!state);

				state = !state;
			}
		}

		private void updateProfile() {
			administrationService.updateProfile(profile,
					new AsyncCallback<Profile>() {
						public void onSuccess(Profile result) {
							profile = result;
							updateLabel();
							attrEditIcon[i].setUrl("images/icons/edit.png");
						}

						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().log(
									Level.WARNING, caught.getMessage());
						}
					});
		}

		private void updateLabel() {
			if (i == 0) {
				label.setText(formatName());
			} else if (i == 1) {
				label.setText(Integer.toString(profile.getAge()));
			} else if (i == 2) {
				label.setText(profile.getGender());
			} else if (i == 3) {
				label.setText(profile.getProfession());
			} else if (i == 4) {
				label.setText(profile.getLocation());
			} else if (i == 5) {
				label.setText(profile.getEducation());
			} else if (i == 6) {
				label.setText(formatSmoker());
			} else if (i == 7) {
				label.setText(profile.getReligion());
			} else if (i == 8) {
				label.setText(formatHeight());
			} else if (i == 9) {
				label.setText(profile.getPhysique());
			} else if (i == 10) {
				label.setText(profile.getHairColor());
			}

			label.setVisible(state);
			input.setVisible(!state);

			state = !state;
		}
	}

	private class DeleteProfilePopup extends DialogBox {
		private DeleteProfilePopup() {
			setText("Profil löschen");
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setModal(false);

			LayoutPanel lPanel = new LayoutPanel();
			Label label = new Label("Sind Sie sicher, dass Sie Ihr Profil "
					+ "permanent löschen möchten?");

			Button cancelButton = new Button("Abbrechen");
			cancelButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					DeleteProfilePopup.this.hide();
				}
			});

			Button deleteButton = new Button("Löschen");
			deleteButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					administrationService
							.deleteProfile(new AsyncCallback<Void>() {
								public void onSuccess(Void result) {
									Window.Location.assign(loginInfo
											.getLogoutUrl());
								}

								public void onFailure(Throwable caught) {
									ClientsideSettings.getLogger().log(
											Level.WARNING, caught.getMessage());
								}
							});

					DeleteProfilePopup.this.hide();
				}
			});

			lPanel.add(label);
			lPanel.add(cancelButton);
			lPanel.add(deleteButton);

			lPanel.setWidgetTopHeight(label, 12, Unit.PX, 50, Unit.PX);
			lPanel.setWidgetLeftRight(label, 8, Unit.PX, 8, Unit.PX);
			lPanel.setWidgetBottomHeight(cancelButton, 8, Unit.PX, 32, Unit.PX);
			lPanel.setWidgetRightWidth(cancelButton, 8, Unit.PX, 85, Unit.PX);
			lPanel.setWidgetBottomHeight(deleteButton, 8, Unit.PX, 32, Unit.PX);
			lPanel.setWidgetRightWidth(deleteButton, 101, Unit.PX, 72, Unit.PX);
			lPanel.setSize("236px", "105px");

			setWidget(lPanel);
		}
	}
}