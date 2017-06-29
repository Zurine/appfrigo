package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductStock implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id;
    private String name;
    private String photo;
    private int stock;
    private int minStock;
    private int businessTotal;
    
    @JsonIgnore
    private int operatorId;
    
    
	public ProductStock() {
		super();
	}
	
	

	public ProductStock(int id, String name, String photo, int stock, int businessTotal) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.stock = stock;
		this.businessTotal = businessTotal;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getBusinessTotal() {
		return businessTotal;
	}

	public void setBusinessTotal(int businessTotal) {
		this.businessTotal = businessTotal;
	}

	
	
	public int getMinStock() {
		return minStock;
	}



	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	

	public int getOperatorId() {
		return operatorId;
	}



	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}



	@Override
	public String toString() {
		return "ProductStock [id=" + id + ", name=" + name + ", photo=" + photo + ", stock=" + stock
				+ ", businessTotal=" + businessTotal + "]";
	}
    
    
    
    

}
