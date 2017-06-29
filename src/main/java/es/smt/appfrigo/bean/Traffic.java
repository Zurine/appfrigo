package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.Date;

import es.smt.appfrigo.model.BusinessMiniDTO;


public class Traffic implements Serializable{

	private static final long serialVersionUID = 1L;

	private BusinessMiniDTO business;
	private String label;
	private Date date;
	private double total;
	private int unities;
	private int people;
	private double rate;
	
	public Traffic() {
		super();
	}

	public Traffic(BusinessMiniDTO business, String label, double total, int unities, int people, double rate) {
		super();
		this.business = business;
		this.label = label;
		this.total = total;
		this.unities = unities;
		this.people = people;
		this.rate = rate;
	}

	public BusinessMiniDTO getBusiness() {
		return business;
	}

	public void setBusiness(BusinessMiniDTO business) {
		this.business = business;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getUnities() {
		return unities;
	}

	public void setUnities(int unities) {
		this.unities = unities;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Traffic [business=" + business + ", label=" + label + ", date=" + date + ", total=" + total
				+ ", unities=" + unities + ", people=" + people + ", rate=" + rate + "]";
	}

}