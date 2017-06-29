package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

public class BusinessProduct implements Serializable{

	private static final long serialVersionUID = 1L;
	private int business;
	private List<ProdBusDetail> products;
	
	public BusinessProduct() {
		super();
	}

	public BusinessProduct(int business, List<ProdBusDetail> products) {
		super();
		this.business = business;
		this.products = products;
	}

	public int getBusiness() {
		return business;
	}

	public void setBusiness(int business) {
		this.business = business;
	}

	public List<ProdBusDetail> getProducts() {
		return products;
	}

	public void setProducts(List<ProdBusDetail> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "BusinessProduct [business=" + business + ", products=" + products + "]";
	}
	
	
}

