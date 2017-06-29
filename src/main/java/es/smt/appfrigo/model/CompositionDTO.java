package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.smt.appfrigo.bean.Category;
import es.smt.appfrigo.bean.ProductMicro;


@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompositionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("category")
	private Category category;
	private List<ProductMicro> productMinis;
    private int amount;
    private double quantity;
    
//    private Item category;
    
	public CompositionDTO() {
		super();
	}

	
	public CompositionDTO(Category category, List<ProductMicro> products, int amount) {
		super();
		this.category = category;
		this.productMinis = products;
		this.amount = amount;
	}




	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}



	
	public List<ProductMicro> getProductMinis() {
		return productMinis;
	}


	public void setProductMinis(List<ProductMicro> productMinis) {
		this.productMinis = productMinis;
	}


	
	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CompositionDTO [category=" + category + ", productMinis=" + productMinis + ", amount=" + amount + "]";
	}
   

}
