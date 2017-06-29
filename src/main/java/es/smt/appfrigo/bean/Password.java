package es.smt.appfrigo.bean;

import java.io.Serializable;

public class Password implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String oldPassword;
	private String password;
	private String repPassword;
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepPassword() {
		return repPassword;
	}
	public void setRepPassword(String repPassword) {
		this.repPassword = repPassword;
	}
	
	
}
