package es.smt.appfrigo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleMicroDTO {

    private int productId;
    private String name;
    private String photo;
    private int amount;
    private double price;
    
	public SaleMicroDTO() {
		super();
	}

	public SaleMicroDTO(int productId, String name, String photo, int amount, double price) {
		super();
		this.productId = productId;
		this.name = name;
		this.photo = photo;
		this.amount = amount;
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SaleMicroDTO [productId=" + productId + ", name=" + name + ", photo=" + photo + ", amount=" + amount
				+ ", price=" + price + "]";
	}
}
