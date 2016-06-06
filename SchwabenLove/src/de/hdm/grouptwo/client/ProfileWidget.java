package de.hdm.grouptwo.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.bo.Profile;

public class ProfileWidget extends ResizeComposite {
	LayoutPanel lPanel = new LayoutPanel();
	LayoutPanel rightPanel = new LayoutPanel();

	public ProfileWidget(Profile profile) {
		initWidget(lPanel);
		lPanel.setStyleName("profile-widget");

		LayoutPanel innerPanel = new LayoutPanel();

		Image profilePicture = new Image("images/38.png");
		profilePicture.setWidth("96px");

		VerticalPanel vPanel1 = new VerticalPanel();

		Label label1 = new Label(profile.getFirstName() + " "
				+ profile.getLastName().substring(0, 1) + "., "
				+ profile.getAge() + ", " + profile.getGender());
		label1.setStyleName("profile-header");
		vPanel1.add(label1);
		
		String line2 = profile.getProfession() + ", " + profile.getLocation();
		vPanel1.add(new Label(line2));
		
		String line3 = profile.getHeight() + "cm";
		vPanel1.add(new Label(line3));

		innerPanel.add(profilePicture);
		innerPanel.add(vPanel1);
		innerPanel.add(rightPanel);
		lPanel.add(innerPanel);

		lPanel.setWidgetTopBottom(innerPanel, 10, Unit.PX, 10, Unit.PX);
		lPanel.setWidgetLeftRight(innerPanel, 10, Unit.PX, 10, Unit.PX);
		innerPanel.setWidgetLeftWidth(profilePicture, 0, Unit.PX, 96, Unit.PX);
		innerPanel.setWidgetLeftWidth(vPanel1, 106, Unit.PX, 200, Unit.PX);
		innerPanel.setWidgetRightWidth(rightPanel, 0, Unit.PX, 100, Unit.PX);
	}
}
