package es.smt.appfrigo.bean;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import es.smt.appfrigo.model.BusinessMiniDTO;


public class Seller implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	@NotEmpty(message="Name is required")
	private String name;
	@NotEmpty(message="Surname is required")
	private String surname;
	private UserMini user;
	private Registration registration;
	private BusinessMiniDTO business;
	private Region region;
	private boolean active;
//	private List<__WorkDayDTO> workDays;
	private boolean gps;
     
    public Seller() {
		super();
    }
    
    public Seller(int id, String name) {
		super();
		this.id = id;
		this.name = name;

	}
    

	public Seller(int id, String name, String surname, UserMini user, Registration registration,
			BusinessMiniDTO business, Region region, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.user = user;
		this.registration = registration;
		this.business = business;
		this.region = region;
		this.active = active;
	}



	public UserMini getUser() {
		return user;
	}

	public void setUser(UserMini user) {
		this.user = user;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	
	

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	
	
//
//	public List<__WorkDayDTO> getWorkDays() {
//		return workDays;
//	}
//
//
//
//	public void setWorkDays(List<__WorkDayDTO> workDays) {
//		this.workDays = workDays;
//	}



	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", surname=" + surname + ", user=" + user + ", business="
				+ business + ", active=" + active + "]";

	}
}
