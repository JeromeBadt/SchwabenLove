package de.hdm.grouptwo.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;

import de.hdm.grouptwo.shared.bo.LoginInfo;

/**
 * This class represents the main layout of the page.
 * 
 * @author JoshuaHill, JeromeBadt
 */
public class MainView extends ResizeComposite {
	private DockLayoutPanel mainView = new DockLayoutPanel(Unit.EM);
	private Menu menu = null;
	private static DeckLayoutPanel contentPanel = new DeckLayoutPanel();
	private ImprintPage imprintPage = new ImprintPage();
	private static Anchor imprint = new Anchor("Impressum");

	public MainView(LoginInfo loginInfo) {
		initWidget(mainView);

		mainView.setStyleName("wrapper");

		FlowPanel nav = new FlowPanel();
		Label logo = new Label("SchwabenLove");
		logo.setStyleName("logo");
		nav.add(logo);

		contentPanel.setStyleName("main");
		contentPanel.add(imprintPage);

		mainView.addNorth(nav, 10);
		mainView.addSouth(createFooterPanel(), 4);
		mainView.add(contentPanel);

		menu = new Menu(contentPanel, loginInfo);
		nav.add(menu);
	}

	public void loadSetup() {
		menu.loadBasicMenu();
	}

	public void loadFull() {
		menu.loadFullMenu();
	}

	public void loadReport() {
		menu.loadReportMenu();
	}

	public static DeckLayoutPanel getContentPanel() {
		return contentPanel;
	}

	public static Anchor getImprintAnchor() {
		return imprint;
	}

	private Anchor createFooterPanel() {
		imprint.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Menu.clearMenuItemStyles();
				imprint.setStyleDependentName("active", true);
				contentPanel.showWidget(imprintPage);
			}
		});

		imprint.setStyleName("footer");
		return imprint;
	}
}
