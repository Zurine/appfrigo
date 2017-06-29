package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;



public class StatisticSearch implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Integer> equipment;
	private String startDate;
	private String endDate;
	private List<Integer> channel;
	private List<Integer> region;
	private List<Integer> equipmentType;
	private List<Integer> product;
	private List<Integer> operator;
	private List<Integer> sellers;
	private List<String> data;
	private int gpType;
	private int id;
	private boolean workDay;
	
	public StatisticSearch() {
		super();
	}

	public StatisticSearch(List<Integer> equipment, String startDate, String endDate,List<Integer> channel, List<Integer> region,
			List<Integer> equipmentType) {
		super();
		this.equipment = equipment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.channel = channel;
		this.region = region;
		this.equipmentType = equipmentType;
	}

	public List<Integer> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Integer> equipment) {
		this.equipment = equipment;
	}
	
	public List<Integer> getSellers() {
		return sellers;
	}

	public void setSellers(List<Integer> sellers) {
		this.sellers = sellers;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Integer>  getChannel() {
		return channel;
	}

	public void setChannel(List<Integer>  channel) {
		this.channel = channel;
	}

	public List<Integer> getRegion() {
		return region;
	}

	public void setRegion(List<Integer> region) {
		this.region = region;
	}

	public List<Integer> getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(List<Integer> equipmentType) {
		this.equipmentType = equipmentType;
	}

	public List<Integer> getProduct() {
		return product;
	}

	public void setProduct(List<Integer> product) {
		this.product = product;
	}

	public List<Integer> getOperator() {
		return operator;
	}

	public void setOperator(List<Integer> operator) {
		this.operator = operator;
	}

	public int getGpType() {
		return gpType;
	}

	public void setGpType(int gpType) {
		this.gpType = gpType;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public boolean isWorkDay() {
		return workDay;
	}

	public void setWorkDay(boolean workDay) {
		this.workDay = workDay;
	}

	@Override
	public String toString() {
		return "StatisticSearch [equipment=" + equipment + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", channel=" + channel + ", region=" + region + ", equipmentType=" + equipmentType + "]";
	}

}
