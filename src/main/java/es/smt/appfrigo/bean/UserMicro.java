package es.smt.appfrigo.bean;

import java.io.Serializable;

public class UserMicro implements Serializable{

	private static final long serialVersionUID = -7766880882146073452L;
	private int id;
    private String name;
    private String email;
    private String photo;
    
    
    
	public UserMicro() {
		super();
	}



	public UserMicro(int id, String name, String email, String photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.photo = photo;
	}



	public int getId() {
		return id;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	@Override
	public String toString() {
		return "UserMicro [id=" + id + ", name=" + name + ", email=" + email + ", photo=" + photo + "]";
	}
    
    
}
