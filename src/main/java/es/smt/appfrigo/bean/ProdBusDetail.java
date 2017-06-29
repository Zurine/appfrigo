package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdBusDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	private ProductMicro product;
	private double price;
	private int stock;
	private int minStock;
	private boolean active;
	private double tax;
	private StatisticsMini data;
      
	public ProdBusDetail() {
		super();
	}

	public ProdBusDetail(ProductMicro product, double price, int stock, int minStock, boolean active, double tax,
			StatisticsMini data) {
		super();
		this.product = product;
		this.price = price;
		this.stock = stock;
		this.minStock = minStock;
		this.active = active;
		this.tax = tax;
		this.data = data;
	}

	public ProdBusDetail(int id,String name) {
		super();
		this.product = new ProductMicro(id,name,"");
	}
	
	public ProductMicro getProduct() {
		return product;
	}

	public void setProduct(ProductMicro product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public StatisticsMini getData() {
		return data;
	}

	public void setData(StatisticsMini data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ProdBusDetail [product=" + product + ", price=" + price + ", stock=" + stock + ", minStock=" + minStock
				+ ", active=" + active + ", tax=" + tax + ", data=" + data + "]";
	}
      
	
      
}
