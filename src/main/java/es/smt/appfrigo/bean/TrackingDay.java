package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.smt.appfrigo.validation.CustomJsonDateDeserializer;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackingDay implements Serializable{

	private static final long serialVersionUID = 1L;
	private double value;
    private String time;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String date;
    private int amount;
    private double latitud;
    private double longitud;
//    @JsonIgnore
    private String label;
    
	public TrackingDay() {
		super();
	}

	public TrackingDay(double value, String time, String date, int amount, double latitud, double longitud) {
		super();
		this.value = value;
		this.time = time;
		this.date = date;
		this.amount = amount;
		this.latitud = latitud;
		this.longitud = longitud;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
	
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "TrackingDay [value=" + value + ", time=" + time + ", date=" + date + ", amount=" + amount + ", latitud="
				+ latitud + ", longitud=" + longitud + "]";
	}
    
    
}
