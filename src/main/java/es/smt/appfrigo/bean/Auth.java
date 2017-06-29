package es.smt.appfrigo.bean;

import java.io.Serializable;

public class Auth implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String username;
	private String password;
	private String repeatPassword;
	private String currentPassword;
	private String code;
	
	
	public Auth() {
		super();
	}
	
	
	public Auth(int userId, String username, String password, String repeatPassword, String currentPassword,
			String code) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.repeatPassword = repeatPassword;
		this.currentPassword = currentPassword;
		this.code = code;
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Auth [userId=" + userId + ", username=" + username + ", password=" + password + ", repeatPassword="
				+ repeatPassword + ", currentPassword=" + currentPassword + ", code=" + code + "]";
	}
	
	
}
