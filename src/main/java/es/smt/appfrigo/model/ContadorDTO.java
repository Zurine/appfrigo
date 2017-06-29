package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import es.smt.appfrigo.rest.JsonDateSerializer;
import es.smt.appfrigo.validation.CustomJsonDateDeserializer;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContadorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private int contadorId;
    private String description;
    private int number;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    @JsonSerialize(using=JsonDateSerializer.class)
    public String deviceDate;
    private String serverDate;
    private boolean present;
    private int presentTime;
    private int distance;
    private int people;
    private int sold;
    private int detected;
    
	public ContadorDTO() {
		super();
	}
	
	public ContadorDTO(int id, int contadorId, String description, int number, String deviceDate, String serverDate,
			boolean present, int presentTime, int distance, int people, int sold, int detected) {
		super();
		this.id = id;
		this.contadorId = contadorId;
		this.description = description;
		this.number = number;
		this.deviceDate = deviceDate;
		this.serverDate = serverDate;
		this.present = present;
		this.presentTime = presentTime;
		this.distance = distance;
		this.people = people;
		this.sold = sold;
		this.detected = detected;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContadorId() {
		return contadorId;
	}
	public void setContadorId(int contadorId) {
		this.contadorId = contadorId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDeviceDate() {
		return deviceDate;
	}
	public void setDeviceDate(String deviceDate) {
		this.deviceDate = deviceDate;
	}
	public String getServerDate() {
		return serverDate;
	}
	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	public int getPresentTime() {
		return presentTime;
	}
	public void setPresentTime(int presentTime) {
		this.presentTime = presentTime;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public int getDetected() {
		return detected;
	}
	public void setDetected(int detected) {
		this.detected = detected;
	}
	@Override
	public String toString() {
		return "ContadorDTO [id=" + id + ", contadorId=" + contadorId + ", description=" + description + ", number="
				+ number + ", deviceDate=" + deviceDate + ", serverDate=" + serverDate + ", present=" + present
				+ ", presentTime=" + presentTime + ", distance=" + distance + ", people=" + people + ", sold=" + sold
				+ ", detected=" + detected + "]";
	}
    
    
}
