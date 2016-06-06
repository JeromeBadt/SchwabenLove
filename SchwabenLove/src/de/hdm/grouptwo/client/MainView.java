package de.hdm.grouptwo.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

import de.hdm.grouptwo.shared.bo.LoginInfo;

/**
 * This class represents the main layout of the page.
 * 
 * @author JoshuaHill, JeromeBadt
 */
public class MainView extends ResizeComposite {
	private DockLayoutPanel mainView = new DockLayoutPanel(Unit.EM);
	private DeckLayoutPanel contentPanel = new DeckLayoutPanel();
	private ImprintPage imprintPage = new ImprintPage();

	public MainView(LoginInfo loginInfo) {
		initWidget(mainView);

		Menu menu = new Menu(contentPanel, loginInfo);
		mainView.setStyleName("wrapper");
		menu.setStyleName("nav");
		contentPanel.setStyleName("main");
		
		contentPanel.add(imprintPage);

		mainView.addNorth(menu, 10);
		mainView.addSouth(createFooterPanel(), 4);
		mainView.add(contentPanel);
	}

	private Anchor createFooterPanel() {
		Anchor imprint = new Anchor("Impressum");
		imprint.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				contentPanel.showWidget(imprintPage);
			}
		});

		imprint.setStyleName("footer");
		return imprint;
	}
}
