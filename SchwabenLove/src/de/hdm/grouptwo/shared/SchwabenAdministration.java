package de.hdm.grouptwo.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.grouptwo.shared.bo.Profile;

@RemoteServiceRelativePath("schwabenadministration")
public interface SchwabenAdministration extends RemoteService {
	ArrayList<Profile> getMatchesByProfileId(int profileId);
}
