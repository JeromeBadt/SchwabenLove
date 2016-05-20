package de.hdm.grouptwo.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.SchwabenAdministrationAsync;
import de.hdm.grouptwo.shared.bo.Profile;

public class MatchesPage extends Composite {
	SchwabenAdministrationAsync schwabenAdministration = ClientsideSettings
			.getSchwabenAdministration();

	HorizontalPanel hPanel = new HorizontalPanel();
	VerticalPanel matchesPanel = new VerticalPanel();

	public MatchesPage() {
		initWidget(hPanel);

		VerticalPanel searchProfilePanel = new VerticalPanel();

		hPanel.add(matchesPanel);
		hPanel.add(searchProfilePanel);
		
	}

	public void updatePage() {
		getMatchesByProfileId(1);
	}

	public void getMatchesByProfileId(int profileId) {
		schwabenAdministration.getMatchesByProfileId(profileId,
				new AsyncCallback<ArrayList<Profile>>() {
					public void onFailure(Throwable caught) {
						System.out.println("An error has occured");
					}

					public void onSuccess(ArrayList<Profile> result) {
						showMatches(result);
					}
				});
	}

	public void showMatches(ArrayList<Profile> matches) {
		matchesPanel.clear();
		
		for (Profile match : matches) {
			HorizontalPanel matchPanel = new HorizontalPanel();
			matchPanel.setBorderWidth(1);

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
