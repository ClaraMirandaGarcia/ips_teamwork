package ips.gcp.logic.dto;

public class CompeticionDTO {
	
	private int idCompeticion;
	private String nombre;
	private String tipo;
	private String fechaInicio; //Las fechas son strings con sqlite
	private String fechaFinal;
	private double cuota;
	private double distancia;
	
	
	public int getIdCompeticion() {
		return idCompeticion;
	}
	public void setIdCompeticion(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public double getCuota() {
		return cuota;
	}
	public void setCuota(double cuota) {
		this.cuota = cuota;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	
}
