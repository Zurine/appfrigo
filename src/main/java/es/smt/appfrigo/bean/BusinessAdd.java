package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

public class BusinessAdd implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<Integer> list;
	
	public BusinessAdd() {
		super();
	}

	public BusinessAdd(int id, String name, List<Integer> list) {
		super();
		this.id = id;
		this.name = name;
		this.list = list;
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

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "BusinessAdd [id=" + id + ", name=" + name + ", list=" + list + "]";
	}
	
	
	
}
