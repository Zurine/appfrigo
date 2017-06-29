package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

public class SelectSeller implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int businessId;
	private String businessName;
	private List<Integer> sellers;
	
	public SelectSeller() {
		super();
	}

	public SelectSeller(int businessId, String businessName, List<Integer> sellers) {
		super();
		this.businessId = businessId;
		this.businessName = businessName;
		this.sellers = sellers;
	}


	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public List<Integer> getSellers() {
		return sellers;
	}

	public void setSellers(List<Integer> sellers) {
		this.sellers = sellers;
	}

	@Override
	public String toString() {
		return "SelectSeller [businessId=" + businessId + ", businessName=" + businessName + ", sellers=" + sellers
				+ "]";
	}
}
