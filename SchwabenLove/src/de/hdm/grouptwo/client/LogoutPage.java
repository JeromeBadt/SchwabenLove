package de.hdm.grouptwo.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

import de.hdm.grouptwo.shared.bo.LoginInfo;

public class LogoutPage extends ContentPage {
	private LoginInfo loginInfo = null;
	SimpleLayoutPanel sPanel = new SimpleLayoutPanel();

	public LogoutPage(LoginInfo loginInfo) {
		super("Logout");
		this.loginInfo = loginInfo;

		initWidget(sPanel);
	}

	@Override
	public void updatePage() {
		sPanel.clear();

		sPanel.add(new Label(name));
		Window.Location.assign(loginInfo.getLogoutUrl());
	}
}
