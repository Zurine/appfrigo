package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.smt.appfrigo.model.BusinessMiniDTO;

public class Beacon implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int id;
	@NotNull(message="Name is required")
	@Size(min=2, max=30)
    private String name;
    private String description;
    @NotNull(message="minor is required")
    private int minor;
    @NotNull(message="major is required")
    private int major;
    private String localizacion;
    private String latitud;
    private String longitud;
    @NotNull(message="uid is required")
    private String uid;
    private BusinessMiniDTO business;
    private boolean active;
    private List<PromotionBeacon> promotions;
    
	public Beacon() {
		super();
	}

	public Beacon(int id, String name, String description, int minor, int major, String localizacion,
			String latitud, String longitud, String uid, BusinessMiniDTO businessBean, boolean active,List<PromotionBeacon> promotions ) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.minor = minor;
		this.major = major;
		this.localizacion = localizacion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.uid = uid;
		this.business = businessBean;
		this.active = active;
		this.promotions = promotions;
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public List<PromotionBeacon> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionBeacon> promotions) {
		this.promotions = promotions;
	}

	@Override
	public String toString() {
		return "Beacon [id=" + id + ", name=" + name + ", descripcion=" + description + ", minor=" + minor + ", major="
				+ major + ", localizacion=" + localizacion + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", uid=" + uid + ", business=" + business + ", active=" + active + ", promotions="
				+ promotions + "]";
	}

}
