package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

import de.hdm.grouptwo.shared.bo.LoginInfo;

public class Menu extends Composite {
	private FlowPanel fPanel = new FlowPanel();
	private MenuBar menuBar = new MenuBar();
	private DeckLayoutPanel contentPanel = null;

	Map<ContentPage, MenuItem> pages = new HashMap<ContentPage, MenuItem>();

	public Menu(DeckLayoutPanel contentPanel, LoginInfo loginInfo) {
		initWidget(fPanel);
		this.contentPanel = contentPanel;

		Label logo = new Label("SchwabenLove");
		logo.setStyleName("logo");
		fPanel.add(logo);
		fPanel.add(menuBar);

		initMenu(loginInfo);
	}

	private void initMenu(LoginInfo loginInfo) {
		ArrayList<ContentPage> contentPages = new ArrayList<ContentPage>();
		contentPages.add(new ProfilePage());
		contentPages.add(new MatchesPage());
		contentPages.add(new BookmarkListPage());
		contentPages.add(new BlockListPage());
		contentPages.add(new AdminPage());
		contentPages.add(new LogoutPage(loginInfo));

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
