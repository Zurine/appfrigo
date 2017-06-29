package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.StatisticsMini;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAdminDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("UserId")
	private int id;
	/**Authentication data**/
    private String password;
    @JsonIgnore
    private String passwordRep;
    @NotEmpty(message="Email is required")
    private String email;
    private TokenDTO tokenDTO;
    /**Extra data ***/
    private int enterpriseId;
    private boolean active;
    private List<Default> items;
    private String name;
    private String surname;
    private int type;
    @JsonProperty("image")
    private String url;
    @JsonIgnore
    private Image image;
    @JsonIgnore
    private List<Integer> businessIds;
    @JsonIgnore
    private String typeDesc;
    private String token;
    private StatisticsMini data;
    private String currency;
    private String phone;
    private boolean emailNot;
    
	public UserAdminDTO() {
		super();
	}
	
	public UserAdminDTO(int id, String password, String passwordRep, String email, TokenDTO tokenDTO, int enterpriseId,
			boolean active, List<Default> items, String name, String surname, int type, String url, Image image,
			List<Integer> businessIds, String typeDesc, String token, StatisticsMini data, String currency,
			String phone) {
		super();
		this.id = id;
		this.password = password;
		this.passwordRep = passwordRep;
		this.email = email;
		this.tokenDTO = tokenDTO;
		this.enterpriseId = enterpriseId;
		this.active = active;
		this.items = items;
		this.name = name;
		this.surname = surname;
		this.type = type;
		this.url = url;
		this.image = image;
		this.businessIds = businessIds;
		this.typeDesc = typeDesc;
		this.token = token;
		this.data = data;
		this.currency = currency;
		this.phone = phone;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordRep() {
		return passwordRep;
	}

	public void setPasswordRep(String passwordRep) {
		this.passwordRep = passwordRep;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Default> getItems() {
		return items;
	}

	public void setItems(List<Default> items) {
		this.items = items;
	}

	public TokenDTO getTokenDTO() {
		return tokenDTO;
	}

	public void setTokenDTO(TokenDTO tokenDTO) {
		this.tokenDTO = tokenDTO;
	}

	public int getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Integer> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Integer> businessIds) {
		this.businessIds = businessIds;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public StatisticsMini getData() {
		return data;
	}

	public void setData(StatisticsMini data) {
		this.data = data;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	public boolean isEmailNot() {
		return emailNot;
	}

	public void setEmailNot(boolean emailNot) {
		this.emailNot = emailNot;
	}

	@Override
	public String toString() {
		return "UserAdminDTO [id=" + id + ", password=" + password 
				+ ", tokenDTO=" + tokenDTO + ", email=" + email + ", enterpriseId=" + enterpriseId
				+ ", active=" + active
				+ ", business=" + items + ", name=" + name + ", surname=" + surname + ", type=" + type + "]";
	}
}