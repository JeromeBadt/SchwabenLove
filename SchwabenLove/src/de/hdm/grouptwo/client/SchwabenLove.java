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

/**
 * Main class <br>
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author JoshuaHill, JeromeBadt
 */
public class SchwabenLove implements EntryPoint {
	private LoginInfo loginInfo = null;

	public void onModuleLoad() {
		// Set logger settings here, since they seem to be ignored .gwt.xml and
		// logging.properties
		ClientsideSettings.getLogger().addHandler(new ConsoleLogHandler());
		ClientsideSettings.getLogger().setLevel(Level.INFO);

		ClientsideSettings.getLoginService().login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
					public void onFailure(Throwable error) {
						System.out.println(error);
					}

					public void onSuccess(LoginInfo result) {
						loginInfo = result;
						
						if (loginInfo.isLoggedIn()) {
							ClientsideSettings.getLogger().log(Level.INFO,
									"Logged in: " + loginInfo.getEmailAddress());
							setProfile(loginInfo.getEmailAddress());
							loadMainView();
						} else {
							ClientsideSettings.getLogger().log(Level.INFO,
									"Not logged in.");
							loadLogin();
						}
					}
				});
	}

	private void loadLogin() {
		VerticalPanel loginPanel = new VerticalPanel();
		Label loginLabel = new Label("Sign in");
		Anchor signInLink = new Anchor("Sign In");

		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootLayoutPanel.get().add(loginPanel);
	}

	private void loadMainView() {
		RootLayoutPanel.get().add(new MainView(loginInfo));
	}

	private void setProfile(String email) {
		ClientsideSettings.getAdministrationService().setProfile(email,
				new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.SEVERE,
								"Set profile failed: " + caught.getMessage());
					}

					public void onSuccess(Void result) {
					}
				});
	}

	// /**
	// * The message displayed to the user when the server cannot be reached or
	// * returns an error.
	// */
	// private static final String SERVER_ERROR = "An error occurred while "
	// + "attempting to contact the server. Please check your network "
	// + "connection and try again.";
	//
	// /**
	// * Create a remote service proxy to talk to the server-side Greeting
	// service.
	// */
	// private final GreetingServiceAsync greetingService = GWT
	// .create(GreetingService.class);
	//
	// /**
	// * This is the entry point method.
	// */
	// public void onModuleLoad() {
	// final Button sendButton = new Button("Send");
	// final TextBox nameField = new TextBox();
	// nameField.setText("GWT User");
	// final Label errorLabel = new Label();
	//
	// // We can add style names to widgets
	// sendButton.addStyleName("sendButton");
	//
	// // Add the nameField and sendButton to the RootPanel
	// // Use RootPanel.get() to get the entire body element
	// RootPanel.get("nameFieldContainer").add(nameField);
	// RootPanel.get("sendButtonContainer").add(sendButton);
	// RootPanel.get("errorLabelContainer").add(errorLabel);
	//
	// // Focus the cursor on the name field when the app loads
	// nameField.setFocus(true);
	// nameField.selectAll();
	//
	// // Create the popup dialog box
	// final DialogBox dialogBox = new DialogBox();
	// dialogBox.setText("Remote Procedure Call");
	// dialogBox.setAnimationEnabled(true);
	// final Button closeButton = new Button("Close");
	// // We can set the id of a widget by accessing its Element
	// closeButton.getElement().setId("closeButton");
	// final Label textToServerLabel = new Label();
	// final HTML serverResponseLabel = new HTML();
	// VerticalPanel dialogVPanel = new VerticalPanel();
	// dialogVPanel.addStyleName("dialogVPanel");
	// dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
	// dialogVPanel.add(textToServerLabel);
	// dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
	// dialogVPanel.add(serverResponseLabel);
	// dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
	// dialogVPanel.add(closeButton);
	// dialogBox.setWidget(dialogVPanel);
	//
	// // Add a handler to close the DialogBox
	// closeButton.addClickHandler(new ClickHandler() {
	// public void onClick(ClickEvent event) {
	// dialogBox.hide();
	// sendButton.setEnabled(true);
	// sendButton.setFocus(true);
	// }
	// });
	//
	// // Create a handler for the sendButton and nameField
	// class MyHandler implements ClickHandler, KeyUpHandler {
	// /**
	// * Fired when the user clicks on the sendButton.
	// */
	// public void onClick(ClickEvent event) {
	// sendNameToServer();
	// }
	//
	// /**
	// * Fired when the user types in the nameField.
	// */
	// public void onKeyUp(KeyUpEvent event) {
	// if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	// sendNameToServer();
	// }
	// }
	//
	// /**
	// * Send the name from the nameField to the server and wait for a response.
	// */
	// private void sendNameToServer() {
	// // First, we validate the input.
	// errorLabel.setText("");
	// String textToServer = nameField.getText();
	// if (!FieldVerifier.isValidName(textToServer)) {
	// errorLabel.setText("Please enter at least four characters");
	// return;
	// }
	//
	// // Then, we send the input to the server.
	// sendButton.setEnabled(false);
	// textToServerLabel.setText(textToServer);
	// serverResponseLabel.setText("");
	// greetingService.greetServer(textToServer,
	// new AsyncCallback<String>() {
	// public void onFailure(Throwable caught) {
	// // Show the RPC error message to the user
	// dialogBox
	// .setText("Remote Procedure Call - Failure");
	// serverResponseLabel
	// .addStyleName("serverResponseLabelError");
	// serverResponseLabel.setHTML(SERVER_ERROR);
	// dialogBox.center();
	// closeButton.setFocus(true);
	// }
	//
	// public void onSuccess(String result) {
	// dialogBox.setText("Remote Procedure Call");
	// serverResponseLabel
	// .removeStyleName("serverResponseLabelError");
	// serverResponseLabel.setHTML(result);
	// dialogBox.center();
	// closeButton.setFocus(true);
	// }
	// });
	// }
	// }
	//
	// // Add a handler to send the name to the server
	// MyHandler handler = new MyHandler();
	// sendButton.addClickHandler(handler);
	// nameField.addKeyUpHandler(handler);
	// }
}
