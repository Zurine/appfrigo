package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.HorarioDTO;

public class PromotionBeacon implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Promotion promotion;
    private BeaconMini beacon;
    private int max;
    private int maxPerUserDay;
    private int maxPerUser;
    private BusinessMiniDTO business;
    private String fechaIni;
    private String fechaFin;
    private String code;
    private boolean active;
    private List<HorarioDTO> franjaHoraria;
    private String timeZone;
    
    
	public PromotionBeacon() {
		super();
	}

	public PromotionBeacon(Promotion promotion, BeaconMini beacon, int max, int maxPerUserDay, int maxPerUser,
			BusinessMiniDTO business, String fechaIni, String fechaFin, String code, boolean active,
			List<HorarioDTO> franjaHoraria) {
		super();
		this.promotion = promotion;
		this.beacon = beacon;
		this.max = max;
		this.maxPerUserDay = maxPerUserDay;
		this.maxPerUser = maxPerUser;
		this.business = business;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.code = code;
		this.active = active;
		this.franjaHoraria = franjaHoraria;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public BeaconMini getBeacon() {
		return beacon;
	}

	public void setBeacon(BeaconMini beacon) {
		this.beacon = beacon;
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

	public BusinessMiniDTO getBusiness() {
		return business;
	}

	public void setBusiness(BusinessMiniDTO business) {
		this.business = business;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
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

	public List<HorarioDTO> getFranjaHoraria() {
		return franjaHoraria;
	}

	public void setFranjaHoraria(List<HorarioDTO> franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}
	
	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public String toString() {
		return "PromotionBeacon [promotion=" + promotion + ", beacon=" + beacon + ", max=" + max + ", maxPerUserDay="
				+ maxPerUserDay + ", maxPerUser=" + maxPerUser + ", business=" + business + ", fechaIni=" + fechaIni
				+ ", fechaFin=" + fechaFin + ", code=" + code + ", active=" + active + ", franjaHoraria="
				+ franjaHoraria + "]";
	}
    
	
    
    
}
