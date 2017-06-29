package es.smt.appfrigo.bean;

public class WorkDayRelation {

	private int id;
    private String name;
    private double hours;
    private int days;
    private double sales;
    private double relTime;
    private double relSales;
    
	public WorkDayRelation() {
		super();
	}
	public WorkDayRelation(int id, String name, double hours, int days, double sales, double relTime, double relSales) {
		super();
		this.id = id;
		this.name = name;
		this.hours = hours;
		this.days = days;
		this.sales = sales;
		this.relTime = relTime;
		this.relSales = relSales;
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
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public double getRelTime() {
		return relTime;
	}
	public void setRelTime(double relTime) {
		this.relTime = relTime;
	}
	public double getRelSales() {
		return relSales;
	}
	public void setRelSales(double relSales) {
		this.relSales = relSales;
	}
	@Override
	public String toString() {
		return "WorkDayRelation [id=" + id + ", name=" + name + ", hours=" + hours + ", days=" + days + ", sales="
				+ sales + ", relTime=" + relTime + ", relSales=" + relSales + "]";
	}
    
    
}
