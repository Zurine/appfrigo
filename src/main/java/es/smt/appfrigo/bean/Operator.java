package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.model.BusinessMiniDTO;
import es.smt.appfrigo.model.UserAdminDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class Operator extends Result implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	@Pattern(regexp="^[a-zA-Z]{1}\\d{7}[a-jA-J0-9]{1}$", message="Invalid CIF")//Mirar para i18n
	private String cif;
	@NotEmpty(message="Fiscal name is required")
	@NotNull(message="Fiscal name is required")
	@JsonProperty("nombreFiscal")
	private String name;
	@NotNull(message="Timezone is required")
	@JsonProperty("timezone")
    private String zone;
    private boolean active;
    private boolean stock;
    
    private UserAdminDTO user;
    private List<BusinessMiniDTO> business;
    
	private String url;
	@JsonIgnore
	private Image image;
    
    private StatisticsMini data;
    
	public Operator() {
		super();
	}
	

	public Operator(int id) {
		super();
		this.id = id;
	}

	public Operator(int id, String cif, String name,  String zone) {
		super();
		this.id = id;
		this.cif = cif;
		this.name = name;
		this.zone = zone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<BusinessMiniDTO> getBusiness() {
		return business;
	}

	public void setBusiness(List<BusinessMiniDTO> business) {
		this.business = business;
	}
	
	public UserAdminDTO getUser() {
		return user;
	}

	public void setUser(UserAdminDTO user) {
		this.user = user;
	}
	
	public StatisticsMini getData() {
		return data;
	}

	public void setData(StatisticsMini data) {
		this.data = data;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "OperatorDTO [id=" + id + ", cif=" + cif + ", name=" + name + ", currencyId="
				+ ", zone=" + zone + "]";
	}
}
