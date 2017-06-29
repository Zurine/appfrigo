package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.smt.appfrigo.validation.CustomJsonDateDeserializer;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketDetail implements Serializable{


	private static final long serialVersionUID = 1L;
	private String ticket;
	private String ticketTemp;
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String date;
    private int saleId;
    private boolean refunded;
    private boolean count;
    
    private double total;
    private int amount;
    private double taxBase;
    private String name;
    private String id;
    
	public TicketDetail() {
		super();
	}
	
	public TicketDetail(String ticket, String date, int saleId, boolean refunded, boolean count) {
		super();
		this.ticket = ticket;
		this.date = date;
		this.saleId = saleId;
		this.refunded = refunded;
		this.count = count;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public boolean isRefunded() {
		return refunded;
	}
	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}
	public boolean isCount() {
		return count;
	}
	public void setCount(boolean count) {
		this.count = count;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTicketTemp() {
		return ticketTemp;
	}

	public void setTicketTemp(String ticketTemp) {
		this.ticketTemp = ticketTemp;
	}
	
	

	public double getTaxBase() {
		return taxBase;
	}

	public void setTaxBase(double taxBase) {
		this.taxBase = taxBase;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TicketDetail [ticket=" + ticket + ", ticketTemp=" + ticketTemp + ", date=" + date + ", saleId=" + saleId
				+ ", refunded=" + refunded + ", count=" + count + ", total=" + total + ", amount=" + amount + "]";
	}

    
    
}
