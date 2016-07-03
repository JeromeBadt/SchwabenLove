package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import de.hdm.grouptwo.shared.AdministrationServiceAsync;
import de.hdm.grouptwo.shared.bo.Description;
import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.Selection;

/**
 * Main class <br>
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author JoshuaHill, JeromeBadt
 */
public class SchwabenLove implements EntryPoint {
	private AdministrationServiceAsync administrationService = ClientsideSettings
			.getAdministrationService();

	private LoginInfo loginInfo = null;
	private MainView mainView = null;

	private Profile user = null;

	public void onModuleLoad() {
		// Set logger settings here, since they seem to be ignored .gwt.xml and
		// logging.properties
		ClientsideSettings.getLogger().addHandler(new ConsoleLogHandler());
		ClientsideSettings.getLogger().setLevel(Level.INFO);

		ClientsideSettings.getLoginService().login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onSuccess(LoginInfo result) {
						loginInfo = result;

						if (loginInfo.isLoggedIn()) {
							ClientsideSettings.getLogger()
									.log(Level.INFO, "Logged in: "
											+ loginInfo.getEmailAddress());
							setProfile(loginInfo.getEmailAddress());
						} else {
							ClientsideSettings.getLogger().log(Level.INFO,
									"Not logged in.");
							Window.Location.assign(loginInfo.getLoginUrl());
						}
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}

	private void setProfile(String email) {
		administrationService.setProfile(email, new AsyncCallback<Profile>() {
			public void onSuccess(Profile result) {
				user = result;
				loadDescriptions();
			}

			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().log(Level.SEVERE,
						caught.getMessage());
			}
		});
	}

	private void loadDescriptions() {
		administrationService
				.getAllDescriptions(new AsyncCallback<ArrayList<Description>>() {
					public void onSuccess(ArrayList<Description> result) {
						ClientsideSettings.setDescriptions(result);
						loadSelections();
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.SEVERE,
								caught.getMessage());
					}
				});
	}

	private void loadSelections() {
		administrationService
				.getAllSelections(new AsyncCallback<ArrayList<Selection>>() {
					public void onSuccess(ArrayList<Selection> result) {
						ClientsideSettings.setSelections(result);

						mainView = new MainView(loginInfo);
						RootLayoutPanel.get().add(mainView);

						// Check if it's a new user
						if (user == null) {
							mainView.loadSetup();
						} else {
							mainView.loadFull();
						}
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.SEVERE,
								caught.getMessage());
					}
				});
	}
}
