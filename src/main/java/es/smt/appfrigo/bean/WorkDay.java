package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//import es.smt.appfrigo.bean.Location;
import es.smt.appfrigo.validation.CustomJsonDateDeserializer;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkDay implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
    private double value;
    private String time;
    @JsonProperty("startDate")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String date;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
  
    private String endDate;
    private int amount;
    private double latitud;
    private double longitud;
    private String label;
    private double totalUser;
    private double totalSystem;
    
	public WorkDay() {
		super();
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getTotalUser() {
		return totalUser;
	}
	public void setTotalUser(double totalUser) {
		this.totalUser = totalUser;
	}
	public double getTotalSystem() {
		return totalSystem;
	}
	public void setTotalSystem(double totalSystem) {
		this.totalSystem = totalSystem;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
