package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailLocationDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String name;
    private double lat;
    private double lon;
    private String address;
    private int retailId;
    private boolean active;
    
	public RetailLocationDTO() {
		super();
	}

	public RetailLocationDTO(int id, String name, double lat, double lon, String address, int retailId,
			boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.address = address;
		this.retailId = retailId;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRetailId() {
		return retailId;
	}

	public void setRetailId(int retailId) {
		this.retailId = retailId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "RetailLocationDTO [id=" + id + ", name=" + name + ", lat=" + lat + ", lon=" + lon + ", address="
				+ address + ", retailId=" + retailId + ", active=" + active + "]";
	}
    
    
}
