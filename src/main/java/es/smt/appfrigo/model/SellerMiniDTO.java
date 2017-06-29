package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerMiniDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
    private int userId ;
    private String name;
    private String surname;
    private boolean active;
    
	public SellerMiniDTO() {
		super();
	}
	
	public SellerMiniDTO(int id, int userId, String name,String surname, boolean active) {
		super();
		this.id = id;
		this.userId = userId;
		this.surname = name;
		this.name = surname;
		this.active = active;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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

	@Override
	public String toString() {
		return "SellerMiniDTO [id=" + id + ", userId=" + userId + ", name=" + name + ", surname=" + surname
				+ ", active=" + active + "]";
	}

	
	
}
