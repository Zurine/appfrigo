package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

public class ProductBusinessDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int productId;
    private double price;
    private int stock;
    private int minStock;
    private boolean active;
    private double iva;
    private List<Integer> composition;
    private boolean sellable;
    
	public ProductBusinessDTO() {
		super();
	}
	public ProductBusinessDTO(int productId, double price, int stock, int minStock, boolean active, int iva,
			List<Integer> composition) {
		super();
		this.productId = productId;
		this.price = price;
		this.stock = stock;
		this.minStock = minStock;
		this.active = active;
		this.iva = iva;
		this.composition = composition;
	}
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public List<Integer> getComposition() {
		return composition;
	}
	public void setComposition(List<Integer> composition) {
		this.composition = composition;
	}
	
	public boolean isSellable() {
		return sellable;
	}
	public void setSellable(boolean sellable) {
		this.sellable = sellable;
	}
	@Override
	public String toString() {
		return "ProductBusinessDTO [productId=" + productId + ", price=" + price + ", stock=" + stock + ", minStock="
				+ minStock + ", active=" + active + ", iva=" + iva + ", composition=" + composition + "]";
	}
    
    

}
