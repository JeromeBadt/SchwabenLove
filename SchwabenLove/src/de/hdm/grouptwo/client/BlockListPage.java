package de.hdm.grouptwo.client;

import java.util.ArrayList;
import java.util.logging.Level;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.grouptwo.shared.bo.Profile;

public class BlockListPage extends ListPage {
	public BlockListPage() {
		super("Blockliste");
	}

	@Override
	public void updatePage() {
		listPanel.clear();

		administrationService
				.getBlockedProfiles(new AsyncCallback<ArrayList<Profile>>() {
					public void onSuccess(ArrayList<Profile> result) {
						profileList = result;
						showList();
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}

	@Override
	protected void deleteElement(int profileId) {
		administrationService.deleteBlock(profileId,
				new AsyncCallback<Void>() {
					public void onSuccess(Void result) {
					}

					public void onFailure(Throwable caught) {
						ClientsideSettings.getLogger().log(Level.WARNING,
								caught.getMessage());
					}
				});
	}
}
