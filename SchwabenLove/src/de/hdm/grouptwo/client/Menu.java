package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

import de.hdm.grouptwo.shared.bo.LoginInfo;

public class Menu extends Composite {
	private LoginInfo loginInfo = null;

	private MenuBar menuBar = new MenuBar();
	private DeckLayoutPanel contentPanel = null;

	private ArrayList<ContentPage> contentPages = new ArrayList<ContentPage>();
	private Map<ContentPage, MenuItem> pages = new HashMap<ContentPage, MenuItem>();

	private SetupPage setupPage = null;

	public Menu(DeckLayoutPanel contentPanel, LoginInfo loginInfo) {
		initWidget(menuBar);

		this.contentPanel = contentPanel;
		this.loginInfo = loginInfo;
	}

	public void loadBasicMenu() {
		setupPage = new SetupPage(this, loginInfo);
		contentPages.add(setupPage);
		contentPages.add(new LogoutPage(loginInfo));

		createMenu();

		// Load setupPage page on login
		pages.get(setupPage).getScheduledCommand().execute();
	}
	
	public void loadReportMenu() {
		UnviewedMatchesReportPage unviewedPage = new UnviewedMatchesReportPage(this);
		contentPages.add(unviewedPage);
		contentPages.add(new MatchesBySearchprofileReportPage());
		contentPages.add(new LogoutPage(loginInfo));
		
		createMenu();
		
		// Load unviewedPage page on login
		pages.get(unviewedPage).getScheduledCommand().execute();
	}

	public void loadFullMenu() {
		contentPages.clear();
		if (setupPage != null) {
			contentPanel.remove(setupPage);
		}

		// Save profilePage to load it on login later
		ProfilePage profilePage = new ProfilePage();
		contentPages.add(profilePage);
		contentPages.add(new MatchesPage());
		contentPages.add(new BookmarkListPage());
		contentPages.add(new BlockListPage());
		// contentPages.add(new AdminPage());
		contentPages.add(new LogoutPage(loginInfo));

		createMenu();

		// Load profile page on login
		pages.get(profilePage).getScheduledCommand().execute();
	}

	private void createMenu() {
		pages.clear();
		menuBar.clearItems();

		for (ContentPage page : contentPages) {
			pages.put(page, createMenuItem(page));
			contentPanel.add(page);
		}

		for (ContentPage nfPage : pages.keySet()) {
			final ContentPage page = nfPage;
			final MenuItem item = pages.get(page);

			item.setScheduledCommand(new Command() {
				public void execute() {
					clearMenuItemStyles();
					item.setStyleDependentName("active", true);
					page.updatePage();
					contentPanel.showWidget(page);
				}
			});

			menuBar.addItem(item);
		}
	}

	private void clearMenuItemStyles() {
		for (ContentPage page : pages.keySet()) {
			pages.get(page).removeStyleDependentName("active");
		}
	}

	private MenuItem createMenuItem(ContentPage page) {
		return new MenuItem(SafeHtmlUtils.fromString(page.getName()));
	}
}
