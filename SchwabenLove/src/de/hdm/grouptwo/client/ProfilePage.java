package de.hdm.grouptwo.client;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.Level;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.bo.Profile;

public class ProfilePage extends ContentPage {
	LayoutPanel lPanel = new LayoutPanel();
	
	public ProfilePage() {
		super("Profil");
		initWidget(lPanel);	
	}

	public ProfilePage(int id) {
		super("Profil");
		initWidget(lPanel);
		
		administrationService.getProfileById(id, new AsyncCallback<Profile>() {

			@Override
			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().log(Level.WARNING,
						caught.getMessage());
			}

			@Override
			public void onSuccess(Profile result) {
				showProfile(result);
			}
			
		});
		
	}

	@Override
	public void updatePage() {
		lPanel.clear();
		
		// change to getProfile()
		administrationService.getProfileById(1, new AsyncCallback<Profile>() {
			public void onSuccess(Profile result) {
				showProfile(result);
				// show edit buttons
			}

			public void onFailure(Throwable caught) {
				ClientsideSettings.getLogger().log(Level.WARNING,
						caught.getMessage());
			}
		});
	}
	
	public void showProfile(Profile profile) {
		LayoutPanel attributePanel = new LayoutPanel();
		LayoutPanel informationPanel = new LayoutPanel();
		
		Image[] images = new Image[10];
		int[] x = {0,1,2,3,4,5,6,7,8,9,10}; for (int y : x) 
		{
		images[y] = new Image ("images/icons/edit.png");	
		images[y].setWidth("1em");
		images[y].setTitle("Attribut editieren");		
		};		
		
		Image profilePicture = new Image("images/38.png");
		profilePicture.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		profilePicture.getElement().getStyle().setBorderWidth(3, Unit.PX);
		profilePicture.setWidth("256px");

		VerticalPanel vPanel1 = new VerticalPanel();

		vPanel1.add(new Label(profile.getFirstName() + " "
				+ profile.getLastName().substring(0, 1) + "."));

		vPanel1.add(new Label(Integer.toString(profile.getAge())));
		vPanel1.add(new Label(profile.getGender()));
		vPanel1.add(new Label(profile.getProfession()));
		vPanel1.add(new Label(profile.getLocation()));
		vPanel1.add(new Label(profile.getEducation()));

		VerticalPanel vPanel2 = new VerticalPanel();

		vPanel2.add(new Label(profile.getSmoker() ? "Raucher" : "Nichtraucher"));
		vPanel2.add(new Label(profile.getReligion()));
		vPanel2.add(new Label(new BigDecimal((float) profile.getHeight() / 100)
				.setScale(2, BigDecimal.ROUND_HALF_UP) + " m"));
		vPanel2.add(new Label(profile.getPhysique()));
		vPanel2.add(new Label(profile.getHairColor()));
		
		//Funktioniert noch nicht
		Image deleteIcon = new Image("images/icons/trash.png");
		deleteIcon.setWidth("24px");
		deleteIcon.setTitle("Profil löschen");
		deleteIcon.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				administrationService.
				deleteProfile(new AsyncCallback<Void>()
				{
					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
					@Override
					public void onSuccess(Void result) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								"Profile deleted");
					}
				});
			}
		});

		attributePanel.add(profilePicture);
		attributePanel.add(vPanel1);
		attributePanel.add(vPanel2);
		attributePanel.add(deleteIcon);

		attributePanel.add(images[0]);
		attributePanel.add(images[1]);
		attributePanel.add(images[2]);
		attributePanel.add(images[3]);
		attributePanel.add(images[4]);
		attributePanel.add(images[5]);
		attributePanel.add(images[6]);
		attributePanel.add(images[7]);
		attributePanel.add(images[8]);
		attributePanel.add(images[9]);
		attributePanel.add(images[10]);

		attributePanel.setWidgetLeftWidth(vPanel1, 279, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(vPanel2, 454, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetRightWidth(deleteIcon, 10, Unit.PX, 24, Unit.PX);
		
		attributePanel.setWidgetLeftWidth(images[0], 335, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[1], 515, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[2], 15, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[2], 335, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[3], 515, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[3], 15, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[4], 35, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[4], 335, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[5], 515, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[5], 35, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[6], 52, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[6], 335, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[7], 52, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[7], 515, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[8], 70, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[8], 335, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[9], 70, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[9], 515, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetTopBottom(images[10], 87, Unit.PX, 150, Unit.PX);
		attributePanel.setWidgetLeftWidth(images[10], 335, Unit.PX, 150, Unit.PX);	
		
		lPanel.add(attributePanel);
		lPanel.add(informationPanel);
		lPanel.setWidgetTopHeight(informationPanel, 281, Unit.PX, 0, Unit.PX);

	}
}
