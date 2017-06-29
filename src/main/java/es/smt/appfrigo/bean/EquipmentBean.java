package es.smt.appfrigo.bean;

import java.io.Serializable;


public class EquipmentBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int equipmentId;
	private String description;
	private Channel channelBean;
	private boolean active;
	
	public EquipmentBean() {
		super();
	}

	public EquipmentBean(int equipmentId, String description, Channel channel, boolean active) {
		super();
		this.equipmentId = equipmentId;
		this.description = description;
		this.channelBean = channel;
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



	public Channel getChannelBean() {
		return channelBean;
	}

	public void setChannelBean(Channel channelBean) {
		this.channelBean = channelBean;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "EquipmentDTO [equipmentId=" + equipmentId + ", description=" + description + ", channel=" + channelBean
				+ ", active=" + active + "]";
	}
	
	

}
