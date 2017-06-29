package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.model.ProductDTO;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category extends Result  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	@Size(min=3, max=50, message="Name is required and must be between 3 and 50")
	private String name;
	private boolean active;
	@JsonProperty("image")
	private String url;
	private List<ProductDTO> products;
	@JsonIgnore
	private Image image;
	
	public Category() {
		super();
	}
	
	public Category(int id, String name, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
	}
	
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Category(int id, String name, boolean active, String url, List<ProductDTO> products, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.url = url;
		this.products = products;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + ", active=" + active + "]";
	}
	
}
