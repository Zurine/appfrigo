package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessDetailDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	 private String name;
	 private String direccion;
	 private String unileverId;
	 private String channel;
	 private String region;
	 private String distributor;
	 private String equipmentType;
	 private String typeIcon;
	 private boolean active;
	 private boolean inactive;
	 private int totalSeller;
	 
	public BusinessDetailDTO() {
		super();
	}

	public BusinessDetailDTO(int id, String name, String direccion, String unileverId, String channel, String region,
			String distributor, boolean active, boolean inactive, String equipmentType, int totalSeller) {
		super();
		this.id = id;
		this.name = name;
		this.direccion = direccion;
		this.unileverId = unileverId;
		this.channel = channel;
		this.region = region;
		this.distributor = distributor;
		this.active = active;
		this.inactive = inactive;
		this.equipmentType = equipmentType;
		this.totalSeller = totalSeller;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUnileverId() {
		return unileverId;
	}

	public void setUnileverId(String unileverId) {
		this.unileverId = unileverId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	
	public String getTypeIcon() {
		return typeIcon;
	}

	public void setTypeIcon(String typeIcon) {
		this.typeIcon = typeIcon;
	}

	public int getTotalSeller() {
		return totalSeller;
	}

	public void setTotalSeller(int totalSeller) {
		this.totalSeller = totalSeller;
	}

	@Override
	public String toString() {
		return "BusinessDetailDTO [id=" + id + ", name=" + name + ", direccion=" + direccion + ", unileverId="
				+ unileverId + ", channel=" + channel + ", region=" + region + ", distributor=" + distributor
				+ ", equipmentType=" + equipmentType + ", active=" + active + ", inactive=" + inactive + "]";
	}

	
	 
}
