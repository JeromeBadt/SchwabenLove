package de.hdm.grouptwo.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.grouptwo.shared.bo.LoginInfo;

/**
 * Synchronous interface to sign on to the dating website.
 * @author joshuahill
 *
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	public LoginInfo login(String requestUri);

}
