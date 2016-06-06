package de.hdm.grouptwo.client;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.Level;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.bo.Profile;

public class ProfilePage extends ContentPage {
	LayoutPanel lPanel = new LayoutPanel();

	public ProfilePage() {
		super("Profil");
		initWidget(lPanel);
	}

	public ProfilePage(int id) {
		super("Profil");
		initWidget(lPanel);
	}

	@Override
	public void updatePage() {
		lPanel.clear();
		administrationService.getProfileById(1, new AsyncCallback<Profile>() {
			public void onSuccess(Profile result) {
				showProfile(result);
			}

			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().log(Level.WARNING,
						caught.getMessage());
			}
		});
	}

	public void showProfile(Profile profile) {
		LayoutPanel attributePanel = new LayoutPanel();
		LayoutPanel informationPanel = new LayoutPanel();

		Image profilePicture = new Image("images/38.png");
		profilePicture.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		profilePicture.getElement().getStyle().setBorderWidth(3, Unit.PX);
		profilePicture.setWidth("256px");

		VerticalPanel vPanel1 = new VerticalPanel();

		vPanel1.add(new Label(profile.getFirstName() + " "
				+ profile.getLastName().substring(0, 1) + "."));

		vPanel1.add(new Label(Integer.toString(profile.getAge())));
		vPanel1.add(new Label(profile.getGender()));
		vPanel1.add(new Label(profile.getProfession()));
		vPanel1.add(new Label(profile.getLocation()));
		vPanel1.add(new Label(profile.getEducation()));

		VerticalPanel vPanel2 = new VerticalPanel();

		vPanel2.add(new Label(profile.getSmoker() ? "Raucher" : "Nichtraucher"));
		vPanel2.add(new Label(profile.getReligion()));
		vPanel2.add(new Label(new BigDecimal((float) profile.getHeight() / 100)
				.setScale(2, BigDecimal.ROUND_HALF_UP) + " m"));
		vPanel2.add(new Label(profile.getPhysique()));
		vPanel2.add(new Label(profile.getHairColor()));
		
		Image deleteIcon = new Image("images/trashbin.png");

		attributePanel.add(profilePicture);
		attributePanel.add(vPanel1);
		attributePanel.add(vPanel2);

		attributePanel.setWidgetLeftWidth(vPanel1, 279, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(vPanel2, 454, Unit.PX, 150, Unit.PX);

		lPanel.add(attributePanel);
		lPanel.add(informationPanel);

	}

}
