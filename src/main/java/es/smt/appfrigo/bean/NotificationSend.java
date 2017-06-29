package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

public class NotificationSend implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Notification notification;
	private List<Item> listAge;
	private List<Item> listGender;
	private List<Item> listTemperature;
	private List<Integer> users;
	private List<Integer> selectedConditions;
	private List<Integer> selectedAge;
	private List<Integer> selectedGender;
	private String postalCode;
	
	public NotificationSend() {
		super();
	}

	public NotificationSend(Notification notification, List<Item> listAge, List<Item> listGender,
			List<Item> listTemperature, List<Integer> users, List<Integer> selectedConditions) {
		super();
		this.notification = notification;
		this.listAge = listAge;
		this.listGender = listGender;
		this.listTemperature = listTemperature;
		this.users = users;
		this.selectedConditions = selectedConditions;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public List<Item> getListAge() {
		return listAge;
	}

	public void setListAge(List<Item> listAge) {
		this.listAge = listAge;
	}

	public List<Item> getListGender() {
		return listGender;
	}

	public void setListGender(List<Item> listGender) {
		this.listGender = listGender;
	}

	public List<Item> getListTemperature() {
		return listTemperature;
	}

	public void setListTemperature(List<Item> listTemperature) {
		this.listTemperature = listTemperature;
	}

	public List<Integer> getUsers() {
		return users;
	}

	public void setUsers(List<Integer> users) {
		this.users = users;
	}

	public List<Integer> getSelectedConditions() {
		return selectedConditions;
	}

	public void setSelectedConditions(List<Integer> selectedConditions) {
		this.selectedConditions = selectedConditions;
	}
	
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public List<Integer> getSelectedAge() {
		return selectedAge;
	}

	public void setSelectedAge(List<Integer> selectedAge) {
		this.selectedAge = selectedAge;
	}

	public List<Integer> getSelectedGender() {
		return selectedGender;
	}

	public void setSelectedGender(List<Integer> selectedGender) {
		this.selectedGender = selectedGender;
	}

	@Override
	public String toString() {
		return "NotificationSend [notification=" + notification + ", listAge=" + listAge + ", listGender=" + listGender
				+ ", listTemperature=" + listTemperature + ", users=" + users + ", selectedConditions="
				+ selectedConditions + "]";
	}

}
