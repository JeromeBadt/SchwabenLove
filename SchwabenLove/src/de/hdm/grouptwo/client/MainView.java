package de.hdm.grouptwo.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * This class represents the main layout of the page.
 * 
 * @author joshuahill
 *
 */
public class MainView extends Composite {
	
	private VerticalPanel mainView = new VerticalPanel();
	private VerticalPanel contentPanel = new VerticalPanel();
	private Menu menu = null;
	
	
	public MainView(LoginInfo loginInfo) {
		
		menu = new Menu(contentPanel, loginInfo);
		
		initWidget(mainView);
		
		Anchor imprint = new Anchor("Impressum");
		imprint.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				contentPanel.clear();
				Label lbl = new Label("Impressum");
				contentPanel.add(lbl);
			}
		});
		
		mainView.add(menu);
		mainView.add(contentPanel);			
		mainView.add(imprint);
	}
	
	
}
