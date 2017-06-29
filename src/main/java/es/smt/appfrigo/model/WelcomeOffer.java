package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeOffer implements Serializable{

	private static final long serialVersionUID = 1L;

    private int offerId;
    private String name;
    private int offerType;
    private String offerURL;
    private String offerDescription;
    private boolean isRedimible;
    @JsonProperty("Business")
    private List<BusinessMiniDTO> business;

	public WelcomeOffer() {
		super();
	}

	public WelcomeOffer(int offerId, String name, int offerType, String offerURL, String offerDescription,
			boolean isRedimible, List<BusinessMiniDTO> business) {
		super();
		this.offerId = offerId;
		this.name = name;
		this.offerType = offerType;
		this.offerURL = offerURL;
		this.offerDescription = offerDescription;
		this.isRedimible = isRedimible;
		this.business = business;
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

	public int getOfferType() {
		return offerType;
	}

	public void setOfferType(int offerType) {
		this.offerType = offerType;
	}

	public String getOfferURL() {
		return offerURL;
	}

	public void setOfferURL(String offerURL) {
		this.offerURL = offerURL;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public boolean isRedimible() {
		return isRedimible;
	}

	public void setRedimible(boolean isRedimible) {
		this.isRedimible = isRedimible;
	}

	public List<BusinessMiniDTO> getBusiness() {
		return business;
	}

	public void setBusiness(List<BusinessMiniDTO> business) {
		this.business = business;
	}

	@Override
	public String toString() {
		return "WelcomeOffer [offerId=" + offerId + ", name=" + name + ", offerType=" + offerType + ", offerURL="
				+ offerURL + ", offerDescription=" + offerDescription + ", isRedimible=" + isRedimible + ", business="
				+ business + "]";
	}
    
    
}

