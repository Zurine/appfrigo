package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.smt.appfrigo.validation.CustomJsonDateDeserializer;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private UserMicro user;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String date;
    private int state;
    private List<MessageMini> messages;
    
	public Message() {
		super();
	}
	public Message(int id, UserMicro user, String date, int state, List<MessageMini> messages) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.state = state;
		this.messages = messages;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserMicro getUser() {
		return user;
	}
	public void setUser(UserMicro user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<MessageMini> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageMini> messages) {
		this.messages = messages;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", user=" + user + ", date=" + date + ", state=" + state + ", messages=" + messages
				+ "]";
	}
    
	
    
}
