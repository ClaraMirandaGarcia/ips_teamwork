package ips.gcp.logic.business;

public class Justificante {
	private String nombre;
	private String idCompeticion;
	private String categoria;
	private String fecha;
	private double amount;

	public Justificante(String nombre, String idCompeticion, String categoria, String fecha, double amount) {
		super();
		this.nombre = nombre;
		this.idCompeticion = idCompeticion;
		this.categoria = categoria;
		this.fecha = fecha;
		this.amount = amount;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdCompeticion() {
		return idCompeticion;
	}

	public void setIdCompeticion(String idCompeticion) {
		this.idCompeticion = idCompeticion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
