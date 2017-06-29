package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductMicro implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private String photo;
    
	public ProductMicro() {
		super();
	}
	
	public ProductMicro(int id, String name, String photo) {
		super();
		this.id = id;
		this.name = name;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "ProductMicro [id=" + id + ", name=" + name + ", photo=" + photo + "]";
	}
    
    
}
