package ips.gcp.logic.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import ips.gcp.jdbc.DbUtil;
import ips.gcp.logic.dto.CompeticionDTO;

public class CompeticionBusiness {

	private Connection con;

	// QUERIES
	private final static String SQL_GET_ALL_COMPETICIONES_ABIERTAS = "SELECT nombre, tipo, distancia, cuota, fechaFinalInscripcion, fechaCompeticion, numeroPlazas FROM COMPETICION WHERE fechaInicioInscripcion < ? AND fechaFinalInscripcion > ?";

	/**
	 * Visualizar todas las competiciones abiertas para poder inscribir a un atleta.
	 * 
	 * @author Pablo
	 * @throws BusinessException
	 */
	public List<CompeticionDTO> getCompeticionesAbiertas() throws BusinessException {
		List<CompeticionDTO> dtoList = new ArrayList<>();

		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		String fecha = year + "-" + month + "-" + day;
		try {
			con = DbUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_COMPETICIONES_ABIERTAS);
			ps.setString(1, fecha);
			ps.setString(2, fecha);
			ResultSet rs = ps.executeQuery();

			CompeticionDTO dto = null;
			while (rs.next()) {
				dto = new CompeticionDTO();
				dto.setNombre(rs.getString("nombre"));
				dto.setTipo(rs.getString("tipo"));
				dto.setDistancia(rs.getDouble("distancia"));
				dto.setCuota(rs.getDouble("cuota"));
				dto.setFechaFinalInscripcion(rs.getString("fechaFinalInscripcion"));
				dto.setFechaCompeticion(rs.getString("fechaCompeticion"));
				dto.setNumeroPlazas(rs.getInt("numeroPlazas"));

				dtoList.add(dto);
			}

			con.close();

			return dtoList;
		} catch (SQLException e) {
			throw new BusinessException(e);
		}
	}
}
