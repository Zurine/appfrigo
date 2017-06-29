package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessTypeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public int id;
	@JsonProperty("description")
    public String name;
	
	public String icon;
    
	public BusinessTypeDTO() {
		super();
	}
	
	public BusinessTypeDTO(int id) {
		super();
		this.id = id;
	}
	
	public BusinessTypeDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "BusinessTypeDTO [id=" + id + ", name=" + name + "]";
	}
}
