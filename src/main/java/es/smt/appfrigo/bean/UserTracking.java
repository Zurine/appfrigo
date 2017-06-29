package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.smt.appfrigo.validation.CustomJsonDateDeserializer;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTracking implements Serializable{

	private static final long serialVersionUID = 1L;
	private UserMicro user;
    private BusinessNano business;
    private int boxState;
    private double totalUser;
    private double totalSystem;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String date;
    private List<TrackingDay> tracking;
    
	public UserTracking() {
		super();
	}

	public UserTracking(UserMicro user, BusinessNano business, int boxState, double totalUser, double totalSystem,
			List<TrackingDay> tracking) {
		super();
		this.user = user;
		this.business = business;
		this.boxState = boxState;
		this.totalUser = totalUser;
		this.totalSystem = totalSystem;
		this.tracking = tracking;
	}

	public UserMicro getUser() {
		return user;
	}

	public void setUser(UserMicro user) {
		this.user = user;
	}

	public BusinessNano getBusiness() {
		return business;
	}

	public void setBusiness(BusinessNano business) {
		this.business = business;
	}

	public int getBoxState() {
		return boxState;
	}

	public void setBoxState(int boxState) {
		this.boxState = boxState;
	}

	public double getTotalUser() {
		return totalUser;
	}

	public void setTotalUser(double totalUser) {
		this.totalUser = totalUser;
	}

	public double getTotalSystem() {
		return totalSystem;
	}

	public void setTotalSystem(double totalSystem) {
		this.totalSystem = totalSystem;
	}

	public List<TrackingDay> getTracking() {
		return tracking;
	}

	public void setTracking(List<TrackingDay> tracking) {
		this.tracking = tracking;
	}
	
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UserTracking [user=" + user + ", business=" + business + ", boxState=" + boxState + ", totalUser="
				+ totalUser + ", totalSystem=" + totalSystem + ", tracking=" + tracking + "]";
	}


    
}
