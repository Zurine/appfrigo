package es.smt.appfrigo.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String label;
    private List<String[]> data;
    private long date;
    
	public ListDTO() {
		super();
	}
	
	public ListDTO(String label, List<String[]> data, long date) {
		super();
		this.label = label;
		this.data = data;
		this.date = date;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<String[]> getData() {
		return data;
	}
	public void setData(List<String[]> data) {
		this.data = data;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}

}
