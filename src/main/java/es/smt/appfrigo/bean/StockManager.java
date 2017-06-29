package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockManager implements Serializable{

	private static final long serialVersionUID = 1L;
	private int operator;
	private List<Stock> stock;
	
	public StockManager() {
		super();
	}
	public StockManager(int operator, List<Stock> stock) {
		super();
		this.operator = operator;
		this.stock = stock;
	}

	
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public List<Stock> getStock() {
		return stock;
	}
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "StockManager [operator=" + operator + "]";
	}
	
	
}
