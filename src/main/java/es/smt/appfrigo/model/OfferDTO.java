package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int offerId;
	private String name;
	private String url;
	private int type;
    private String errorText;
    private String description;
    private boolean active;
    private boolean isWelcomeOffer;
    private boolean isRedimible;
    private int enterpriseId;
    @JsonProperty("Condiciones")
    private List<Integer> condition;
    private List<ConditionDTO> objCondiciones;
    private double price;
    public List<ProductCompositionDTO> products;

	public OfferDTO() {
		super();
	}

	/**
	 * @param offerId
	 * @param name
	 * @param url
	 * @param type
	 * @param errorText
	 * @param description
	 * @param active
	 * @param isWelcomeOffer
	 * @param isRedimible
	 * @param enterpriseId
	 * @param product
	 * @param condition
	 */
	public OfferDTO(int offerId, String name, String url, int type, String errorText, String description,
			boolean active, boolean isWelcomeOffer, boolean isRedimible, int enterpriseId,
			List<Integer> condition, double price,List<ConditionDTO> objCondiciones) {
		super();
		this.offerId = offerId;
		this.name = name;
		this.url = url;
		this.type = type;
		this.errorText = errorText;
		this.description = description;
		this.active = active;
		this.isWelcomeOffer = isWelcomeOffer;
		this.isRedimible = isRedimible;
		this.enterpriseId = enterpriseId;
		this.condition = condition;
	//	this.products = products;
		this.price = price;
		this.objCondiciones = objCondiciones;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
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

	public boolean isIsWelcomeOffer() {
		return isWelcomeOffer;
	}

	public void setIsWelcomeOffer(boolean isWelcomeOffer) {
		this.isWelcomeOffer = isWelcomeOffer;
	}

	public boolean isIsRedimible() {
		return isRedimible;
	}

	public void setIsRedimible(boolean isRedimible) {
		this.isRedimible = isRedimible;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public List<Integer> getCondition() {
		return condition;
	}

	public void setCondition(List<Integer> condition) {
		this.condition = condition;
	}
	
/*	public List<ProductMiniDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductMiniDTO> products) {
		this.products = products;
	}*/

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<ConditionDTO> getObjCondiciones() {
		return objCondiciones;
	}

	public void setObjCondiciones(List<ConditionDTO> objCondiciones) {
		this.objCondiciones = objCondiciones;
	}
	
	

	public boolean isWelcomeOffer() {
		return isWelcomeOffer;
	}

	public void setWelcomeOffer(boolean isWelcomeOffer) {
		this.isWelcomeOffer = isWelcomeOffer;
	}

	public boolean isRedimible() {
		return isRedimible;
	}

	public void setRedimible(boolean isRedimible) {
		this.isRedimible = isRedimible;
	}

	public List<ProductCompositionDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductCompositionDTO> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OfferDTO [offerId=" + offerId + ", name=" + name + ", url=" + url + ", type=" + type + ", errorText="
				+ errorText + ", description=" + description + ", active=" + active + ", isWelcomeOffer="
				+ isWelcomeOffer + ", isRedimible=" + isRedimible + ", enterpriseId=" + enterpriseId + ", condition="
				+ condition + ", products=" + products + ", objCondiciones=" + objCondiciones + ", price=" + price
				+ "]";
	}
}
