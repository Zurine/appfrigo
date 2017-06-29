package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.smt.appfrigo.bean.Channel;
import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.bean.Distributor;
import es.smt.appfrigo.bean.Operator;
import es.smt.appfrigo.bean.Region;
import es.smt.appfrigo.bean.Result;
import es.smt.appfrigo.bean.StatisticsMini;
import es.smt.appfrigo.validation.NumberPlate;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessDTO extends Result implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
//	@NotEmpty(message="Fiscal name is required")
//	@NotNull(message="Fiscal name is required")
//	private String nombreFiscal;
	@NotEmpty(message="Tradename is required")
	@NotNull(message="Tradename is required")
	@JsonProperty("nombreComercial")
	private String name;
//	@Pattern(regexp="^[a-zA-Z]{1}\\d{7}[a-jA-J0-9]{1}$", message="Invalid CIF")//Mirar para i18n
//	@JsonProperty("CIF")
//	private String cif;
	private double latitud;
	private double longitud;
	private String photo;
	private int enterpriseId;
	private boolean active;
//	@NotEmpty(message="Address is required")
	@NotNull(message="Address is required")
	private String direccion;
	private String ciudad;
//	@NotEmpty(message="Unilever Id is required")
//	@NotNull(message="Unilever Id is required")
	private String unileverId;
	private String desc;
	@JsonProperty("Beacon")
    private List<BeaconDTO> beacon;
	private Channel channel;
	private Region region;
	private BusinessTypeDTO type;
    @NotNull(message="Inactive Time is required")
	private int inactiveTime;
	private Distributor distributor;
	@NotNull(message="Tax is required")
	@Range(min=1,max=100, message="Tax must be a percentaje")
	private double iva;
	@NumberPlate
	private String numberPlate;  
	private boolean inactive;
	@JsonProperty("oper")
	private Operator operator;
	private Default tax;
	private boolean addProducts;

	private StatisticsMini data;

	public BusinessDTO() {
		super();
	}

	
	
	
	public BusinessDTO(int id) {
		super();
		this.id = id;

	}
	
	public BusinessDTO(Channel channel, Region region, BusinessTypeDTO type,
			Distributor distributor) {
		super();

		this.channel = channel;
		this.region = region;
		this.type = type;
		this.distributor = distributor;
	}

	

	public BusinessDTO(int id, String nombreFiscal, String name, String cif, double latitud, double longitud,
			String photo, int enterpriseId, boolean active, String direccion, String ciudad, String unileverId,
			String desc, List<BeaconDTO> beacon, Channel channel, Region region, BusinessTypeDTO type,
			int inactiveTime, Distributor distributor, int iva, String numberPlate, boolean inactive) {
		super();
		this.id = id;
		this.name = name;
		this.latitud = latitud;
		this.longitud = longitud;
		this.photo = photo;
		this.enterpriseId = enterpriseId;
		this.active = active;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.unileverId = unileverId;
		this.desc = desc;
		this.beacon = beacon;
		this.channel = channel;
		this.region = region;
		this.type = type;
		this.inactiveTime = inactiveTime;
		this.distributor = distributor;
		this.iva = iva;
		this.numberPlate = numberPlate;
		this.inactive = inactive;
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

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getUnileverId() {
		return unileverId;
	}

	public void setUnileverId(String unileverId) {
		this.unileverId = unileverId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<BeaconDTO> getBeacon() {
		return beacon;
	}

	public void setBeacon(List<BeaconDTO> beacon) {
		this.beacon = beacon;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public BusinessTypeDTO getType() {
		return type;
	}

	public void setType(BusinessTypeDTO type) {
		this.type = type;
	}

	public int getInactiveTime() {
		return inactiveTime;
	}

	public void setInactiveTime(int inactiveTime) {
		this.inactiveTime = inactiveTime;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	
	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public Default getTax() {
		return tax;
	}

	public void setTax(Default tax) {
		this.tax = tax;
	}

	
	public boolean isAddProducts() {
		return addProducts;
	}


	public void setAddProducts(boolean addProducts) {
		this.addProducts = addProducts;
	}


	

	public StatisticsMini getData() {
		return data;
	}




	public void setData(StatisticsMini data) {
		this.data = data;
	}




	@Override
	public String toString() {
		final int maxLen = 10;
		return "BusinessDTO [id=" + id +", name=" + name 
				+ ", latitud=" + latitud + ", longitud=" + longitud + ", photo=" + photo + ", enterpriseId="
				+ enterpriseId + ", active=" + active + ", direccion=" + direccion + ", ciudad=" + ciudad
				+ ", unileverId=" + unileverId + ", desc=" + desc + ", beacon="
				+ (beacon != null ? beacon.subList(0, Math.min(beacon.size(), maxLen)) : null) + ", channel=" + channel
				+ ", region=" + region + ", type=" + type + ", inactiveTime=" + inactiveTime + ", distributor="
				+ distributor + ", iva=" + iva + ", numberPlate=" + numberPlate + ", inactive=" + inactive + "]";
	}


	
	

}
