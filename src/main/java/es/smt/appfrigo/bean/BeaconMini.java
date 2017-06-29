package es.smt.appfrigo.bean;

import java.io.Serializable;

import es.smt.appfrigo.model.BusinessMiniDTO;


public class BeaconMini implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int id;
    private String name;
    private BusinessMiniDTO business;
    private boolean active;
    
	public BeaconMini() {
		super();
	}

	public BeaconMini(int id, String name, BusinessMiniDTO business, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.business = business;
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

	public BusinessMiniDTO getBusiness() {
		return business;
	}

	public void setBusiness(BusinessMiniDTO business) {
		this.business = business;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "BeaconMini [id=" + id + ", name=" + name + ", business=" + business + ", active=" + active
				+ "]";
	}

}
