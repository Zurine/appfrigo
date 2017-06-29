package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompoundProductDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int productId;
	 private String name;
     private double price;
     private List<CompositionDTO> composition;
     
	public CompoundProductDTO() {
		super();
	}

	public CompoundProductDTO(int productId, String name, double price, List<CompositionDTO> composition) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.composition = composition;
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

	public List<CompositionDTO> getComposition() {
		return composition;
	}

	public void setComposition(List<CompositionDTO> composition) {
		this.composition = composition;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "CompoundProductDTO [productId=" + productId + ", name=" + name + ", price=" + price + ", composition="
				+ (composition != null ? composition.subList(0, Math.min(composition.size(), maxLen)) : null) + "]";
	}
     
     
}
