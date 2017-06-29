package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockItem implements Serializable{


	private static final long serialVersionUID = 1L;
    private int stock;
    private List<Integer> products;
    private int operator;
    
	public StockItem() {
		super();
	}

	public StockItem(int stock, List<Integer> products) {
		super();
		this.stock = stock;
		this.products = products;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Integer> getProducts() {
		return products;
	}

	public void setProducts(List<Integer> products) {
		this.products = products;
	}
	
	


	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "StockItem [stock=" + stock + ", products=" + products + "]";
	}
    
}
