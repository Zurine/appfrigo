package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductMiniDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("productId")
	private int id;
	private String name;
    private double price;
//    private int minStock;
//    private double tax;
    private String photo;
    private boolean active;
    private int genericId;
    private boolean compound;

//    private int amount;

    
    
	public ProductMiniDTO() {
		super();
	}
	
	public ProductMiniDTO(int id) {
		super();
		this.id = id;
	}
	
	public ProductMiniDTO(int id,String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ProductMiniDTO(int id, String name, double price, String photo, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.photo = photo;
		this.active = active;
	}

	public ProductMiniDTO(int id,  int parentId) {
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getGenericId() {
		return genericId;
	}

	public void setGenericId(int genericId) {
		this.genericId = genericId;
	}

	public boolean isCompound() {
		return compound;
	}

	public void setCompound(boolean compound) {
		this.compound = compound;
	}

	@Override
	public String toString() {
		return "ProductMiniDTO [id=" + id + ", name=" + name + ", price=" + price + ", photo=" + photo
				+ ", active=" + active  + ", isCompound=" + compound + "]";
	}

}
