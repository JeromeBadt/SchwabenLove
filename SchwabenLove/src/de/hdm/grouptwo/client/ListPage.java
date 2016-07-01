package de.hdm.grouptwo.client;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

import de.hdm.grouptwo.shared.bo.Profile;

public abstract class ListPage extends ContentPage {
	private LayoutPanel lPanel = new LayoutPanel();
	protected LayoutPanel listPanel = new LayoutPanel();

	protected ArrayList<Profile> profileList = new ArrayList<Profile>();

	public ListPage(String name) {
		super(name);
		initWidget(lPanel);

		lPanel.setStyleName("list-page");
		lPanel.add(listPanel);
	}

	@Override
	public abstract void updatePage();

	protected void showList() {
		int offset = 0;
		for (Profile p : profileList) {
			ListProfileWidget profileWidget = new ListProfileWidget(p);
			listPanel.add(profileWidget);
			listPanel.setWidgetTopHeight(profileWidget, offset, Unit.PX,
					118, Unit.PX);
			offset += 128;
		}

		setPageHeight(offset);
	}

	public void setPageHeight(int offset) {
		lPanel.setWidgetTopHeight(listPanel, 0, Unit.PX, offset, Unit.PX);

		// Adjust the layout when the browser event loop returns (wait for
		// scrollbar to render)
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
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

	protected abstract void deleteElement(int profileId);

	private class ListProfileWidget extends ProfileWidget {
		ListProfileWidget(Profile p) {
			super(p);

			Image removeIcon = new Image("images/icons/cross.png");
			removeIcon.addStyleName("img-button");
			removeIcon.setWidth("24px");
			removeIcon.setTitle("Profil entfernen");
			removeIcon.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					deleteElement(profile.getId());

					profileList.remove(profile);
					listPanel.remove(ListProfileWidget.this);

					int offset = 0;
					for (int i = 0; i < listPanel.getWidgetCount(); i++) {
						listPanel.setWidgetTopHeight(listPanel.getWidget(i),
								offset, Unit.PX,
								118, Unit.PX);
						offset += 128;
					}

					setPageHeight(offset);
				}
			});

			LayoutPanel heartPanel = new LayoutPanel();
			Image heartIcon = new Image("images/icons/heart.png");
			heartIcon.setWidth("72px");
			Label similarityDegreeLbl = new Label("120");
			similarityDegreeLbl.setStyleName("similarity-degree-label");

			heartPanel.add(heartIcon);
			heartPanel.add(similarityDegreeLbl);

			rightPanel.add(removeIcon);
			rightPanel.add(heartPanel);

			rightPanel
					.setWidgetRightWidth(removeIcon, 0, Unit.PCT, 24, Unit.PX);
			rightPanel.setWidgetTopHeight(removeIcon, 0, Unit.PCT, 24, Unit.PX);
			rightPanel.setWidgetTopBottom(heartPanel, 22, Unit.PX, 0, Unit.PX);
			heartPanel.setWidgetLeftRight(heartIcon, 14, Unit.PX, 14, Unit.PX);
		}
	}
}
