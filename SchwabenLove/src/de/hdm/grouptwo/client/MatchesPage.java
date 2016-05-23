package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.bo.Profile;

public class MatchesPage extends ContentPage {
	LayoutPanel hPanel = new LayoutPanel();
	VerticalPanel matchesPanel = new VerticalPanel();

	public MatchesPage() {
		super("Partnervorschläge");
		initWidget(hPanel);

		VerticalPanel searchProfilePanel = new VerticalPanel();
		
		hPanel.add(matchesPanel);
		hPanel.add(searchProfilePanel);
		
		hPanel.setWidgetLeftRight(matchesPanel, 0, Unit.PCT, 50, Unit.PCT);
		hPanel.setWidgetLeftRight(searchProfilePanel, 50, Unit.PCT, 0, Unit.PCT);
		
		Button btn1 = new Button("Insert demo profile");
		btn1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				administrationService
						.insertDemoProfile(new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								System.out.println("An error has occured");
							}

							public void onSuccess(String result) {
								matchesPanel.clear();
								matchesPanel.add(new Label(result));
							}
						});
			}
		});

		Button btn2 = new Button("Show all profiles");
		btn2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				getMatchesByProfileId(1);
			}
		});

		searchProfilePanel.add(btn1);
		searchProfilePanel.add(btn2);
	}

	@Override
	public void updatePage() {
		matchesPanel.clear();
		// getMatchesByProfileId(1);
	}

	public void getMatchesByProfileId(int profileId) {
		administrationService.getMatchesByProfileId(profileId,
				new AsyncCallback<ArrayList<Profile>>() {
					public void onFailure(Throwable caught) {
						System.out.println("An error has occured");
						ClientsideSettings.getLogger().log(Level.WARNING,
								"error");
					}

					public void onSuccess(ArrayList<Profile> result) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								"success");
						showMatches(result);
					}
				});
	}

	public void showMatches(ArrayList<Profile> matches) {
		matchesPanel.clear();

		//matchesPanel.add(new Label("test"));
		
		for (Profile match : matches) {
			HorizontalPanel matchPanel = new HorizontalPanel();
			// matchPanel.setBorderWidth(1);
			matchPanel.getElement().getStyle().setBorderWidth(1, Unit.PX);
			matchPanel.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);

			Image profilePicture = new Image("images/38.png");
			profilePicture.setWidth("96px");

			VerticalPanel vPanel1 = new VerticalPanel();
			vPanel1.setWidth("400px");

			HorizontalPanel hPanel1 = new HorizontalPanel();
			Label nameLabel = new Label(match.getFirstName() + " "
					+ match.getLastName());
			Label ageLabel = new Label(", " + match.getBirthdate());
			Label genderLabel = new Label(", " + match.getGender());
			hPanel1.add(nameLabel);
			hPanel1.add(ageLabel);
			hPanel1.add(genderLabel);

			HorizontalPanel hPanel2 = new HorizontalPanel();
			Label professionLabel = new Label(match.getProfession());
			Label locationLabel = new Label(", " + match.getLocation());
			hPanel2.add(professionLabel);
			hPanel2.add(locationLabel);

			Label heightLabel = new Label(Integer.toString(match.getHeight()));

			vPanel1.add(hPanel1);
			vPanel1.add(hPanel2);
			vPanel1.add(heightLabel);

			VerticalPanel vPanel2 = new VerticalPanel();

			HorizontalPanel hPanel3 = new HorizontalPanel();
			Label bookmarkLabel = new Label("Bookmark");
			Label blockLabel = new Label("Block");
			hPanel3.add(bookmarkLabel);
			hPanel3.add(blockLabel);

			Label similarityLabel = new Label("120");

			vPanel2.add(hPanel3);
			vPanel2.add(similarityLabel);

			matchPanel.add(profilePicture);
			matchPanel.add(vPanel1);
			matchPanel.add(vPanel2);

			matchesPanel.add(matchPanel);
		}
	}
}
