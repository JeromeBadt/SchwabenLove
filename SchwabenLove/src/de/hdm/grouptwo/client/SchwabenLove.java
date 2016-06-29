package de.hdm.grouptwo.client;

import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.bo.LoginInfo;
import de.hdm.grouptwo.shared.bo.Profile;

/**
 * Main class <br>
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author JoshuaHill, JeromeBadt
 */
public class SchwabenLove implements EntryPoint {
	private LoginInfo loginInfo = null;
	MainView mainView = new MainView();

	public void onModuleLoad() {
		// Set logger settings here, since they seem to be ignored .gwt.xml and
		// logging.properties
		ClientsideSettings.getLogger().addHandler(new ConsoleLogHandler());
		ClientsideSettings.getLogger().setLevel(Level.INFO);

		ClientsideSettings.getLoginService().login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}

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
							loadLoginPage();
						}
					}
				});
	}

	private void setProfile(String email) {
		ClientsideSettings.getAdministrationService().setProfile(email,
				new AsyncCallback<Profile>() {
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.SEVERE,
								"Set profile failed: " + caught.getMessage());
					}

					public void onSuccess(Profile result) {
						// Check if it's a new user
						if (result == null) {
							// loadMainView();
							loadSetupPage();
						} else {
							loadMainView();
						}
					}
				});
	}

	private void loadLoginPage() {
		VerticalPanel loginPanel = new VerticalPanel();
		Label loginLabel = new Label("Sign in");
		Anchor signInLink = new Anchor("Sign In");

		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootLayoutPanel.get().add(loginPanel);
	}

	private void loadSetupPage() {
		RootLayoutPanel.get().add(mainView);
		mainView.showSetupPage(loginInfo);
	}

	private void loadMainView() {
		mainView.addMenu(loginInfo);
		RootLayoutPanel.get().add(mainView);
	}
}