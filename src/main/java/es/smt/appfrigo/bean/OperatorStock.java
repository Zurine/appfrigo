package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

public class OperatorStock implements Serializable{


	private static final long serialVersionUID = 1L;
	private int operatorId;
	private List<ProductStock> products;
	
	public OperatorStock() {
		super();
	}

	public OperatorStock(int operatorId, List<ProductStock> products) {
		super();
		this.operatorId = operatorId;
		this.products = products;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public List<ProductStock> getProducts() {
		return products;
	}

	public void setProducts(List<ProductStock> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OperatorStock [operatorId=" + operatorId + ", products=" + products + "]";
	}
	
	
}
