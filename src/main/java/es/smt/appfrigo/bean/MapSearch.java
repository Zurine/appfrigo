package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;


public class MapSearch implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Integer> equipment;
	@NotNull
	private String startDate;
	@NotNull
	private String endDate;
	private List<Integer> product;
	
	public MapSearch() {
		super();
	}

	public MapSearch(List<Integer> equipment, String startDate, String endDate, List<Integer> product) {
		super();
		this.equipment = equipment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.product = product;
	}

	public List<Integer> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Integer> equipment) {
		this.equipment = equipment;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Integer> getProduct() {
		return product;
	}

	public void setProduct(List<Integer> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "MapSearch [equipment=" + equipment + ", startDate=" + startDate + ", endDate=" + endDate + ", product="
				+ product + "]";
	}


}
