package de.hdm.grouptwo.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.grouptwo.client.LoginInfo;
import de.hdm.grouptwo.client.LoginService;



public class LoginServiceImpl extends RemoteServiceServlet implements LoginService{
	
	// Default SerialVersionUID
	private static final long serialVersionUID = 1L;

	public LoginInfo login (String requestUri) {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		LoginInfo info = new LoginInfo();
		
		if (user != null) {
			info.setLoggedIn(true);
			info.setEmailAddress(user.getEmail());
			info.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
			info.setLoggedIn(false);
			info.setLoginUrl(userService.createLoginURL(requestUri));
		}
		
		return info;
	}

}
