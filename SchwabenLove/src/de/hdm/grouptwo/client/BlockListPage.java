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
}
