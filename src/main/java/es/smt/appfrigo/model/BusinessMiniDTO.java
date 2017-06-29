package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessMiniDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 @JsonProperty("businessId")
	 private int id;
	 @JsonProperty("businessName")
     private String name;
     private String direccion;
     private String longitude;
     private String latitude;
     public String unileverId;
     public String channel;
     public boolean active;
     public boolean inactive;
	
     public BusinessMiniDTO() {
		super();
	}

     
 	
     public BusinessMiniDTO(int id) {
		super();
		this.id = id;
	}


     
	public BusinessMiniDTO(int id, String name, String direccion, String longitude, String latitude,
			String unileverId, String channel, boolean active, boolean inactive) {
		super();
		this.id = id;
		this.name = name;
		this.direccion = direccion;
		this.longitude = longitude;
		this.latitude = latitude;
		this.unileverId = unileverId;
		this.channel = channel;
		this.active = active;
		this.inactive = inactive;
	}

	
	public BusinessMiniDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}



	@Override
	public String toString() {
		return "BusinessMiniDTO [id=" + id + ", name=" + name + ", direccion="
				+ direccion + ", longitude=" + longitude + ", latitude=" + latitude + ", unileverId=" + unileverId
				+ ", channel=" + channel + ", active=" + active + ", inactive=" + inactive + "]";
	}


	public boolean isInactive() {
		return inactive;
	}


	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
}
