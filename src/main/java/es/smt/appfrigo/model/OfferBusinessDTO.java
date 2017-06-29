package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferBusinessDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int offerId;
	private String name;
	private String description;
    private int type;
    private String url;
    private boolean redimible;
    private String code;
    private int max;
    private int maxPerUserDay;
    private int maxPerUser;
    private long startDate;
    private long endDate;
    private List<HorarioDTO> time;
    private List<String> conditions;
    
	public OfferBusinessDTO() {
		super();
	}
	
	public OfferBusinessDTO(int offerId, String name, String description, int type, String url, boolean redimible,
			String code, int max, int maxPerUserDay, int maxPerUser, long startDate, long endDate,
			List<HorarioDTO> time, List<String> conditions) {
		super();
		this.offerId = offerId;
		this.name = name;
		this.description = description;
		this.type = type;
		this.url = url;
		this.redimible = redimible;
		this.code = code;
		this.max = max;
		this.maxPerUserDay = maxPerUserDay;
		this.maxPerUser = maxPerUser;
		this.startDate = startDate;
		this.endDate = endDate;
		this.time = time;
		this.conditions = conditions;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isRedimible() {
		return redimible;
	}
	public void setRedimible(boolean redimible) {
		this.redimible = redimible;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMaxPerUserDay() {
		return maxPerUserDay;
	}
	public void setMaxPerUserDay(int maxPerUserDay) {
		this.maxPerUserDay = maxPerUserDay;
	}
	public int getMaxPerUser() {
		return maxPerUser;
	}
	public void setMaxPerUser(int maxPerUser) {
		this.maxPerUser = maxPerUser;
	}
	public long getStartDate() {
		return startDate;
	}
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public List<HorarioDTO> getTime() {
		return time;
	}
	public void setTime(List<HorarioDTO> time) {
		this.time = time;
	}
	public List<String> getConditions() {
		return conditions;
	}
	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}
	@Override
	public String toString() {
		return "OfferBusinessDTO [offerId=" + offerId + ", name=" + name + ", description=" + description + ", type="
				+ type + ", url=" + url + ", redimible=" + redimible + ", code=" + code + ", max=" + max
				+ ", maxPerUserDay=" + maxPerUserDay + ", maxPerUser=" + maxPerUser + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", time=" + time + ", conditions=" + conditions + "]";
	}
    
    
}
