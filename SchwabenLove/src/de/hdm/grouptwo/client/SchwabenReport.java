package de.hdm.grouptwo.client;

import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;


/**
 * Main class for Report Generator <br>
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author JoshuaHill, JeromeBadt
 */
public class SchwabenReport implements EntryPoint {

	private LoginInfo loginInfo = null;
	private MainView mainView = null;

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
									.log(Level.INFO,
											"Logged in: "
													+ loginInfo
															.getEmailAddress());
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
		ClientsideSettings.getAdministrationService().setProfile(email,
				new AsyncCallback<Profile>() {
					public void onSuccess(Profile result) {
						mainView = new MainView(loginInfo);
						RootLayoutPanel.get().add(mainView);

						// Check if it's a new user
						if (result == null) {
							mainView.loadSetup();
						} else {
							mainView.loadFull();
						}
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.SEVERE,
								"Set profile failed: " + caught.getMessage());
					}
				});
	}
}
