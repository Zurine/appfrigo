package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import es.smt.appfrigo.model.CompositionDTO;
import es.smt.appfrigo.model.ProductMiniDTO;

public class SelectProduct extends Result implements Serializable{

	private static final long serialVersionUID = 1L;
	private int stock;
	private double price;
	private int minStock;
	private double iva;
	private List<Integer> products;
	private int business;
	private boolean active;
	private List<List<Integer>> components;
	private List<CompositionDTO> composition;
	private ProductMiniDTO product; 
	private boolean sellable;
	private boolean update;
	private int type;
	private String stringSimple;
	private String stringCompound;
	
	
	public SelectProduct() {
		super();
	}

	public SelectProduct(int stock, int minStock, int iva, List<Integer> products, int business) {
		super();
		this.stock = stock;
		this.minStock = minStock;
		this.iva = iva;
		this.products = products;
		this.business = business;
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

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public List<Integer> getProducts() {
		return products;
	}

	public void setProducts(List<Integer> products) {
		this.products = products;
	}

	public int getBusiness() {
		return business;
	}

	public void setBusiness(int business) {
		this.business = business;
	}

	public List<CompositionDTO> getComposition() {
		return composition;
	}

	public void setComposition(List<CompositionDTO> composition) {
		this.composition = composition;
	}

	public List<List<Integer>> getComponents() {
		return components;
	}

	public void setComponents(List<List<Integer>> components) {
		this.components = components;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean isSellable() {
		return sellable;
	}

	public void setSellable(boolean sellable) {
		this.sellable = sellable;
	}

	public ProductMiniDTO getProduct() {
		return product;
	}

	public void setProduct(ProductMiniDTO product) {
		this.product = product;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
	

	public String getStringSimple() {
		return stringSimple;
	}

	public void setStringSimple(String stringSimple) {
		this.stringSimple = stringSimple;
	}

	public String getStringCompound() {
		return stringCompound;
	}

	public void setStringCompound(String stringCompound) {
		this.stringCompound = stringCompound;
	}

	@Override
	public String toString() {
		return "SelectProduct [stock=" + stock + ", price=" + price + ", minStock=" + minStock + ", iva=" + iva
				+ ", products=" + products + ", business=" + business + ", active=" + active + ", components="
				+ components + ", composition=" + composition + "]";
	}


	
	
	
}