package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.Result;
import es.smt.appfrigo.bean.StatisticsMini;


@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO  extends Result implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("productId")
	private int id;
	@NotEmpty(message="Name is required")
    private String name;
	@NotNull(message = "Price cannot be empty")
    private double price;
    private int stock;
    private int minStock;
    @Size( max=200, message="Description is too large")
	@NotEmpty(message="Description is required")
    @NotNull(message="Description is required")
    private String description;
    private boolean active;
    private int enterpriseId;
    private double iva;
    @JsonProperty("image")
    private String url;
    private ProductMiniDTO parent;
    private List<CompositionDTO> composition;
    private Category category;
    private int genericId;
    private int composed;
    @JsonIgnore
    private Image image;
    private int remove;
    private int type;
    private boolean sellable;
    private StatisticsMini data;
    
    
	public ProductDTO() {
		super();
	}

	
	public ProductDTO(int id, String name, double price, int stock, int minStock, String description, boolean active,
			int enterpriseId, int iva, String url, ProductMiniDTO parent, List<CompositionDTO> composition,
			Category category, int genericId, int composed, Image image, int remove, int type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.minStock = minStock;
		this.description = description;
		this.active = active;
		this.enterpriseId = enterpriseId;
		this.iva = iva;
		this.url = url;
		this.parent = parent;
		this.composition = composition;
		this.category = category;
		this.genericId = genericId;
		this.composed = composed;
		this.image = image;
		this.remove = remove;
		this.type = type;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getRemove() {
		return remove;
	}

	public void setRemove(int remove) {
		this.remove = remove;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}
	

	public ProductMiniDTO getParent() {
		return parent;
	}

	public void setParent(ProductMiniDTO parent) {
		this.parent = parent;
	}

	public List<CompositionDTO> getComposition() {
		return composition;
	}

	public void setComposition(List<CompositionDTO> composition) {
		this.composition = composition;
	}


	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public boolean isSellable() {
		return sellable;
	}


	public void setSellable(boolean sellable) {
		this.sellable = sellable;
	}


	public int getGenericId() {
		return genericId;
	}

	public void setGenericId(int genericId) {
		this.genericId = genericId;
	}

	
	
	public int getComposed() {
		return composed;
	}

	public void setComposed(int composed) {
		this.composed = composed;
	}
	

	public StatisticsMini getData() {
		return data;
	}


	public void setData(StatisticsMini data) {
		this.data = data;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock
				+ ", minStock=" + minStock + ", description=" + description + ", active=" + active + ", enterpriseId="
				+ enterpriseId + ", iva=" + iva + ", parent=" + parent + ", composition=" + composition + "]";
	}


 
}
