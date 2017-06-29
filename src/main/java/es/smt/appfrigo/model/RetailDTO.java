package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String image;
	private List<RetailLocationDTO> locations;
	
	public RetailDTO() {
		super();
	}

	public RetailDTO(int id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<RetailLocationDTO> getLocations() {
		return locations;
	}

	public void setLocations(List<RetailLocationDTO> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "RetailDTO [id=" + id + ", name=" + name + ", image=" + image + "]";
	}

}
