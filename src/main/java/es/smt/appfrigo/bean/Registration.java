package es.smt.appfrigo.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import es.smt.appfrigo.constants.Constants;

public class Registration implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	
    private String nickName;
    @NotBlank
    private String password;
    private String repPassword;
    @NotEmpty(message="Email is required")
    @NotNull(message="Email is required")
    @Pattern(regexp=Constants.EMAIL_PATTERN)
    private String email;
    private int equipmentId;
    
	public Registration() {
		super();
	}

	public Registration(String nickName, String password, String repPassword, String email,int equipmentId) {
		super();
		this.nickName = nickName;
		this.password = password;
		this.repPassword = repPassword;
		this.email = email;
		this.equipmentId = equipmentId;
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

	public String getRepPassword() {
		return repPassword;
	}

	public void setRepPassword(String repPassword) {
		this.repPassword = repPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RegistrationBean [nickName=" + nickName + ", password=" + password + ", repPassword=" + repPassword
				+ ", email=" + email + ", equipmentId=" + equipmentId + "]";
	}
}
