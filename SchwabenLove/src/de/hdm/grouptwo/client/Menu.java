package de.hdm.grouptwo.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Menu extends Composite {
	private VerticalPanel contentPanel = null;
	private MenuBar menuBar = new MenuBar();
	
	public Menu(VerticalPanel contentPanel) {
		initWidget(menuBar);
		
		this.contentPanel = contentPanel;
		initMenu();
	}
	
	private void initMenu() {
		menuBar.setWidth("1366px");
		
		Command showProfile = new Command() {
			public void execute() {
			
				contentPanel.clear();
				Label lbl = new Label("Profile");
				contentPanel.add(lbl);
			}
		};
		
		Command showMatches = new Command() {
			public void execute() {
			
				contentPanel.clear();
				Label lbl = new Label("Matches");
				contentPanel.add(lbl);
			}
		};
		
		Command showBookmarkList = new Command() {
			public void execute() {
			
				contentPanel.clear();
				Label lbl = new Label("Bookmark List");
				contentPanel.add(lbl);
			}
		};
		
		Command showBlockList = new Command() {
			public void execute() {
			
				contentPanel.clear();
				Label lbl = new Label("Block List");
				contentPanel.add(lbl);
			}
		};
		
		Command showLogout = new Command() {
			public void execute() {
			
				contentPanel.clear();
				Label lbl = new Label("Logout");
				contentPanel.add(lbl);
			}
		};
		
		
		menuBar.addItem("Profil", showProfile);
		menuBar.addItem("Partnervorschläge", showMatches);
		menuBar.addItem("Merkzettel", showBookmarkList);
		menuBar.addItem("Blockliste", showBlockList);
		menuBar.addItem("Logout", showLogout).setStyleDependentName("logout", true);
	}	
}
