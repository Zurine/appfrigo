package es.smt.appfrigo.bean;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	private MultipartFile file;
	
	private int id;
	private String path;
	private double zoom;
	private double x;
	private double y;
	private double w;
	private double h;
	
	
     
    public Image(String path) {
		super();
		this.path = path;
	}

	public Image() {
		super();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MultipartFile getFile() {
        return file;
    }
	 
    public void setFile(MultipartFile file) {
        this.file = file;
    }

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FileBean [file=" + file + ", path=" + path + ", zoom=" + zoom + ", x=" + x + ", y=" + y + ", w=" + w
				+ ", h=" + h + "]";
	}


    
    
}
