package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageMini implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String username;
    private String image;
    private String date;
    private String message;
    
	public MessageMini() {
		super();
	}
	public MessageMini(int id, String username, String image, String date, String message) {
		super();
		this.id = id;
		this.username = username;
		this.image = image;
		this.date = date;
		this.message = message;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageMini [id=" + id + ", username=" + username + ", image=" + image + ", date=" + date + ", message="
				+ message + "]";
	}
    
    
}
