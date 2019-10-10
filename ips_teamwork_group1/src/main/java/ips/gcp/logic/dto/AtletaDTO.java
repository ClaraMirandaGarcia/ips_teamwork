package ips.gcp.logic.dto;

public class AtletaDTO {

	private int idAtleta;
	private String dni;
	private String nombre; //Nombre y apellidos
	private String email;
	private String fechaNacimiento;
	private String sexo;
	
	public int getIdAtleta() {
		return idAtleta;
	}
	public void setIdAtleta(int idAtleta) {
		this.idAtleta = idAtleta;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	

}
