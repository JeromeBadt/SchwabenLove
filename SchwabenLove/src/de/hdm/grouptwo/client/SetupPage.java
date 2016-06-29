package de.hdm.grouptwo.client;

import java.sql.Date;
import java.util.logging.Level;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.AdministrationServiceAsync;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;

public class SetupPage extends ResizeComposite {
	private AdministrationServiceAsync administrationService = ClientsideSettings
			.getAdministrationService();

	private MainView mainView = null;
	private LoginInfo loginInfo = null;

	private ScrollPanel sPanel = new ScrollPanel();
	private LayoutPanel lPanel = new LayoutPanel();
	private VerticalPanel vPanel = new VerticalPanel();

	private TextBox firstNameInput = new TextBox();
	private TextBox lastNameInput = new TextBox();
	private TextBox dayInput = new TextBox();
	private TextBox monthInput = new TextBox();
	private TextBox yearInput = new TextBox();
	private RadioButton genderMaleRB = new RadioButton("gender", "m");
	private RadioButton genderFemaleRB = new RadioButton("gender", "w");
	private TextBox heightInput = new TextBox();
	private TextBox hairColorInput = new TextBox();
	private TextBox physiqueInput = new TextBox();
	private RadioButton smokerYesRB = new RadioButton("smoker", "Ja");
	private RadioButton smokerNoRB = new RadioButton("smoker", "Nein");
	private TextBox professionInput = new TextBox();
	private TextBox locationInput = new TextBox();
	private TextBox educationInput = new TextBox();
	private TextBox religionInput = new TextBox();

	private HTML errorOutput = new HTML();
	private StringBuilder error = new StringBuilder();

