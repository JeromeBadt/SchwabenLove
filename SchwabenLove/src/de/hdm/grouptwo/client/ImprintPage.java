package de.hdm.grouptwo.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class ImprintPage extends ContentPage {
	SimpleLayoutPanel sPanel = new SimpleLayoutPanel();

	public ImprintPage() {
		super("Impressum");
		initWidget(sPanel);
		sPanel.add(new Label(name));
	}

	@Override
	public void updatePage() {
	}
}
