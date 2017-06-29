package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

public class BreadCrumb implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Data> steps;
	

	public BreadCrumb() {
		super();
	}
	

	public BreadCrumb(List<Data> steps) {
		super();
		this.steps = steps;
	}

	public List<Data> getSteps() {
		return steps;
	}

	public void setSteps(List<Data> steps) {
		this.steps = steps;
	}
	
	
}
