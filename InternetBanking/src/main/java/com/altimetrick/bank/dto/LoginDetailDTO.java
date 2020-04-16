package com.altimetrick.bank.dto;

import java.io.Serializable;

/**
 * Login details data transfer object.
 * 
 * @author VSanthosh
 *
 */
public class LoginDetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String loginName;

	private char[] password;

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	public char[] getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(char[] password) {
		this.password = password;
	}

}
