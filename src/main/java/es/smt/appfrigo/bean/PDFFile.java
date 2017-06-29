package es.smt.appfrigo.bean;

import java.io.Serializable;
import java.util.List;

public class PDFFile implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<String> header;
	private List<Data> filter;
	private List<List<List<String>>> table;
	private String title;
	private String path;
	private String startDate;
	private String endDate;
	private int maxSize;
	private List<Double> cols;
	private long name;
	
	
	public PDFFile() {
		super();
	}
	
	public PDFFile(List<String> header, List<List<List<String>>> table) {
		super();
		this.header = header;
		this.table = table;
	}

	public List<String> getHeader() {
		return header;
	}
	public void setHeader(List<String> header) {
		this.header = header;
	}
	public List<List<List<String>>> getTable() {
		return table;
	}
	public void setTable(List<List<List<String>>> table) {
		this.table = table;
	}

	public List<Data> getFilter() {
		return filter;
	}

	public void setFilter(List<Data> filter) {
		this.filter = filter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public List<Double> getCols() {
		return cols;
	}

	public void setCols(List<Double> cols) {
		this.cols = cols;
	}

	public long getName() {
		return name;
	}

	public void setName(long name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PDFFile [header=" + header + ", table=" + table + "]";
	}
	
	
	
	
}
