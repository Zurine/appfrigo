package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperatorF implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private List<BusinessF> business;
	public OperatorF() {
		super();
	}
	public OperatorF(int id, String name, List<BusinessF> business) {
		super();
		this.id = id;
		this.name = name;
		this.business = business;
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
	public List<BusinessF> getBusiness() {
		return business;
	}
	public void setBusiness(List<BusinessF> business) {
		this.business = business;
	}
	
	
}
