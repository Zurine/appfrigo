package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapSales implements Serializable{

	private static final long serialVersionUID = 1L;
	private double lat;
    private double lon;
    private int amount;
    private double total;
    
	public MapSales() {
		super();
	}

	public MapSales(double lat, double lon, int amount, double total) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.amount = amount;
		this.total = total;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "MapSales [lat=" + lat + ", lon=" + lon + ", amount=" + amount + ", total=" + total + "]";
	}
    
    
}
