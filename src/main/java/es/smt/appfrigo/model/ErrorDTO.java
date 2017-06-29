package es.smt.appfrigo.model;

import java.io.Serializable;

public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int code;
	 private String desc;
	public ErrorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorDTO(int code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "ErrorDTO [code=" + code + ", desc=" + desc + "]";
	}
	 
	 
}
