package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int totalBusiness;
	private int totalSeller;
	private int totalSales;
//    private int pickupAvg;
    private List<DataDTO> equipmentType;
    private List<DataDTO> distributor;
    private List<DataDTO> equipmentList;
    private List<DataDTO> equipmentNumber;
    private List<DataDTO> sellerNumber;
    private List<DataDTO> salesNumber;  
    private List<DataDTO> unitNumber;  
    private int totalEquipmentNumber;
    private int totalSellerNumber;
    private int totalSalesNumber;
    private int totalUnitNumber;
    
    private int days;
    private int units;
    private int salesDay;
    private int salesEquiment;
    
	public HomeDTO() {
		super();
	}

	

	public HomeDTO(int totalBusiness, int totalSeller, int totalSales, List<DataDTO> equipmentType,
			List<DataDTO> distributor, List<DataDTO> equipmentList, List<DataDTO> equipmentNumber,
			List<DataDTO> sellerNumber, List<DataDTO> salesNumber, List<DataDTO> unitNumber, int totalEquipmentNumber,
			int totalSellerNumber, int totalSalesNumber, int totalUnitNumber, int days, int units, int salesDay,
			int salesEquiment) {
		super();
		this.totalBusiness = totalBusiness;
		this.totalSeller = totalSeller;
		this.totalSales = totalSales;
		this.equipmentType = equipmentType;
		this.distributor = distributor;
		this.equipmentList = equipmentList;
		this.equipmentNumber = equipmentNumber;
		this.sellerNumber = sellerNumber;
		this.salesNumber = salesNumber;
		this.unitNumber = unitNumber;
		this.totalEquipmentNumber = totalEquipmentNumber;
		this.totalSellerNumber = totalSellerNumber;
		this.totalSalesNumber = totalSalesNumber;
		this.totalUnitNumber = totalUnitNumber;
		this.days = days;
		this.units = units;
		this.salesDay = salesDay;
		this.salesEquiment = salesEquiment;
	}



	public int getTotalBusiness() {
		return totalBusiness;
	}

	public void setTotalBusiness(int totalBusiness) {
		this.totalBusiness = totalBusiness;
	}

	public int getTotalSeller() {
		return totalSeller;
	}

	public void setTotalSeller(int totalSeller) {
		this.totalSeller = totalSeller;
	}

	public List<DataDTO> getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(List<DataDTO> equipmentType) {
		this.equipmentType = equipmentType;
	}

	public List<DataDTO> getDistributor() {
		return distributor;
	}

	public void setDistributor(List<DataDTO> distributor) {
		this.distributor = distributor;
	}

	public List<DataDTO> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<DataDTO> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public List<DataDTO> getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(List<DataDTO> equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}
	
	public List<DataDTO> getSellerNumber() {
		return sellerNumber;
	}

	public void setSellerNumber(List<DataDTO> sellerNumber) {
		this.sellerNumber = sellerNumber;
	}
	
	public List<DataDTO> getSalesNumber() {
		return salesNumber;
	}

	public void setSalesNumber(List<DataDTO> salesNumber) {
		this.salesNumber = salesNumber;
	}

	public int getTotalEquipmentNumber() {
		return totalEquipmentNumber;
	}

	public void setTotalEquipmentNumber(int totalEquipmentNumber) {
		this.totalEquipmentNumber = totalEquipmentNumber;
	}
	
	public int getTotalSellerNumber() {
		return totalSellerNumber;
	}

	public void setTotalSellerNumber(int totalSellerNumber) {
		this.totalSellerNumber = totalSellerNumber;
	}
	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public List<DataDTO> getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(List<DataDTO> unitNumber) {
		this.unitNumber = unitNumber;
	}

	public int getTotalUnitNumber() {
		return totalUnitNumber;
	}

	public void setTotalUnitNumber(int totalUnitNumber) {
		this.totalUnitNumber = totalUnitNumber;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	
	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public int getTotalSalesNumber() {
		return totalSalesNumber;
	}

	public void setTotalSalesNumber(int totalSalesNumber) {
		this.totalSalesNumber = totalSalesNumber;
	}

	public int getSalesDay() {
		return salesDay;
	}

	public void setSalesDay(int salesDay) {
		this.salesDay = salesDay;
	}

	public int getSalesEquiment() {
		return salesEquiment;
	}

	public void setSalesEquiment(int salesEquiment) {
		this.salesEquiment = salesEquiment;
	}

	@Override
	public String toString() {
		return "HomeDTO [totalBusiness=" + totalBusiness + ", totalSeller=" + totalSeller + ", totalSales=" + totalSales
				+ ", equipmentType=" + equipmentType + "]";
	}
}
