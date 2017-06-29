package es.smt.appfrigo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdBusMini implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private boolean compound;

      
	public ProdBusMini() {
		super();
	}


	public ProdBusMini(int id, String name, boolean compound) {
		super();
		this.id = id;
		this.name = name;
		this.compound = compound;
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


	public boolean isCompound() {
		return compound;
	}


	public void setCompound(boolean compound) {
		this.compound = compound;
	}


	@Override
	public String toString() {
		return "ProdBusMini [id=" + id + ", name=" + name + ", compound=" + compound + "]";
	}

	
      
}
