package es.smt.appfrigo.bean;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region extends Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	
	@JsonProperty("regionId")
	private int id;
	@Size(min=2, max=20, message="Name is required and must be between 4 and 100")
	private String name;
	@Size(min=4, max=100, message="Description is required and must be between 4 and 100")
	private String description;
	private boolean active;

	 
	public Region() {
		super();
	}
	
	public Region(int id) {
		super();
		this.id = id;
	}

	public Region(String name) {
		super();
		this.name = name;
	}
	
	public Region(int id,String name,  String description, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "RegionDTO [id=" + id + ", name=" + name + ", description=" + description + ", active="
				+ active + "]";
	}
	 
}