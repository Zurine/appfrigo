package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistributorContact implements Serializable{

	private static final long serialVersionUID = 1L;
	private String distributor;
	private List<Contact> contacts;
	
	public DistributorContact() {
		super();
	}

	public DistributorContact(String distributor, List<Contact> contacts) {
		super();
		this.distributor = distributor;
		this.contacts = contacts;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "DistributorContact [distributor=" + distributor + ", contacts=" + contacts + "]";
	}
	
	
}
