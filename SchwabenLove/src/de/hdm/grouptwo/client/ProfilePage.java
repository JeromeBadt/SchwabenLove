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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.grouptwo.shared.bo.Information;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.Property;

public class ProfilePage extends ContentPage {
	private LayoutPanel lPanel = new LayoutPanel();
	private LayoutPanel attrPanel = new LayoutPanel();
	private LayoutPanel infoPanel = new LayoutPanel();

	private LoginInfo loginInfo = null;
	private Profile profile = null;
	private ArrayList<Property> properties = null;

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
	private TextBox educationInput = new TextBox();
	private TextBox smokerInput = new TextBox();
	private TextBox religionInput = new TextBox();
	private TextBox heightInput = new TextBox();
	private TextBox physiqueInput = new TextBox();
	private TextBox hairColorInput = new TextBox();

	private Image attrEditIcon[] = new Image[11];

	public ProfilePage(LoginInfo loginInfo, ArrayList<Property> properties) {
		super("Profil");
		initWidget(lPanel);

		lPanel.setStyleName("profile-page");

		this.loginInfo = loginInfo;
		this.properties = properties;

		lPanel.add(attrPanel);
		lPanel.add(infoPanel);

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

	public ProfilePage(int id) {
		super("Profil");
		initWidget(lPanel);
	}

	@Override
	public void updatePage() {
	}

	public void showProfile() {
		Image profilePicture = new Image("images/38.png");
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
				.setWidgetLeftWidth(genderFemaleRB, 70, Unit.PX, 70, Unit.PX);
		genderPanel.setVisible(false);

		professionInput.setWidth("140px");
		professionInput.setMaxLength(45);
		professionInput.setText(professionLabel.getText());
		professionInput.setVisible(false);

		locationInput.setWidth("140px");
		locationInput.setMaxLength(45);
		locationInput.setText(locationLabel.getText());
		locationInput.setVisible(false);

		educationInput.setWidth("140px");
		educationInput.setMaxLength(45);
		educationInput.setText(educationLabel.getText());
		educationInput.setVisible(false);

		smokerInput.setWidth("140px");
		smokerInput.setMaxLength(45);
		smokerInput.setText(profile.getSmoker());
		smokerInput.setVisible(false);

		religionInput.setWidth("140px");
		religionInput.setMaxLength(45);
		religionInput.setText(religionLabel.getText());
		religionInput.setVisible(false);

		heightInput.setWidth("140px");
		heightInput.setMaxLength(45);
		heightInput.setText(Integer.toString(profile.getHeight()));
		heightInput.setVisible(false);

		physiqueInput.setWidth("140px");
		physiqueInput.setMaxLength(45);
		physiqueInput.setText(physiqueLabel.getText());
		physiqueInput.setVisible(false);

		hairColorInput.setWidth("140px");
		hairColorInput.setMaxLength(45);
		hairColorInput.setText(hairColorLabel.getText());
		hairColorInput.setVisible(false);

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
		attrPanel.add(educationInput);
		attrPanel.add(smokerInput);
		attrPanel.add(religionInput);
		attrPanel.add(heightInput);
		attrPanel.add(physiqueInput);
		attrPanel.add(hairColorInput);

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
				.setWidgetLeftWidth(educationInput, 279, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(educationInput, 120, Unit.PX, 24,
				Unit.PX);
		attrPanel.setWidgetLeftWidth(smokerInput, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(smokerInput, 0, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(religionInput, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(religionInput, 24, Unit.PX, 24,
				Unit.PX);
		attrPanel.setWidgetLeftWidth(heightInput, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(heightInput, 48, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetLeftWidth(physiqueInput, 490, Unit.PX, 150,
				Unit.PX);
		attrPanel.setWidgetTopHeight(physiqueInput, 72, Unit.PX, 24,
				Unit.PX);
		attrPanel
				.setWidgetLeftWidth(hairColorInput, 490, Unit.PX, 150, Unit.PX);
		attrPanel.setWidgetTopHeight(hairColorInput, 96, Unit.PX, 24,
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

	private void showInformation(ArrayList<Information> information) {
		int offset = 0;
		for (Property p : properties) {
			Label propertyLabel = new Label(p.getExplanation());
			infoPanel.add(propertyLabel);
			infoPanel.setWidgetTopHeight(propertyLabel, offset, Unit.PX, 24,
					Unit.PX);
			infoPanel
					.setWidgetLeftRight(propertyLabel, 0, Unit.PX, 30, Unit.PX);
			offset += 24;

			for (Information info : information) {
				if (info.getPropertyId() == p.getId()) {
					SimpleLayoutPanel sPanel = new SimpleLayoutPanel();
					sPanel.addStyleName("info-panel");
					Label infoLabel = new Label("Information: " + info.getId());

					sPanel.add(infoLabel);
					infoPanel.add(sPanel);

					infoPanel.setWidgetTopHeight(sPanel, offset, Unit.PX,
							72, Unit.PX);
					offset += 86;
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
				educationInput, 5));
		attrEditIcon[6].addClickHandler(new EditClickHandler(smokerLabel,
				smokerInput, 6));
		attrEditIcon[7].addClickHandler(new EditClickHandler(religionLabel,
				religionInput, 7));
		attrEditIcon[8].addClickHandler(new EditClickHandler(heightLabel,
				heightInput, 8));
		attrEditIcon[9].addClickHandler(new EditClickHandler(physiqueLabel,
				physiqueInput, 9));
		attrEditIcon[10].addClickHandler(new EditClickHandler(hairColorLabel,
				hairColorInput, 10));

		for (int i = 0; i < properties.size(); i++) {
			Image img = new Image("images/icons/edit.png");
			img.addStyleName("img-button");
			img.setWidth("16px");
			img.setTitle("Attribut editieren");

			infoPanel.add(img);
			infoPanel.setWidgetRightWidth(img, 10, Unit.PX, 16, Unit.PX);
			infoPanel
					.setWidgetTopHeight(img, i * 110 + 4, Unit.PX, 16, Unit.PX);
		}

		attrPanel.setWidgetRightWidth(deleteIcon, 10, Unit.PX, 24, Unit.PX);
		attrPanel.setWidgetRightWidth(deleteIcon, 10, Unit.PX, 24, Unit.PX);
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
					RegExp regExp = RegExp.compile("(.* )(.*)");
					MatchResult m = regExp.exec(((TextBox) input).getText());
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
					MatchResult m = regExp.exec(((TextBox) input).getText());
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
					String gender = ((RadioButton) ((LayoutPanel) input)
							.getWidget(0)).getValue() ? "m" : "w";
					profile.setGender(gender);
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
			}

			label.setVisible(state);
			input.setVisible(!state);

			state = !state;
		}
	}

	/**
	 * <code>ErrorPopup</code> is an extension of <code>DialogBox</code> and
	 * used to inform the user of invalid inputs. <br>
	 * This class is used instead of <code>Window.alert()</code> because alert()
	 * sometimes causes Firefox to hang.<br>
	 * 
	 * @see <a href="https://bugzilla.mozilla.org/show_bug.cgi?id=1180005">
	 *      https://bugzilla.mozilla.org/show_bug.cgi?id=1180005</a>
	 */
	private class ErrorPopup extends DialogBox {
		private HTML errorHTML = new HTML();

		private ErrorPopup() {
			setText("Fehler");
			setAnimationEnabled(true);
			setGlassEnabled(true);
			setModal(false);

			VerticalPanel vPanel = new VerticalPanel();
			LayoutPanel lPanel = new LayoutPanel();

			Button okButton = new Button("OK");
			okButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					ErrorPopup.this.hide();
				}
			});

			lPanel.add(okButton);

			lPanel.setWidgetBottomHeight(okButton, 8, Unit.PX, 32, Unit.PX);
			lPanel.setWidgetRightWidth(okButton, 8, Unit.PX, 41, Unit.PX);

			vPanel.add(errorHTML);
			vPanel.add(lPanel);

			lPanel.setSize("100%", "48px");

			setWidget(vPanel);
		}

		/**
		 * Set an error message and show the popup.
		 * 
		 * @param error
		 *            The message to show
		 */
		public void showError(String error) {
			errorHTML.setHTML(error);
			center();
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