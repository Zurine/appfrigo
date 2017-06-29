package es.smt.appfrigo.bean;

public class TimeZone {

	private String name;
	private String offset;
	private Integer id;
	
	public TimeZone() {
		super();
	}

	public TimeZone(String name, String offset, int id) {
		super();
		this.name = name;
		this.offset = offset;
		this.id = id;
	}

	@Override
	public String toString() {
		return "TimeZone [name=" + name + ", offset=" + offset + ", id=" + id + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
