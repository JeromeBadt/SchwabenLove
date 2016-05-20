package de.hdm.grouptwo.server;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.grouptwo.shared.SchwabenAdministration;
import de.hdm.grouptwo.shared.bo.Profile;

public class SchwabenAdministrationImpl extends RemoteServiceServlet implements
		SchwabenAdministration {
	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<Profile> getMatchesByProfileId(int profileId) {
		ArrayList<Profile> matches = new ArrayList<Profile>();
		SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");

		Profile profile1 = new Profile();
		profile1.setId(1);
		profile1.setFirstName("Martina");
		profile1.setLastName("Müller");
		try {
			profile1.setBirthdate(ft.parse("14.05.1974"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		profile1.setGender("w");
		profile1.setProfession("Lehrerin");
		profile1.setLocation("Tübingen");
		profile1.setHeight(173);

		Profile profile2 = new Profile();
		profile2.setId(2);
		profile2.setFirstName("Bernd");
		profile2.setLastName("Heinrich");
		try {
			profile2.setBirthdate(ft.parse("19.03.1969"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		profile2.setGender("m");
		profile2.setProfession("Professor");
		profile2.setLocation("Stuttgart");
		profile2.setHeight(179);

		Profile profile3 = new Profile();
		profile3.setId(3);
		profile3.setFirstName("Max");
		profile3.setLastName("Mustermann");
		try {
			profile3.setBirthdate(ft.parse("05.07.1988"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		profile3.setGender("m");
		profile3.setProfession("Social Media Manager");
		profile3.setLocation("Stuttgart");
		profile3.setHeight(181);

		matches.add(profile1);
		matches.add(profile2);
		matches.add(profile3);
		
		return matches;
	}
}
