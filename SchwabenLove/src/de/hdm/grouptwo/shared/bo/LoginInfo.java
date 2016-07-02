package de.hdm.grouptwo.shared.bo;

import java.io.Serializable;

public class LoginInfo implements Serializable {
	
	// Default SerialVersionUID
	private static final long serialVersionUID = 1L;
	
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String emailAddress;
	
	/**
	 * Check if the user is logged in.
	 * @return
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * Set the login state.
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Get the URL of the login page.
	 * @return
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * Set the URL of the login page.
	 * @param loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * Get the URL of the logout page.
	 * @return
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * Set the URL of the logout page.
	 * @param logoutUrl
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * Return the email adress of the user.
	 * @return
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Set the email adress. 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}
