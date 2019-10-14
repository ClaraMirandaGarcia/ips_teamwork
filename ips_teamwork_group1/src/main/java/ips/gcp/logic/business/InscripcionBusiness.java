package ips.gcp.logic.business;

import java.util.Calendar;

import ips.gcp.logic.dto.AtletaDTO;
import ips.gcp.logic.dto.InscripcionDTO;
import ips.gcp.logic.exception.ApplicationException;

public class InscripcionBusiness {

	private AtletaDTO atleta;
	private InscripcionDTO inscripcion;
	
	private Categorias cs;
	
	public InscripcionBusiness(AtletaDTO atleta, InscripcionDTO inscripcion, Categorias cs) {
		this.atleta = atleta;
		this.inscripcion = inscripcion;
		this.cs = cs;
	}
	
	/**
	 * @author Adrian
	 */
	public void calcularCategoria() throws ApplicationException{
		String fechaDeNacimiento = atleta.getFechaNacimiento();
		int edad = calcularEdad(fechaDeNacimiento);
		
		String nombreCat = "NOCAT";
		for(Categoria c : cs.getCategorias()) {
			if(edad >= c.getEdadInicio() && edad <= c.getEdadFin()) {
				nombreCat = c.getNombre();
				break;
			}
		}
		if(nombreCat.equals("NOCAT"))
			throw new ApplicationException("No se pudo establecer la categoría para el atleta " + atleta.getIdAtleta());
		inscripcion.setCategoria(nombreCat);
	}
	
	/**
	 * Se asume que el formato de la String es YYYY-MM-DD
	 * @author Adrian
	 */
	private int calcularEdad(String fechaDeNacimiento) {
		String[] strings = fechaDeNacimiento.split("-");
		int añoDeNacimiento = Integer.parseInt(strings[0]);
		int añoActual = Calendar.getInstance().get(Calendar.YEAR);
		return añoActual - añoDeNacimiento;
	}
	
}
