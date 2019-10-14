package ips.gcp.logic.business;

/**
 * @author Adrian
 */
public class Categoria {

	private String nombre;
	private int edadInicio;
	private int edadFin;
	
	public Categoria(String nombre, int edadInicio, int edadFin) {
		this.nombre = nombre;
		this.edadInicio = edadInicio;
		this.edadFin = edadFin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdadInicio() {
		return edadInicio;
	}

	public void setEdadInicio(int edadInicio) {
		this.edadInicio = edadInicio;
	}

	public int getEdadFin() {
		return edadFin;
	}

	public void setEdadFin(int edadFin) {
		this.edadFin = edadFin;
	}
	
}
