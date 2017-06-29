package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.smt.appfrigo.validation.CustomJsonDateDeserializer;



@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserNotif implements Serializable{


	private static final long serialVersionUID = 1L;
//	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	private String date;
    private UserMicro user;
    
	public UserNotif() {
		super();
	}

	public UserNotif(String date, UserMicro user) {
		super();
		this.date = date;
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
//
	public UserMicro getUser() {
		return user;
	}

	public void setUser(UserMicro user) {
		this.user = user;
	}
//    
    
    
}
