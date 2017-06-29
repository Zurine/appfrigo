package es.smt.appfrigo.bean;

import java.io.Serializable;

public class SupportBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String subject;
	private String name;
	private String email;
	private String message;

	public SupportBean() {
		super();
	}
	
	public SupportBean(long id, String subjet, String email, String message, String name) {
		super();
		this.id = id;
		this.subject = subjet;
		this.email = email;
		this.message = message;
		this.name = name;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SupportBean [id=" + id + ", subject=" + subject + ", name=" + name + ", email=" + email + ", message="
				+ message + "]";
	}
	
	
	
}
