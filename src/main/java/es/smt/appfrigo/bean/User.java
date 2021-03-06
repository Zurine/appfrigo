package es.smt.appfrigo.bean;

import java.io.Serializable;


import es.smt.appfrigo.model.TokenDTO;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private int userId;
    private String nickName;
    private String password;
    private String passwordRep;
    private String deviceToken;
    private String token;
    private String email;
    private String Sex;
    private String CP;
    private String birthDate;
    private String registrationDate;   
    private int enterpriseId;
    private int rol;
    private boolean isAdmin;
    private boolean active;
    private TokenDTO tokenDTO;
    
	public User() {
		super();
	}
	
	public User(int userId, String nickName) {
		super();
		this.userId = userId;
		this.nickName = nickName;

	}
	
	
	public User(int userId, String nickName, String password, String deviceToken, String token, String email,
			String sex, String cP, String birthDate, String registrationDate, int enterpriseId, int rol, boolean isAdmin,
			boolean active, TokenDTO tokenDTO) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.password = password;
		this.deviceToken = deviceToken;
		this.token = token;
		this.email = email;
		Sex = sex;
		CP = cP;
		this.birthDate = birthDate;
		this.registrationDate = registrationDate;
		this.enterpriseId = enterpriseId;
		this.rol = rol;
		this.isAdmin = isAdmin;
		this.active = active;
		this.tokenDTO = tokenDTO;
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
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public TokenDTO getTokenDTO() {
		return tokenDTO;
	}
	public void setTokenDTO(TokenDTO tokenDTO) {
		this.tokenDTO = tokenDTO;
	}
	
	public String getPasswordRep() {
		return passwordRep;
	}
	public void setPasswordRep(String passwordRep) {
		this.passwordRep = passwordRep;
	}
	
	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", nickName=" + nickName + ", password=" + password + ", deviceToken="
				+ deviceToken + ", token=" + token + ", email=" + email + ", Sex=" + Sex + ", CP=" + CP + ", birthDate="
				+ birthDate + ", registrationDate=" + registrationDate + ", enterpriseId=" + enterpriseId + ", rol="
				+ rol + ", isAdmin=" + isAdmin + ", active=" + active + ", tokenDTO=" + tokenDTO + "]";
	}
    
    
	
}
