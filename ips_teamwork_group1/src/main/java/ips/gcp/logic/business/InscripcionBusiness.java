package ips.gcp.logic.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import ips.gcp.jdbc.DbUtil;
import ips.gcp.logic.dto.AtletaDTO;
import ips.gcp.logic.dto.CompeticionDTO;
import ips.gcp.logic.exception.ApplicationException;

public class InscripcionBusiness {

	private CompeticionDTO competicion;

	private List<Categoria> categorias;

	private final static int AMOUNT_PAYED_BD = 0;
	private final static String STATE_BD = "PRE-INSCRITO";
	private static String SQL_INSERT_INSCRIPTION = "insert into Inscripcion(idAtleta, idCompeticion,"
			+ " fechaInscripcion, categoria, estado, cantidadPagada, tiempo, posicion)"
			+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SQL_SEARCH_ATHLETE_BY_EMAIL = "select * from atleta where email = ?";
	private final static String SQL_SEARCH_COMPETITION_BY_ID = "select * from competition where idCompeticion = ?";
	private final static String SQL_CHECK_MORE_THAN_ONCE = "select i.* from Inscripcion i, Atleta a where a.email = ? and a.idAtleta = i.idAtleta and i.idCompeticion = ?";
	private final static String SQL_CHECK_FREE_VACANCIES = "select * from Competicion where idCompeticion = ? and numeroPlazas <= 0";
	private final static String SQL_CORRECT_DATE = "SELECT * FROM Competicion WHERE idCompeticion = ? and (fechaInicioInscripcion >= ? OR fechaFinalInscripcion <= ?)";

	public InscripcionBusiness(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	/**
	 * @author CMG
	 * @throws BusinessException
	 */
	public Justificante solicita(String email, CompeticionDTO comp) throws BusinessException {

		try {
			// getConnection
			Connection c = DbUtil.getConnection();

			this.competicion = comp;

			// checking
			checkNotRepited(email, competicion.getIdCompeticion());
			checkInscripionDate(competicion.getIdCompeticion());
			checkFreeVacancies(competicion.getIdCompeticion());

			// inserting
			PreparedStatement pst = c.prepareStatement(SQL_INSERT_INSCRIPTION);
			AtletaDTO aux = searchAthletaByEmail(email);

			pst.setInt(1, aux.getIdAtleta());
			pst.setInt(2, competicion.getIdCompeticion());
			pst.setString(3, getFechaInscripción()); // Prueba pst.setDate(3, java.sql.Date.valueOf(getFechaInscripción));
			pst.setString(4, calcularCategoria(aux));
			pst.setString(5, STATE_BD);
			pst.setDouble(6, AMOUNT_PAYED_BD);
			pst.setTime(7, null);// HERE??
			pst.setInt(8, 0);// HERE??

			pst.execute(); //Creo que aquí es executeUpdate(), que devuelve el numero de filas que modifica, aunque ni idea

			// plazas libres de la competicion -> --
			int plazasLibres = competicion.getNumeroPlazas();
			comp.setNumeroPlazas(plazasLibres--);

			Justificante justAux = new Justificante(aux.getNombre(), competicion.getNombre(), calcularCategoria(aux),
					getFechaInscripción(), AMOUNT_PAYED_BD);

			c.close();
			return justAux;
		} catch (SQLException e) {
			throw new BusinessException(e.getMessage());
		}

	}

	private String getFechaInscripción() {
		// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		// LocalDate localDate = LocalDate.now();
		// return dtf.format(localDate); // 2016/11/16

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		String fecha = year + "-" + month + "-" + day;
		return fecha;
	}

	/**
	 * @author Adrian
	 */
	public String calcularCategoria(AtletaDTO atleta) throws ApplicationException {
		String fechaDeNacimiento = atleta.getFechaNacimiento(); 
		int edad = calcularEdad(fechaDeNacimiento);

		String nombreCat = "NOCAT";
		for (Categoria c : categorias) {
			if (edad >= c.getEdadInicio() && edad <= c.getEdadFin()) {
				nombreCat = c.getNombre();
				break;
			}
		}
		if (nombreCat.equals("NOCAT"))
			throw new ApplicationException(
					"No se pudo establecer la categoría para el atleta " + atleta.getIdAtleta());
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
			throw new BusinessException(e.getMessage());
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
				throw new BusinessException("No hay plazas libres en la competición " + idCompeticion);
			}

			c.close();
		} catch (SQLException e) {
			throw new BusinessException(e.getMessage());
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
				throw new BusinessException("No hay plazas libres en la competición " + idCompeticion);
			}

			c.close();
		} catch (SQLException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * @author CMG
	 * @throws BusinessException 
	 */
	public AtletaDTO searchAthletaByEmail(String email) throws BusinessException {
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
			throw new BusinessException(e.getMessage());
		}
		return aux;
	}

}
