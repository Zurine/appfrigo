package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.smt.appfrigo.validation.CustomJsonDateDeserializer;


@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id;
	private ProductMicro product;
    private int stock;
    private int minStock;
    private double price;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String date;
    
    
	public Stock() {
		super();
	}
	public Stock(ProductMicro product, int stock, int minStock) {
		super();
		this.product = product;
		this.stock = stock;
		this.minStock = minStock;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductMicro getProduct() {
		return product;
	}
	public void setProduct(ProductMicro product) {
		this.product = product;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getMinStock() {
		return minStock;
	}
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Stock [product=" + product + ", stock=" + stock + ", minStock=" + minStock + "]";
	}
    
    
}
