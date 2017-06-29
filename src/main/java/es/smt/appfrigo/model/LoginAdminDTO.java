package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginAdminDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private UserAdminDTO usuario;
	private HomeDTO home;
	
	public LoginAdminDTO() {
		super();
	}
	
	public LoginAdminDTO(UserAdminDTO usuario, HomeDTO home) {
		super();
		this.usuario = usuario;
		this.home = home;
	}
	public UserAdminDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UserAdminDTO usuario) {
		this.usuario = usuario;
	}

	
	@Override
	public String toString() {
		return "LoginDTO [usuario=" + usuario + ", home=" + home + "]";
	}
	
	
}

