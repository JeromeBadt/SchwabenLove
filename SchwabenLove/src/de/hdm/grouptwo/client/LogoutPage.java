package de.hdm.grouptwo.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

import de.hdm.grouptwo.shared.bo.LoginInfo;

public class LogoutPage extends ContentPage {
	private LoginInfo loginInfo = null;
	private SimpleLayoutPanel sPanel = new SimpleLayoutPanel();

	public LogoutPage(LoginInfo loginInfo) {
		super("Logout");
		this.loginInfo = loginInfo;

		initWidget(sPanel);
	}

	@Override
	public void updatePage() {
		sPanel.clear();

		Window.Location.assign(loginInfo.getLogoutUrl());
	}
}
