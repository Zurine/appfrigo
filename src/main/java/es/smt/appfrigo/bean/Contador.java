package es.smt.appfrigo.bean;

import java.io.Serializable;

public class Contador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
    private String description;
    private int number;
    private String date;
    private int presentTime;
    private int distance;
    private int people;
    private int sold;
    private int detected;
    
	public Contador() {
		super();
	}
	
	public Contador(int id, String description, int number, String date, int presentTime, int distance, int people,
			int sold, int detected) {
		super();
		this.id = id;
		this.description = description;
		this.number = number;
		this.date = date;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
		return "Contador [id=" + id + ", description=" + description + ", number=" + number + ", date="
				+ date + ", presentTime=" + presentTime + ", distance=" + distance + ", people=" + people
				+ ", sold=" + sold + ", detected=" + detected + "]";
	}
    
    
}
