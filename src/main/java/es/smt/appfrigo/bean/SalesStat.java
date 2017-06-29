package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.SaleMicroDTO;


public class SalesStat implements Serializable{

	private static final long serialVersionUID = 1L;
	private Item item;
	private double total;
	private int dailyAmount;
	private double dailyTotal;
	private int totalGifs;
	private int workDay;
	private double totalHours;
	private int dailyHours;
	private int traffic;
	private double pickUpRate;
	private BusinessMiniDTO business;
	private List<SaleMicroDTO> sales;
	public int type;
	private int totalAmount;
	
	public SalesStat() {
		super();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BusinessMiniDTO getBusiness() {
		return business;
	}

	public void setBusiness(BusinessMiniDTO business) {
		this.business = business;
	}

	public int getWorkDay() {
		return workDay;
	}

	public void setWorkDay(int workDay) {
		this.workDay = workDay;
	}

	public int getDailyAmount() {
		return dailyAmount;
	}

	public void setDailyAmount(int dailyAmount) {
		this.dailyAmount = dailyAmount;
	}

	public int getTotalGifs() {
		return totalGifs;
	}

	public void setTotalGifs(int totalGifs) {
		this.totalGifs = totalGifs;
	}

	public double getDailyTotal() {
		return dailyTotal;
	}

	public void setDailyTotal(double dailyTotal) {
		this.dailyTotal = dailyTotal;
	}

	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public int getDailyHours() {
		return dailyHours;
	}

	public void setDailyHours(int dailyHours) {
		this.dailyHours = dailyHours;
	}

	public int getTraffic() {
		return traffic;
	}

	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}

	public double getPickUpRate() {
		return pickUpRate;
	}

	public void setPickUpRate(double pickUpRate) {
		this.pickUpRate = pickUpRate;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public List<SaleMicroDTO> getSales() {
		return sales;
	}

	public void setSales(List<SaleMicroDTO> sales) {
		this.sales = sales;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Sales [item=" + item + ", total=" + total + ", dailyAmount=" + dailyAmount
				+ ", dailyTotal=" + dailyTotal + ", totalGifs=" + totalGifs + ", workDay=" + workDay + ", totalHours="
				+ totalHours + ", dailyHours=" + dailyHours + ", traffic=" + traffic + ", pickUpRate=" + pickUpRate
				+ ", business=" + business + ", sales=" + sales + "]";
	}

}
