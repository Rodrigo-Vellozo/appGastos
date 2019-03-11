package br.com.rv.entity;

public class Chart {
	
	private String label;
	private Double y;
	
	public Chart() {

	}
	public Chart(String label, Double y) {
		super();
		this.label = label;
		this.y = y;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	
	
	
}
