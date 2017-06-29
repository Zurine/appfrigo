package es.smt.appfrigo.bean;

import java.io.Serializable;

public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
    private String name;
    
	public Item() {
		super();
	}
	
	public Item(int id, String description) {
		super();
		this.id = id;
		this.name = description;
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", description=" + name + "]";
	}
	

    
}
