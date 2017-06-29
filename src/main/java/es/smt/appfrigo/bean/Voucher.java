package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;


public class Voucher implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private String description2;
	private String description3;
	private Image image;
    private String startDate;
    private String endDate;
    private Retail retail;
    private String code;
    private boolean active;
    private List<Integer> retails;
    private String shortName;
    
	public Voucher() {
		super();
	}

	public Voucher(int id, String name, String description, Image image, String startDate,String endDate, Retail retail,
			String code,boolean active,String shortName) {
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
		this.shortName = shortName;
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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

	public Retail getRetail() {
		return retail;
	}

	public void setRetail(Retail retail) {
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

	public List<Integer> getRetails() {
		return retails;
	}

	public void setRetails(List<Integer> retails) {
		this.retails = retails;
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
		return "Voucher [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", retail=" + retail + ", code=" + code + "]";
	}
    
    
}
