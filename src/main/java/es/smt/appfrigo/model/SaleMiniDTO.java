package es.smt.appfrigo.model;

import java.util.List;

public class SaleMiniDTO {

    private int productId;
    private String name;
    private String photo;
    private int amount;
    private double price;
    private boolean gift;
    private List<Integer> components;
    
	public SaleMiniDTO() {
		super();
	}



	public SaleMiniDTO(int productId, String name, String photo, int amount, double price, boolean gift) {
		super();
		this.productId = productId;
		this.name = name;
		this.photo = photo;
		this.amount = amount;
		this.price = price;
		this.gift = gift;
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

	
	
	public boolean isGift() {
		return gift;
	}



	public void setGift(boolean gift) {
		this.gift = gift;
	}


	

	public List<Integer> getComponents() {
		return components;
	}



	public void setComponents(List<Integer> components) {
		this.components = components;
	}



	@Override
	public String toString() {
		return "SaleMicroDTO [productId=" + productId + ", name=" + name + ", photo=" + photo + ", amount=" + amount
				+ ", price=" + price + "]";
	}
}