	public SetupPage(MainView mainView, LoginInfo loginInfo) {
		initWidget(sPanel);

		this.mainView = mainView;
		this.loginInfo = loginInfo;

		Label title = new Label("Neues Profil erstellen");
		title.setStyleName("title");

		HorizontalPanel attributePanel = new HorizontalPanel();
		vPanel.setStyleName("profile-setup-panel");

		VerticalPanel labelPanel = new VerticalPanel();
		labelPanel.setWidth("170px");
		labelPanel.add(new Label("Vorname: "));
		labelPanel.add(new Label("Nachname: "));
		labelPanel.add(new Label("Geburtsdatum: "));
		labelPanel.add(new Label("Geschlecht: "));
		labelPanel.add(new Label("Größe: "));
		labelPanel.add(new Label("Haarfarbe: "));
		labelPanel.add(new Label("Körperbau: "));
		labelPanel.add(new Label("Raucher: "));
		labelPanel.add(new Label("Beruf: "));
		labelPanel.add(new Label("Wohnort: "));
		labelPanel.add(new Label("Ausbildung: "));
		labelPanel.add(new Label("Religion: "));

		VerticalPanel inputPanel = new VerticalPanel();
		firstNameInput.setMaxLength(45);
		lastNameInput.setMaxLength(45);
		FlowPanel birthdatePanel = new FlowPanel();
		birthdatePanel.setStyleName("h-panel-padding");
		dayInput.getElement().setPropertyString("placeholder", "Tag");
		dayInput.setMaxLength(2);
		dayInput.setWidth("42px");
		monthInput.getElement().setPropertyString("placeholder", "Monat");
		monthInput.setMaxLength(2);
		monthInput.setWidth("42px");
		yearInput.getElement().setPropertyString("placeholder", "Jahr");
		yearInput.setMaxLength(4);
		yearInput.setWidth("42px");
		birthdatePanel.add(dayInput);
		birthdatePanel.add(monthInput);
		birthdatePanel.add(yearInput);
		FlowPanel genderPanel = new FlowPanel();
		genderPanel.setStyleName("h-panel-padding");
		genderPanel.add(genderMaleRB);
		genderPanel.add(genderFemaleRB);
		heightInput.getElement().setPropertyString("placeholder", "cm");
		FlowPanel smokerPanel = new FlowPanel();
		smokerPanel.setStyleName("h-panel-padding");
		smokerPanel.add(smokerYesRB);
		smokerPanel.add(smokerNoRB);
		professionInput.setMaxLength(45);
		locationInput.setMaxLength(45);
		educationInput.setMaxLength(45);
		religionInput.setMaxLength(45);

		inputPanel.add(firstNameInput);
		inputPanel.add(lastNameInput);
		inputPanel.add(birthdatePanel);
		inputPanel.add(genderPanel);
		inputPanel.add(heightInput);
		inputPanel.add(hairColorInput);
		inputPanel.add(physiqueInput);
		inputPanel.add(smokerPanel);
		inputPanel.add(professionInput);
		inputPanel.add(locationInput);
		inputPanel.add(educationInput);
		inputPanel.add(religionInput);

		attributePanel.add(labelPanel);
		attributePanel.add(inputPanel);
		vPanel.add(attributePanel);

		Button submitButton = new Button("Profil anlegen");
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				validateInput();
			}
		});

		errorOutput.setStyleName("error");

		lPanel.add(title);
		lPanel.add(vPanel);
		lPanel.add(submitButton);
		lPanel.add(errorOutput);

		lPanel.setWidgetTopHeight(title, 0, Unit.PX, 38, Unit.PX);
		lPanel.setWidgetTopHeight(vPanel, 38, Unit.PX, 385, Unit.PX);
		lPanel.setWidgetTopHeight(submitButton, 423, Unit.PX, 32, Unit.PX);
		lPanel.setWidgetTopHeight(errorOutput, 465, Unit.PX, 0, Unit.PX);

		sPanel.add(lPanel);
	}

	private void setProfile() {
		Profile p = new Profile();

		p.setEmail(loginInfo.getEmailAddress());
		p.setFirstName(firstNameInput.getText());
		p.setLastName(lastNameInput.getText());
		p.setBirthdate(getBirthdate());
		p.setGender(genderMaleRB.getFormValue());
		p.setHeight(Integer.parseInt(heightInput.getText()));
		p.setHairColor(hairColorInput.getText()); // ToDo: Selection
		p.setPhysique(physiqueInput.getText()); // ToDo: Selection
		p.setSmoker(smokerYesRB.getFormValue().equals("Ja"));
		p.setProfession(professionInput.getText());
		p.setLocation(locationInput.getText());
		p.setEducation(educationInput.getText()); // ToDo: Selection
		p.setReligion(religionInput.getText());

		administrationService.createProfile(p, new AsyncCallback<Void>() {
			public void onSuccess(Void result) {
				administrationService.setProfile(p.getEmail(),
						new AsyncCallback<Profile>() {
							public void onSuccess(Profile result) {
								mainView.addMenu(loginInfo);
							}

							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().log(
										Level.WARNING, caught.getMessage());
							}
						});
			}

			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().log(Level.WARNING,
						caught.getMessage());
			}
		});
	}

	/**
	 * Get a sql Date from the user input.
	 * 
	 * @return the sql Date
	 */
	private Date getBirthdate() {
		int day = Integer.parseInt(dayInput.getText());
		int month = Integer.parseInt(monthInput.getText());
		int year = Integer.parseInt(yearInput.getText());

		// Date.valueOf needs a string in the format of yyyy-[m]m-[d]d
		return Date.valueOf(year + "-" + month + "-" + day);
	}

	/**
	 * Method to validate the user input. Tests for empty input elements and to
	 * some extent format and sensible values.<br>
	 * Sets appropriate error messages if the input is invalide.
	 */
	private void validateInput() {
		error.setLength(0);

		// Validate first name
		if (firstNameInput.getText().length() == 0) {
			error.append("Bitte einen Vornamen angeben.<br>");
		}

		// Validate last name
		if (lastNameInput.getText().length() == 0) {
			error.append("Bitte einen Nachnamen angeben.<br>");
		}

		// Validate gender
		if (!genderMaleRB.getValue() && !genderFemaleRB.getValue()) {
			error.append("Bitte ein Geschlecht angeben.<br>");
		}

		// Validate height
		if (heightInput.getText().length() == 0) {
			error.append("Bitte Größe angeben.<br>");
		} else {
			try {
				int height = Integer.parseInt(heightInput.getText());

				if (height < 50 || height > 275) {
					error.append("Bitte eine gültige Größe angeben.<br>");
				}
			} catch (NumberFormatException e) {
				error.append("Bitte eine gültige Größe in cm angeben.<br>");
			}
		}

		// Validate hairColor
		// ToDo: Selection
		if (hairColorInput.getText().length() == 0) {
			error.append("Bitte eine Haarfarbe angeben.<br>");
		}

		// Validate physique
		// ToDo: Selection
		if (physiqueInput.getText().length() == 0) {
			error.append("Bitte einen Körperbau angeben.<br>");
		}

		// Validate smoker
		if (!smokerYesRB.getValue() && !smokerNoRB.getValue()) {
			error.append("Bitte angeben, ob Sie rauchen.<br>");
		}

		// Validate profession
		if (professionInput.getText().length() == 0) {
			error.append("Bitte einen Beruf angeben.<br>");
		}

		// Validate location
		if (locationInput.getText().length() == 0) {
			error.append("Bitte einen Wohnort angeben.<br>");
		}

		// Validate education
		// ToDo: Select: UNI, BHS, AHS, BMS, Lehre, Pflichtschule
		if (educationInput.getText().length() == 0) {
			error.append("Bitte geben Sie Ihre höchste abgeschlossene "
					+ "Ausbildung an.<br>");
		}

		// Validate religion
		if (religionInput.getText().length() == 0) {
			error.append("Bitte eine Religion angeben.<br>");
		}

		// Validate birthdate
		try {
			int day = Integer.parseInt(dayInput.getText());
			int month = Integer.parseInt(monthInput.getText());
			int year = Integer.parseInt(yearInput.getText());

			administrationService.validateDate(day, month, year,
					new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							if (result.booleanValue() == false) {
								error.append("Bitte ein korrektes Geburtsdatum "
										+ "angeben. (Sie müssen mindestens 18 "
										+ "Jahre alt sein, um diese Seite zu "
										+ "benutzen)<br>");
							}
							if (checkValid()) {
								setProfile();
							}
						}

						public void onFailure(Throwable caught) {
							ClientsideSettings.getLogger().log(Level.WARNING,
									caught.getMessage());
						}
					});
		} catch (NumberFormatException e) {
			error.append("Bitte ein gültiges Geburtsdatum angeben.<br>");
			checkValid();
		}
	}

	/**
	 * Helper method to check if any errors were set during validation.<br>
	 * Needs to be a seperate method because of asynchronous RPC in
	 * <code>validateInput()</code>.
	 * 
	 * @return <code>true</code> if all inputs are valid, <code>false</code> if
	 *         they aren't
	 */
	private boolean checkValid() {
		if (error.length() > 0) {
			String errorString = error.toString();
			int lines = errorString.split("<br>", -1).length - 1;

			if (lines <= 3) {
				errorOutput.setHTML(errorString);
			} else {
				lines = 1;
				errorOutput.setHTML("Bitte füllen Sie alle Felder "
						+ "korrekt aus.");
			}

			lPanel.setWidgetTopHeight(errorOutput, 465, Unit.PX, 20.8 * lines,
					Unit.PX);

			sPanel.setVerticalScrollPosition(sPanel
					.getMaximumVerticalScrollPosition());

			return false;
		}

		return true;
	}
}
