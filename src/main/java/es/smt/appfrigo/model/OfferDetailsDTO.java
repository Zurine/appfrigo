package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int major;
    private int minor;
    private int offerId;
    private int beaconId;
    private String beaconName;
    private String name;
    private String thumbnail;
    private int offerType;
    private String offerURL;
    private String offerDescription;
    private String longitude;
    private String latitude;
    private int max;
    private boolean isRedimible;
    private int businessId;
    private String businessName;
    private String direccion;
    private long fechaIni;
    private long fechaFin;
    private String code;
    private boolean active;
    private int maxPerUserDay;
    private int maxPerUser;
    private List<HorarioDTO> franjaHoraria;
	/**
	 * 
	 */
	public OfferDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param major
	 * @param minor
	 * @param offerId
	 * @param beaconId
	 * @param beaconName
	 * @param name
	 * @param thumbnail
	 * @param offerType
	 * @param offerURL
	 * @param offerDescription
	 * @param longitude
	 * @param latitude
	 * @param max
	 * @param isRedimible
	 * @param businessId
	 * @param businessName
	 * @param direccion
	 * @param fechaIni
	 * @param fechaFin
	 * @param code
	 * @param active
	 * @param maxPerUserDay
	 * @param maxPerUser
	 * @param franjaHoraria
	 */
	public OfferDetailsDTO(int major, int minor, int offerId, int beaconId, String beaconName, String name,
			String thumbnail, int offerType, String offerURL, String offerDescription, String longitude,
			String latitude, int max, boolean isRedimible, int businessId, String businessName, String direccion,
			long fechaIni, long fechaFin, String code, boolean active, int maxPerUserDay, int maxPerUser,
			List<HorarioDTO> franjaHoraria) {
		super();
		this.major = major;
		this.minor = minor;
		this.offerId = offerId;
		this.beaconId = beaconId;
		this.beaconName = beaconName;
		this.name = name;
		this.thumbnail = thumbnail;
		this.offerType = offerType;
		this.offerURL = offerURL;
		this.offerDescription = offerDescription;
		this.longitude = longitude;
		this.latitude = latitude;
		this.max = max;
		this.isRedimible = isRedimible;
		this.businessId = businessId;
		this.businessName = businessName;
		this.direccion = direccion;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.code = code;
		this.active = active;
		this.maxPerUserDay = maxPerUserDay;
		this.maxPerUser = maxPerUser;
		this.franjaHoraria = franjaHoraria;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getBeaconId() {
		return beaconId;
	}
	public void setBeaconId(int beaconId) {
		this.beaconId = beaconId;
	}
	public String getBeaconName() {
		return beaconName;
	}
	public void setBeaconName(String beaconName) {
		this.beaconName = beaconName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getOfferType() {
		return offerType;
	}
	public void setOfferType(int offerType) {
		this.offerType = offerType;
	}
	public String getOfferURL() {
		return offerURL;
	}
	public void setOfferURL(String offerURL) {
		this.offerURL = offerURL;
	}
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public boolean isRedimible() {
		return isRedimible;
	}
	public void setRedimible(boolean isRedimible) {
		this.isRedimible = isRedimible;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public long getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(long fechaIni) {
		this.fechaIni = fechaIni;
	}
	public long getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(long fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getMaxPerUserDay() {
		return maxPerUserDay;
	}
	public void setMaxPerUserDay(int maxPerUserDay) {
		this.maxPerUserDay = maxPerUserDay;
	}
	public int getMaxPerUser() {
		return maxPerUser;
	}
	public void setMaxPerUser(int maxPerUser) {
		this.maxPerUser = maxPerUser;
	}
	public List<HorarioDTO> getFranjaHoraria() {
		return franjaHoraria;
	}
	public void setFranjaHoraria(List<HorarioDTO> franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}
	@Override
	public String toString() {
		return "OfferDetailsDTO [major=" + major + ", minor=" + minor + ", offerId=" + offerId + ", beaconId="
				+ beaconId + ", beaconName=" + beaconName + ", name=" + name + ", thumbnail=" + thumbnail
				+ ", offerType=" + offerType + ", offerURL=" + offerURL + ", offerDescription=" + offerDescription
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", max=" + max + ", isRedimible="
				+ isRedimible + ", businessId=" + businessId + ", businessName=" + businessName + ", direccion="
				+ direccion + ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin + ", code=" + code + ", active="
				+ active + ", maxPerUserDay=" + maxPerUserDay + ", maxPerUser=" + maxPerUser + ", franjaHoraria="
				+ franjaHoraria + "]";
	}
    
    
    
}
