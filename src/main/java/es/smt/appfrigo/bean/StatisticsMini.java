package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticsMini implements Serializable{

	private static final long serialVersionUID = 1L;
	private int equipments;
	private int sellers ;
	private double sales;
	private int amount;
	
	public StatisticsMini() {
		super();
	}

	public StatisticsMini(int equipments, int sellers, double sales, int amount) {
		super();
		this.equipments = equipments;
		this.sellers = sellers;
		this.sales = sales;
		this.amount = amount;
	}

	public int getEquipments() {
		return equipments;
	}

	public void setEquipments(int equipments) {
		this.equipments = equipments;
	}

	public int getSellers() {
		return sellers;
	}

	public void setSellers(int sellers) {
		this.sellers = sellers;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "StatisticsMini [equipments=" + equipments + ", sellers=" + sellers + ", sales=" + sales + ", amount="
				+ amount + "]";
	}
	
	
}
