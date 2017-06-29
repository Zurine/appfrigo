package es.smt.appfrigo.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private boolean rememberMe;
	private String timezone;
	private String language;
    
	public LoginBean() {
		super();
		
	}
	public LoginBean(String username, String password, boolean rememberMe) {
		super();
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "LoginBean [username=" + username + ", password=" + password + ", rememberMe=" + rememberMe + "]";
	}
}
