package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.Date;

public class Location implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private double latitud;
	private double longitud;
	private String time;
	private String label;
	private double value;
	private int amount;
	
	private Date startDate;
	private Date endDate;

	public Location() {
		super();
	}


	
	public Location(double latitud, double longitud, String time, String label) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
		this.time = time;
		this.label = label;
	}

	
	public Location(double latitud, double longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}



	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}




	public double getValue() {
		return value;
	}



	public void setValue(double value) {
		this.value = value;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}
	
	



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	@Override
	public String toString() {
		return "Location [latitud=" + latitud + ", longitud=" + longitud + ", time=" + time + ", label=" + label + "]";
	}

   
}
