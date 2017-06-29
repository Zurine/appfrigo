package es.smt.appfrigo.bean;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel extends Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("channelId")
	private int id;
	@Size(min=4, max=100, message="Description is required and must be between 4 and 100")
	@JsonProperty("description")
	private String name;
	private boolean active;
	

	 
	public Channel() {
		super();
	}

	public Channel( String name) {
		super();
		this.name = name;
	}


	public Channel(int id, String name, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
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



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	@Override
	public String toString() {
		return "ChannelDTO [id=" + id + ", name=" + name + ", active=" + active + "]";
	}
	
	
	
	
	 
}
