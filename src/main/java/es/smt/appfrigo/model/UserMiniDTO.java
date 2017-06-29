package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserMiniDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	private int userId;
    private String nickName;
    private String password;
    private String email;
    private int enterpriseId;
    
	public UserMiniDTO() {
		super();
	}
	
	public UserMiniDTO(int userId) {
		super();
		this.userId = userId;

	}
	
	public UserMiniDTO(int userId, String nickName, String password, String email, int enterpriseId) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
		this.enterpriseId = enterpriseId;
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
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}


	@Override
	public String toString() {
		return "UserMiniDTO [userId=" + userId + ", nickName=" + nickName + ", password=" + password + ", email="
				+ email + ", enterpriseId=" + enterpriseId + "]";
	}
	
}
    
