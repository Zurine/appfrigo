package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sales  implements Serializable{


	private static final long serialVersionUID = 1L;
	private int productId;
    private String name;
    private double price;
    private String image;
    private int number;
    private int gifts;
    
	public Sales() {
		super();
	}
	
	public Sales(int productId, String name, double price, String image, int number, int gifts) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.image = image;
		this.number = number;
		this.gifts = gifts;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getGifts() {
		return gifts;
	}
	public void setGifts(int gifts) {
		this.gifts = gifts;
	}
	@Override
	public String toString() {
		return "Sales [productId=" + productId + ", name=" + name + ", price=" + price + ", image=" + image
				+ ", number=" + number + ", gifts=" + gifts + "]";
	}
    
    
}
