package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferBeaconDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private int offerId;
    private int beaconId;
    private boolean isRedimible;
    private String code;
    private int max;
    private int maxPerUserDay;
    private int maxPerUser;
    private long fechaIni;
    private long fechaFin;
    private boolean active;
    
    

    @JsonProperty("FranjaHoraria")
    private List<HorarioDTO> franjaHoraria;

	public OfferBeaconDTO() {
		super();
	}

	
	public OfferBeaconDTO(int offerId, int beaconId, boolean isRedimible, String code, int max, int maxPerUserDay,
			int maxPerUser, long fechaIni, long fechaFin, boolean active, List<HorarioDTO> franjaHoraria) {
		super();
		this.offerId = offerId;
		this.beaconId = beaconId;
		this.isRedimible = isRedimible;
		this.code = code;
		this.max = max;
		this.maxPerUserDay = maxPerUserDay;
		this.maxPerUser = maxPerUser;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.active = active;
		this.franjaHoraria = franjaHoraria;
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


	public boolean isRedimible() {
		return isRedimible;
	}


	public void setRedimible(boolean isRedimible) {
		this.isRedimible = isRedimible;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
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


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public List<HorarioDTO> getFranjaHoraria() {
		return franjaHoraria;
	}


	public void setFranjaHoraria(List<HorarioDTO> franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}




	@Override
	public String toString() {
		return "OfferBeaconDTO [offerId=" + offerId + ", beaconId=" + beaconId + ", isRedimible=" + isRedimible
				+ ", code=" + code + ", max=" + max + ", maxPerUserDay=" + maxPerUserDay + ", maxPerUser=" + maxPerUser
				+ ", fechaIni=" + fechaIni + ", fechaFin=" + fechaFin + ", active=" + active + ", franjaHoraria="
				+ franjaHoraria + "]";
	}
    
	
    

}
