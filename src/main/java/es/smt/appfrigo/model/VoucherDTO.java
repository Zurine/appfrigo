package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoucherDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String description;
	private String image;
    private long startDate;
    private long endDate;
    private RetailDTO retail;
    private String code;
    private boolean active;
    private String description2;
    private String description3;
    private String shortName;
    
	public VoucherDTO() {
		super();
	}

	public VoucherDTO(int id, String name, String description, String image, long startDate, long endDate,
			RetailDTO retail, String code, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.startDate = startDate;
		this.endDate = endDate;
		this.retail = retail;
		this.code = code;
		this.active = active;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public RetailDTO getRetail() {
		return retail;
	}

	public void setRetail(RetailDTO retail) {
		this.retail = retail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getDescription3() {
		return description3;
	}

	public void setDescription3(String description3) {
		this.description3 = description3;
	}
	
	

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		return "VoucherDTO [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", retail=" + retail + ", code=" + code + "]";
	}
      
      
}
