package de.hdm.grouptwo.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.grouptwo.shared.bo.LoginInfo;

/**
 * Asynchronous interface to sign on to the dating website.
 * @author manuelruss
 *
 */
public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInfo> callback);
}
