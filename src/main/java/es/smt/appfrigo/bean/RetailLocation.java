package es.smt.appfrigo.bean;

import java.io.Serializable;

public class RetailLocation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private double lat;
	private double lon;
	private String address;
	private boolean active;
	private Retail retail;
	
	public RetailLocation() {
		super();
	}

	
	public RetailLocation(int id, String name, int retailId, double lat, double lon, String address, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.retail =  new Retail(retailId);
		this.lat = lat;
		this.lon = lon;
		this.address = address;
		this.active = active;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Retail getRetail() {
		return retail;
	}


	public void setRetail(Retail retail) {
		this.retail = retail;
	}


	@Override
	public String toString() {
		return "RetailLocation [id=" + id + ", name=" + name + ", lat=" + lat + ", lon=" + lon + ", address=" + address
				+ ", active=" + active + ", retail=" + retail + "]";
	}
	
	


}
