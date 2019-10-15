package ips.gcp.logic.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import ips.gcp.jdbc.DbUtil;
import ips.gcp.logic.dto.AtletaDTO;
import ips.gcp.logic.dto.CompeticionDTO;
import ips.gcp.logic.dto.InscripcionDTO;
import ips.gcp.logic.exception.ApplicationException;

public class InscripcionBusiness {

	private CompeticionDTO competicion;

	private Categorias cs;

	private final static int AMOUNT_PAYED_BD = 0;
	private final static String STATE_BD = "PRE-INSCRITO";
	private static String SQL_INSERT_INSCRIPTION = "insert into Inscripcion(idAtleta, idCompeticion,"
			+ " fechaInscripcion, categoria, estado, cantidadPagada, tiempo, posicion)"
			+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_SEARCH_ATHLETE_BY_EMAIL = "select * from atleta where email = ?";
	private final static String SQL_SEARCH_COMPETITION_BY_ID = "select * from competition where idCompetition = ?";
	private final static String SQL_CHECK_MORE_THAN_ONCE = "select * from inscripcion where email = ? and idCompeticion = ?";
    private final static String SQL_CHECK_FREE_VACANCIES = "select * from competition where idCompeticion = ? and numeroPlazas <= 0";
    private final static String SQL_CORRECT_DATE = "SELECT * FROM COMPETICION WHERE idcompetition = ? and (fechaInicioInscripcion >= ? OR fechaFinalInscripcion <= ?)";

	public InscripcionBusiness(Categorias cs) {

		this.cs = cs;

	}
	
	/**
	 * @author CMG
	 */
	public void solicita(String email, CompeticionDTO competicion) {

		try {
			// getConnection
			Connection c = DbUtil.getConnection();

			this.competicion = competicion;
			
			 checkNotRepited(email, competicion.getIdCompeticion());
			 checkInscripionDate(competicion.getIdCompeticion());
			 checkFreeVacancies(competicion.getIdCompeticion());

			// inserting
			PreparedStatement pst = c.prepareStatement(SQL_INSERT_INSCRIPTION);
			AtletaDTO aux = searchAthletaByEmail(email);

			pst.setInt(1, aux.getIdAtleta());
			pst.setInt(2, competicion.getIdCompeticion());
			pst.setString(3, getFechaInscripción());
			calcularCategoria();
			//pst.setString(4, inscripcion.getCategoria());
			pst.setString(5, STATE_BD);
			pst.setDouble(6, AMOUNT_PAYED_BD);
			pst.setTime(7, null);// HERE??
			pst.setInt(8, 0);// HERE??

			pst.execute();

            // plazas libres de la competicion -> --

			c.close();
		} catch (SQLException e) {

		}
		// checking
		catch (BusinessException e) {
			//AQUÍ?
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getFechaInscripción() {
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		//LocalDate localDate = LocalDate.now();
		//return dtf.format(localDate); // 2016/11/16


        int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		String fecha = year + "-" + month + "-" + day;
        return fecha;
	}

	/**
	 * @author Adrian
	 */
	public String calcularCategoria() throws ApplicationException {
		String fechaDeNacimiento = /*atleta.getFechaNacimiento();*/null;
		int edad = calcularEdad(fechaDeNacimiento);

		String nombreCat = "NOCAT";
		for (Categoria c : cs.getCategorias()) {
			if (edad >= c.getEdadInicio() && edad <= c.getEdadFin()) {
				nombreCat = c.getNombre();
				break;
			}
		}
		if (nombreCat.equals("NOCAT"))
			throw new ApplicationException("No se pudo establecer la categoría para el atleta "/* + atleta.getIdAtleta()*/);
		return nombreCat;
	}

	/**
	 * Se asume que el formato de la String es YYYY-MM-DD
	 * 
	 * @author Adrian
	 */
	private int calcularEdad(String fechaDeNacimiento) {
		String[] strings = fechaDeNacimiento.split("-");
		int añoDeNacimiento = Integer.parseInt(strings[0]);
		int añoActual = Calendar.getInstance().get(Calendar.YEAR);
		return añoActual - añoDeNacimiento;
	}

	/**
	 * @author CMG
	 * @throws BusinessException
	 */
	public void checkNotRepited(String email, int idCompeticion) throws BusinessException {
		try {
			// getConnection
			Connection c = DbUtil.getConnection();

			// inserting
			PreparedStatement pst = c.prepareStatement(SQL_CHECK_MORE_THAN_ONCE);
			pst.setString(1, email);
			pst.setInt(2, idCompeticion);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				throw new BusinessException("El atleta con email " + email + " ya estaba inscrito en la competición.");
			}

			c.close();
		} catch (SQLException e) {

		}
	}

	/**
	 * @author CMG
	 * @throws BusinessException 
	 */
	public void checkInscripionDate(int idCompeticion) throws BusinessException {
       try {
			// getConnection
			Connection c = DbUtil.getConnection();

			// inserting
			PreparedStatement pst = c.prepareStatement(SQL_CORRECT_DATE);
			pst.setInt(1, idCompeticion);
            pst.setString(2, getFechaInscripción());
            pst.setString(3, getFechaInscripción());
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				throw new BusinessException("No hay plazas libres en la competición " + idCompeticion );
			}

			c.close();
		} catch (SQLException e) {

		}  
	}

	/**
	 * @author CMG
	 * @throws BusinessException 
	 */
	public void checkFreeVacancies(int idCompeticion) throws BusinessException {
		try {
			// getConnection
			Connection c = DbUtil.getConnection();

			// inserting
			PreparedStatement pst = c.prepareStatement(SQL_CHECK_FREE_VACANCIES);
			pst.setInt(1, idCompeticion);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				throw new BusinessException("No hay plazas libres en la competición " + idCompeticion );
			}

			c.close();
		} catch (SQLException e) {

		}
	}

	/**
	 * @author CMG
	 */
	public AtletaDTO searchAthletaByEmail(String email) {
		AtletaDTO aux = new AtletaDTO();

		// getConnection
		try {
			Connection c = DbUtil.getConnection();
			PreparedStatement pst = c.prepareStatement(SQL_SEARCH_ATHLETE_BY_EMAIL);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();

			int idAtleta = rs.getInt(1);
			String dni = rs.getString(2);
			String nombre = rs.getString(3);
			String email_at = rs.getString(4);
			String fechaNacimiento = rs.getString(5);
			String sexo = rs.getString(6);

			aux.setIdAtleta(idAtleta);
			aux.setDni(dni);
			aux.setNombre(nombre);
			aux.setEmail(email_at);
			aux.setFechaNacimiento(fechaNacimiento);
			aux.setSexo(sexo);

			c.close();
			// return aux;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}

}
