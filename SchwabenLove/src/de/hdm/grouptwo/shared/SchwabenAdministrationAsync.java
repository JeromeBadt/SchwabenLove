package de.hdm.grouptwo.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.grouptwo.shared.bo.Profile;

public interface SchwabenAdministrationAsync {
	void getMatchesByProfileId(int profileId,
			AsyncCallback<ArrayList<Profile>> callback);
}
