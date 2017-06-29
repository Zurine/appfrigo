package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Retail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Image image;
	private List<RetailLocation> locations;
	
	public Retail() {
		super();
	}

	public Retail(int id, String name, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}
	
	public Retail(int id) {
		super();
		this.id = id;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
	public List<RetailLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<RetailLocation> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "RetailDTO [id=" + id + ", name=" + name + ", image=" + image + "]";
	}

}
