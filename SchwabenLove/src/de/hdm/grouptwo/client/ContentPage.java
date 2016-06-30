package de.hdm.grouptwo.client;

import com.google.gwt.user.client.ui.ResizeComposite;

import de.hdm.grouptwo.shared.AdministrationServiceAsync;

public abstract class ContentPage extends ResizeComposite {
	protected final String name;

	protected AdministrationServiceAsync administrationService = ClientsideSettings
			.getAdministrationService();

	public ContentPage(String name) {
		this.name = name;
	}

	public abstract void updatePage();

	public String getName() {
		return name;
	}
}
