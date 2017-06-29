package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class SaleMini implements Serializable{

	private static final long serialVersionUID = 1L;
	private int businessId; 
	private int sellerId;
	private int productId;
	private int amount;
	private int row;
	@NotNull(message="Date is required")
	@NotEmpty(message="Date is required")
	private String date;
	private List<SaleItem> item;
	
	public SaleMini() {
		super();
	}


	public SaleMini(int businessId, int productId, int amount, String date) {
		super();
		this.businessId = businessId;
		this.productId = productId;
		this.amount = amount;
		this.date = date;
	}


	public int getBusinessId() {
		return businessId;
	}


	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getSellerId() {
		return sellerId;
	}


	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}


	public List<SaleItem> getItem() {
		return item;
	}


	public void setItem(List<SaleItem> item) {
		this.item = item;
	}
	
	


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	@Override
	public String toString() {
		return "SalesMini [businessId=" + businessId + ", productId=" + productId + ", amount=" + amount + ", date="
				+ date + "]";
	}

	
	
}
