package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Distributor extends Result implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("distributorId")
	private int id;
    private Region region;
    @NotNull  (message="Concession must be between 1 and 500")
    @Min(value=1, message="Concession must be between 1 and 500") 
    @Max(value=500, message="Concession must be between 1 and 500") 
    private int numConcession;
    @NotEmpty(message="Concession is required")
    private String concession;
    private boolean active;
    @JsonIgnore
    private int row;
    
    private List<Contact> contacts;
    
	public Distributor() {
		super();
	}
	public Distributor(int id, Region region, int numConcession, String concession, boolean active) {
		super();
		this.id = id;
		this.region = region;
		this.numConcession = numConcession;
		this.concession = concession;
		this.active = active;
	}
	
	public Distributor(int id, String concession) {
		super();
		this.id = id;
		this.concession = concession;
	}
	
	public Distributor( String concession) {
		super();
		this.concession = concession;
	}
	
	public Distributor(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public int getNumConcession() {
		return numConcession;
	}
	public void setNumConcession(int numConcession) {
		this.numConcession = numConcession;
	}
	public String getConcession() {
		return concession;
	}
	public void setConcession(String concession) {
		this.concession = concession;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	@Override
	public String toString() {
		return "DistributorDTO [id=" + id + ", region=" + region + ", numConcession="
				+ numConcession + ", concession=" + concession + ", active=" + active + "]";
	}
    
    
}
