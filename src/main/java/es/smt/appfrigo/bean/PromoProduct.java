package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

public class PromoProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private List<Integer> products;
	private int amount;
	
	
	public PromoProduct() {
		super();
	}


	public PromoProduct(int id, List<Integer> products, int amount) {
		super();
		this.id = id;
		this.products = products;
		this.amount = amount;
	}
	

	public PromoProduct(int id) {
		super();
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Integer> getProducts() {
		return products;
	}


	public void setProducts(List<Integer> products) {
		this.products = products;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "PromoProduct [id=" + id + ", products=" + products + ", amount=" + amount + "]";
	}
	
	
	
	
}
