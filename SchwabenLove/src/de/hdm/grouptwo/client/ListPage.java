package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

import de.hdm.grouptwo.shared.bo.Profile;

public class ListPage extends ContentPage {
	private LayoutPanel lPanel = new LayoutPanel();
	protected LayoutPanel listPanel = new LayoutPanel();

	public ListPage(String name) {
		super(name);
		initWidget(lPanel);

		lPanel.setStyleName("list-page");
		lPanel.add(listPanel);
	}

	@Override
	public void updatePage() {
		listPanel.clear();

		administrationService
				.getAllProfiles(new AsyncCallback<ArrayList<Profile>>() {
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}

					public void onSuccess(ArrayList<Profile> result) {
						showList(result);
					}
				});
	}

	protected void showList(ArrayList<Profile> profileList) {
		int offset = 0;
		for (Profile p : profileList) {
			ListProfileWidget profileWidget = new ListProfileWidget(p);
			listPanel.add(profileWidget);
			listPanel.setWidgetTopHeight(profileWidget, offset, Unit.PX,
					118, Unit.PX);
			offset += 128;
		}
		lPanel.setWidgetTopHeight(listPanel, 0, Unit.PX, offset, Unit.PX);

		// Adjust the layout when the browser event loop returns (wait for
		// scrollbar to render)
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				onResize();
			}
		});
	}

	@Override
	public void onResize() {
		if (getElement().getClientWidth() >= 920) {
			int offset = (getElement().getClientWidth() - 910) / 2;
			lPanel.setWidgetLeftWidth(listPanel, offset, Unit.PX, 900,
					Unit.PX);
		} else {
			lPanel.setWidgetLeftRight(listPanel, 0, Unit.PX, 10, Unit.PX);
		}
	}

	private class ListProfileWidget extends ProfileWidget {
		ListProfileWidget(Profile profile) {
			super(profile);

			Image removeIcon = new Image("images/icons/cross.png");
			removeIcon.setWidth("24px");
			removeIcon.setTitle("Profil entfernen");
			rightPanel.add(removeIcon);
			LayoutPanel heartPanel = new LayoutPanel();
			rightPanel.add(heartPanel);
			Image heartIcon = new Image("images/icons/heart.png");
			heartIcon.setWidth("72px");
			heartPanel.add(heartIcon);
			Label similarityDegreeLbl = new Label("120");
			similarityDegreeLbl.setStyleName("similarity-degree-label");
			heartPanel.add(similarityDegreeLbl);

			rightPanel
					.setWidgetRightWidth(removeIcon, 0, Unit.PCT, 24, Unit.PX);
			rightPanel.setWidgetTopHeight(removeIcon, 0, Unit.PCT, 24, Unit.PX);
			rightPanel.setWidgetTopBottom(heartPanel, 22, Unit.PX, 0, Unit.PX);
			heartPanel.setWidgetLeftRight(heartIcon, 14, Unit.PX, 14, Unit.PX);
		}
	}
}