package es.smt.appfrigo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConditionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int condicionId;
    private String condicionSexo;
    private String condicionEdad;
    private String condicionTemperatura;
	
    public ConditionDTO() {
		super();
	}
	public ConditionDTO(int condicionId, String condicionSexo, String condicionEdad, String condicionTemperatura) {
		super();
		this.condicionId = condicionId;
		this.condicionSexo = condicionSexo;
		this.condicionEdad = condicionEdad;
		this.condicionTemperatura = condicionTemperatura;
	}
	public int getCondicionId() {
		return condicionId;
	}
	public void setCondicionId(int condicionId) {
		this.condicionId = condicionId;
	}
	public String getCondicionSexo() {
		return condicionSexo;
	}
	public void setCondicionSexo(String condicionSexo) {
		this.condicionSexo = condicionSexo;
	}
	public String getCondicionEdad() {
		return condicionEdad;
	}
	public void setCondicionEdad(String condicionEdad) {
		this.condicionEdad = condicionEdad;
	}
	public String getCondicionTemperatura() {
		return condicionTemperatura;
	}
	public void setCondicionTemperatura(String condicionTemperatura) {
		this.condicionTemperatura = condicionTemperatura;
	}
	@Override
	public String toString() {
		return "ConditionDTO [condicionId=" + condicionId + ", condicionSexo=" + condicionSexo + ", condicionEdad="
				+ condicionEdad + ", condicionTemperatura=" + condicionTemperatura + "]";
	}
}
