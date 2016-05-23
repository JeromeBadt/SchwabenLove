package de.hdm.grouptwo.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class ProfilePage extends ContentPage {
	SimpleLayoutPanel sPanel = new SimpleLayoutPanel();

	public ProfilePage() {
		super("Profil");
		initWidget(sPanel);
	}

	@Override
	public void updatePage() {
		sPanel.clear();

		sPanel.add(new Label(name));
	}
}
