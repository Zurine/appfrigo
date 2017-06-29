package es.smt.appfrigo.model;

import java.io.Serializable;

public class TokenDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String token;
    private String version;
    private String idioma;
    private int userId;
    private int enterpriseId;
    private String timezone;
    
	public TokenDTO() {
		super();
	}
	

	public TokenDTO(String token, String version, String idioma, int userId, int enterpriseId, String timezone) {
		super();
		this.token = token;
		this.version = version;
		this.idioma = idioma;
		this.userId = userId;
		this.enterpriseId = enterpriseId;
		this.timezone = timezone;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}


	public String getTimezone() {
		return timezone;
	}


	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}


	@Override
	public String toString() {
		return "TokenDTO [token=" + token + ", version=" + version + ", idioma=" + idioma + ", userId=" + userId
				+ ", enterpriseId=" + enterpriseId + ", timezone=" + timezone + "]";
	}

}
