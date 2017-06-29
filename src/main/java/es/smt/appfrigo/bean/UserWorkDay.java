package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWorkDay extends Response implements Serializable{

	private static final long serialVersionUID = 1L;
	private UserMicro user;
    private BusinessNano business;
    private List<WorkDay> workDays;
    
	public UserWorkDay() {
		super();
	}
	public UserWorkDay(UserMicro user, BusinessNano business, List<WorkDay> workDays) {
		super();
		this.user = user;
		this.business = business;
		this.workDays = workDays;
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
	public List<WorkDay> getWorkDays() {
		return workDays;
	}
	public void setWorkDays(List<WorkDay> workDays) {
		this.workDays = workDays;
	}

	@Override
	public String toString() {
		return "UserWorkDay [user=" + user + ", business=" + business + ", workDays=" + workDays + "]";
	}
    
    
}
