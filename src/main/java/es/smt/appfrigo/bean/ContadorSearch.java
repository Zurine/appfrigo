package es.smt.appfrigo.bean;

import java.io.Serializable;

public class ContadorSearch implements Serializable{

	private static final long serialVersionUID = 1L;
	private String startDate;
	private String endDate;
	
	public ContadorSearch() {
		super();
	}

	public ContadorSearch(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
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

	@Override
	public String toString() {
		return "ContadorSearch [startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
