package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessF implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<UserMicro> users;
	
	public BusinessF() {
		super();
	}

	public BusinessF(int id, String name, List<UserMicro> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
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

	public List<UserMicro> getUsers() {
		return users;
	}

	public void setUsers(List<UserMicro> users) {
		this.users = users;
	}
	
	
}
