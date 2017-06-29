package es.smt.appfrigo.bean;

import java.util.List;

import es.smt.appfrigo.model.DataDTO;

public class Dashboard {
    
    private List<DataDTO> equipmentNumber;
    private int totalEquipmentNumber;
    private List<DataDTO> sellerNumber;
    private int totalSellerNumber;
    
    private List<DataDTO> salesNumber;
    private int totalSalesNumber;
    
    private List<DataDTO> unitNumber;
    private int totalUnitNumber;
   
	public Dashboard() {
		super();
	}



	public List<DataDTO> getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(List<DataDTO> equipmentNumber) {
		this.equipmentNumber = equipmentNumber;
	}

	public int getTotalEquipmentNumber() {
		return totalEquipmentNumber;
	}


	public void setTotalEquipmentNumber(int totalEquipmentNumber) {
		this.totalEquipmentNumber = totalEquipmentNumber;
	}
	
	public List<DataDTO> getSellerNumber() {
		return sellerNumber;
	}

	public void setSellerNumber(List<DataDTO> sellerNumber) {
		this.sellerNumber = sellerNumber;
	}

	public int getTotalSellerNumber() {
		return totalSellerNumber;
	}

	public void setTotalSellerNumber(int totalSellerNumber) {
		this.totalSellerNumber = totalSellerNumber;
	}

	public List<DataDTO> getSalesNumber() {
		return salesNumber;
	}

	public void setSalesNumber(List<DataDTO> salesNumber) {
		this.salesNumber = salesNumber;
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

	public int getTotalSalesNumber() {
		return totalSalesNumber;
	}

	public void setTotalSalesNumber(int totalSalesNumber) {
		this.totalSalesNumber = totalSalesNumber;
	}




}
