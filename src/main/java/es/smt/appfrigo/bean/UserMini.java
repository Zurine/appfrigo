package es.smt.appfrigo.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import es.smt.appfrigo.constants.Constants;


public class UserMini implements Serializable{

	private static final long serialVersionUID = 1L;

	private int userId;
    private String nickName;
    private String password;
    @NotEmpty(message="Email is required")
    @NotNull(message="Email is required")
    @Pattern(regexp=Constants.EMAIL_PATTERN)
    private String email;

	public UserMini() {
		super();
	}
	
	public UserMini(int userId, String nickName, String password, String email) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserMiniBean [userId=" + userId + ", nickName=" + nickName + ", password=" + password + ", email="
				+ email + "]";
	}
	
	
	
    
}
