package ips.gcp.logic.dto;

public class CompeticionDTO {
	
	private int idCompeticion;
	private String nombre;
	private String tipo;
	private String fechaInicioInscripcion; //Las fechas son strings con sqlite
	private String fechaFinalInscripcion;
	private String fechaCompeticion;
	private double cuota;
	private double distancia;
	private int numeroPlazas;
	
	
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
	public String getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}
	public void setFechaInicioInscripcion(String fechaInicio) {
		this.fechaInicioInscripcion = fechaInicio;
	}
	public String getFechaFinalInscripcion() {
		return fechaFinalInscripcion;
	}
	public void setFechaFinalInscripcion(String fechaFinal) {
		this.fechaFinalInscripcion = fechaFinal;
	}
	public String getFechaCompeticion() {
		return fechaCompeticion;
	}
	public void setFechaCompeticion(String fechaCompeticion) {
		this.fechaCompeticion = fechaCompeticion;
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
	public int getNumeroPlazas() {
		return numeroPlazas;
	}
	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}

	
}
