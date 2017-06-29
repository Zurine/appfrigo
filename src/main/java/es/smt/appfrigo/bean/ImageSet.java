package es.smt.appfrigo.bean;

import java.io.Serializable;

public class ImageSet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String extension;
	private String name;
	private String location;
	private String path;
	private String auxLocation;
	private String contentType;
	
	public ImageSet() {
		super();
	}

	public ImageSet(String extension, String name, String location, String path) {
		super();
		this.extension = extension;
		this.name = name;
		this.location = location;
		this.path = path;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getAuxLocation() {
		return auxLocation;
	}

	public void setAuxLocation(String auxLocation) {
		this.auxLocation = auxLocation;
	}
	
	

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "ImageSet [extension=" + extension + ", name=" + name + ", location=" + location + ", path=" + path
				+ "]";
	}
	
}
