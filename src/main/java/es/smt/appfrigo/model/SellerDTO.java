package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.bean.Image;
import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.bean.Registration;
import es.smt.appfrigo.bean.Result;
import es.smt.appfrigo.bean.StatisticsMini;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerDTO extends Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("sellerId")
	public int id;
	@NotEmpty(message="Name is required")
    public String name;
	@NotEmpty(message="Surname is required")
    public String surname;
    public UserMiniDTO user;
    public List<BusinessMiniDTO> business;
    public List<Integer> businessList;
    public boolean active;
    private Region region;
  	private Registration registration;
 	private boolean gps;
 	private String photo;
 	@JsonIgnore
 	private Image file;
 	
 	private StatisticsMini data;


     public SellerDTO() {
		super();
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

	public boolean isGps() {
		return gps;
	}

	public void setGps(boolean gps) {
		this.gps = gps;
	}

	public List<BusinessMiniDTO> getBusiness() {
		return business;
	}

	public void setBusiness(List<BusinessMiniDTO> business) {
		this.business = business;
	}

	public List<Integer> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<Integer> businessList) {
		this.businessList = businessList;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public UserMiniDTO getUser() {
		return user;
	}

	public void setUser(UserMiniDTO user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public StatisticsMini getData() {
		return data;
	}

	public void setData(StatisticsMini data) {
		this.data = data;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Image getFile() {
		return file;
	}

	public void setFile(Image file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "SellerDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", user=" + user + ", business="
				+ business + ", active=" + active + ", region=" + region + ", registration=" + registration
				+ "]";
	}

}
