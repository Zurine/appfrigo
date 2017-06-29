package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import es.smt.appfrigo.model.BusinessMiniDTO;

public class Promotion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	@NotNull(message = "Name cannot be empty")
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	@NotNull(message = "Description cannot be empty")
	@NotEmpty(message = "Description cannot be empty")
	private String description;
	private String url;
	private boolean welcome;
	private int type;
	private boolean redimible;
	private boolean active;
//	private List<Integer> productsId;
//	private List<Product> products;
	private double price;
	private List<Integer> selectedConditions;
	private List<Item> conditions;
	private List<BusinessMiniDTO> business;
	private Image image;
	private String selected;
	private List<PromoProduct> products;
	private List<Integer> productIds;
	
	public Promotion() {
		super();
	}

	//List<Product> products,List<Integer> productsId, 
	public Promotion(int id, String name, String description, String url, int type,boolean redimible, 
			boolean active,double price,List<Integer> selectedConditions, Image file) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
		this.type = type;
		this.redimible = redimible;
		this.active = active;
//		this.products = products;
//		this.productsId = productsId;
		this.price = price;
		this.selectedConditions = selectedConditions;
		this.image = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isRedimible() {
		return redimible;
	}

	public void setRedimible(boolean redimible) {
		this.redimible = redimible;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<PromoProduct> getProducts() {
		return products;
	}

	public void setProducts(List<PromoProduct> products) {
		this.products = products;
	}
	

	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}

	/*	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Integer> getProductsId() {
		return productsId;
	}

	public void setProductsId(List<Integer> productsId) {
		this.productsId = productsId;
	}
*/
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Integer> getSelectedConditions() {
		return selectedConditions;
	}

	public void setSelectedConditions(List<Integer> selectedConditions) {
		this.selectedConditions = selectedConditions;
	}

	public List<Item> getConditions() {
		return conditions;
	}

	public void setConditions(List<Item> conditions) {
		this.conditions = conditions;
	}

	public List<BusinessMiniDTO> getBusiness() {
		return business;
	}

	public void setBusiness(List<BusinessMiniDTO> business) {
		this.business = business;
	}

	public boolean isWelcome() {
		return welcome;
	}

	public void setWelcome(boolean welcome) {
		this.welcome = welcome;
	}
	
	

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", name=" + name + ", description=" + description + ", url=" + url + ", welcome="
				+ welcome + ", type=" + type + ", redimible=" + redimible + ", active=" + active
				+ ", price=" + price + ", selectedConditions="
				+ selectedConditions + ", conditions=" + conditions + ", business=" + business + "]";
	}


}
