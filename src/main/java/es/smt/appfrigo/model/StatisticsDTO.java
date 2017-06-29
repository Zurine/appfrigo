package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.smt.appfrigo.validation.CustomJsonDateDeserializer;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticsDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
    private String name;
    private String businessName;
    private int businessId;
    private double total;
    private int totalWorkDay;
    private int totalGifs;
    private int totalAmount;
    private double totalHours;
    private int totalPeople;
    private int totalSales;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String date;
    private List<SaleMicroDTO> products;
     
	public StatisticsDTO() {
		super();
	}

	public StatisticsDTO(int id, String name, String businessName, int businessId, double total, int totalWorkDay) {
		super();
		this.id = id;
		this.name = name;
		this.businessName = businessName;
		this.businessId = businessId;
		this.total = total;
		this.totalWorkDay = totalWorkDay;
	}

	public int getTotalWorkDay() {
		return totalWorkDay;
	}

	public void setTotalWorkDay(int totalWorkDay) {
		this.totalWorkDay = totalWorkDay;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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

	public int getTotalGifs() {
		return totalGifs;
	}

	public void setTotalGifs(int totalGifs) {
		this.totalGifs = totalGifs;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public int getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(int totalPeople) {
		this.totalPeople = totalPeople;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public List<SaleMicroDTO> getProducts() {
		return products;
	}

	public void setProducts(List<SaleMicroDTO> products) {
		this.products = products;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "StatisticsDTO [id=" + id + ", name=" + name + ", businessName=" + businessName + ", businessId="
				+ businessId + ", total=" + total + ", totalWorkDay=" + totalWorkDay + "]";
	}
}