package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class BeaconDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int beaconId;
	private int enterpriseId;
    private int minor;
    private int major;
    private String name;
    @JsonProperty("Descripcion")
    private String description;
    private String localizacion;
    private String latitud;
    private String longitud;
    private String uid;
    //private BusinessDTO businessDTO;
    private BusinessMiniDTO business;
    private boolean active;
    @JsonProperty("ObjOffers")
    private List<OfferDetailsDTO> objOffers;
    
	public BeaconDTO() {
		super();
	}

	public BeaconDTO(int beaconId, int enterpriseId, int minor, int major, String name, String description,
			String localizacion, String latitud, String longitud, String uid, BusinessMiniDTO business, boolean active,
			List<OfferDetailsDTO> objOffers) {
		super();
		this.beaconId = beaconId;
		this.enterpriseId = enterpriseId;
		this.minor = minor;
		this.major = major;
		this.name = name;
		this.description = description;
		this.localizacion = localizacion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.uid = uid;
		this.business = business;
		this.active = active;
		this.objOffers = objOffers;
	}

	public int getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(int beaconId) {
		this.beaconId = beaconId;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public List<OfferDetailsDTO> getObjOffers() {
		return objOffers;
	}

	public void setObjOffers(List<OfferDetailsDTO> objOffers) {
		this.objOffers = objOffers;
	}

	@Override
	public String toString() {
		return "BeaconDTO [beaconId=" + beaconId + ", enterpriseId=" + enterpriseId + ", minor=" + minor + ", major="
				+ major + ", name=" + name + ", description=" + description + ", localizacion=" + localizacion
				+ ", latitud=" + latitud + ", longitud=" + longitud + ", uid=" + uid + ", business=" + business
				+ ", active=" + active + ", objOffers=" + objOffers + "]";
	}


    

	
}
