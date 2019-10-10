package ips.gcp.logic.dto;

import java.sql.Time;

public class InscripcionDTO {
	
	private int idAtleta;
	private int idCompeticion;
	private String fechaInscripcion;
	private String categoria;
	private String estado;
	private double cantidadPagada;
	private Time tiempo; // REVISAR --> Datatype Time?
	private int posicion;
	
	
	public int getIdAtleta() {
		return idAtleta;
	}
	public void setIdAtleta(int idAtleta) {
		this.idAtleta = idAtleta;
	}
	public int getIdCompeticion() {
		return idCompeticion;
	}
	public void setIdCompeticion(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getCantidadPagada() {
		return cantidadPagada;
	}
	public void setCantidadPagada(double cantidadPagada) {
		this.cantidadPagada = cantidadPagada;
	}
	public Time getTiempo() {
		return tiempo;
	}
	public void setTiempo(Time tiempo) {
		this.tiempo = tiempo;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	
	

}
