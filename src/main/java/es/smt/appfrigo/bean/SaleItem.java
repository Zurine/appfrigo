package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.model.CompositionDTO;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int productId;
	private int amount;
	private boolean gift;
	private List<Integer> components;
	private List<CompositionDTO> compositionList;
	
	public SaleItem() {
		super();
	}

	public SaleItem(int productId, int amount, boolean gift, List<Integer> components) {
		super();
		this.productId = productId;
		this.amount = amount;
		this.gift = gift;
		this.components = components;
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

	public List<CompositionDTO> getCompositionList() {
		return compositionList;
	}

	public void setCompositionList(List<CompositionDTO> compositionList) {
		this.compositionList = compositionList;
	}




	
	
}
