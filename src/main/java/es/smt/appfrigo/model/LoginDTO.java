package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private UserDTO usuario ;
	private WelcomeOffer oferta;
	public LoginDTO() {
		super();
	}
	public LoginDTO(UserDTO usuario, WelcomeOffer oferta) {
		super();
		this.usuario = usuario;
		this.oferta = oferta;
	}
	public UserDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UserDTO usuario) {
		this.usuario = usuario;
	}
	public WelcomeOffer getOferta() {
		return oferta;
	}
	public void setOferta(WelcomeOffer oferta) {
		this.oferta = oferta;
	}
	
	
	/*public String get__type() {
		return __type;
	}
	public void set__type(String __type) {
		this.__type = __type;
	}*/
	@Override
	public String toString() {
		return "LoginDTO [usuario=" + usuario + ", oferta=" + oferta + "]";
	}
	
	
}
