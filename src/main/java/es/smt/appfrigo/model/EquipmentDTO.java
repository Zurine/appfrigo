package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.bean.Channel;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int equipmentId;
	private String description;
	private Channel channel;
	private boolean active ;
	
	public EquipmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EquipmentDTO(int equipmentId, String description, Channel channel, boolean active) {
		super();
		this.equipmentId = equipmentId;
		this.description = description;
		this.channel = channel;
		this.active = active;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "EquipmentDTO [equipmentId=" + equipmentId + ", description=" + description + ", channel=" + channel
				+ ", active=" + active + "]";
	}
	
	

}
