package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import es.smt.appfrigo.bean.Category;

public class ProductCompositionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int productId;
    private String name;
    private double price;
    private int amount;
    private String image;
    private List<ProductMiniDTO> composition;
    private Category category;
    
	public ProductCompositionDTO() {
		super();
	}
	
	public ProductCompositionDTO(int productId) {
		super();
		this.productId = productId;
	}
	
	public ProductCompositionDTO(int productId, String name, double price, int amount, String image,
			List<ProductMiniDTO> composition, Category category) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.image = image;
		this.composition = composition;
		this.category = category;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<ProductMiniDTO> getComposition() {
		return composition;
	}
	public void setComposition(List<ProductMiniDTO> composition) {
		this.composition = composition;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "ProductCompositionDTO [productId=" + productId + ", name=" + name + ", price=" + price + ", amount="
				+ amount + ", image=" + image + ", composition=" + composition + ", category=" + category + "]";
	}
    
}
