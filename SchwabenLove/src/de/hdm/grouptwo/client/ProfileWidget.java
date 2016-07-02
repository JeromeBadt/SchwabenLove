package de.hdm.grouptwo.client;

import java.util.logging.Level;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.grouptwo.shared.bo.Profile;
import de.hdm.grouptwo.shared.bo.SimilarityDegree;

public class ProfileWidget extends ResizeComposite {
	protected LayoutPanel lPanel = new LayoutPanel();
	protected LayoutPanel rightPanel = new LayoutPanel();

	protected Profile profile = null;

	Label similarityDegreeLbl = new Label();

	public ProfileWidget(Profile p) {
		initWidget(lPanel);
		lPanel.setStyleName("profile-widget");

		profile = p;

		LayoutPanel innerPanel = new LayoutPanel();

		Image profilePicture = new Image("images/default.png");
		profilePicture.setWidth("96px");

		VerticalPanel vPanel1 = new VerticalPanel();

		Label label1 = new Label(profile.getFirstName() + " "
				+ profile.getLastName().substring(0, 1) + "., "
				+ profile.getAge() + ", " + profile.getGender());
		label1.setStyleName("profile-header");
		vPanel1.add(label1);

		String line2 = profile.getProfession() + ", " + profile.getLocation();
		vPanel1.add(new Label(line2));

		String line3 = profile.getHeight() + "cm";
		vPanel1.add(new Label(line3));

		innerPanel.add(profilePicture);
		innerPanel.add(vPanel1);
		innerPanel.add(rightPanel);

		LayoutPanel heartPanel = new LayoutPanel();
		Image heartIcon = new Image("images/icons/heart.png");
		heartIcon.setWidth("72px");
		similarityDegreeLbl.setStyleName("similarity-degree-label");

		lPanel.add(innerPanel);
		heartPanel.add(heartIcon);
		heartPanel.add(similarityDegreeLbl);
		rightPanel.add(heartPanel);

		lPanel.setWidgetTopBottom(innerPanel, 10, Unit.PX, 10, Unit.PX);
		lPanel.setWidgetLeftRight(innerPanel, 10, Unit.PX, 10, Unit.PX);
		innerPanel.setWidgetLeftWidth(profilePicture, 0, Unit.PX, 96, Unit.PX);
		innerPanel.setWidgetLeftWidth(vPanel1, 106, Unit.PX, 200, Unit.PX);
		innerPanel.setWidgetRightWidth(rightPanel, 0, Unit.PX, 100, Unit.PX);

		rightPanel.setWidgetTopBottom(heartPanel, 22, Unit.PX, 0, Unit.PX);
		heartPanel.setWidgetLeftRight(heartIcon, 14, Unit.PX, 14, Unit.PX);

		ClientsideSettings.getAdministrationService()
				.getSimilarityDegreeByProfileId(profile.getId(),
						new AsyncCallback<SimilarityDegree>() {
							public void onSuccess(SimilarityDegree result) {
								showSimilarityDegree(result);
							}

							public void onFailure(Throwable caught) {
								ClientsideSettings.getLogger().log(
										Level.WARNING, caught.getMessage());
							}
						});
	}

	private void showSimilarityDegree(SimilarityDegree similarityDegree) {
		similarityDegreeLbl.setText(Integer.toString(similarityDegree
				.getScore()));
	}
}
