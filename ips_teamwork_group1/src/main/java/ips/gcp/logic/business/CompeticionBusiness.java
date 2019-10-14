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
	private final static String SQL_GET_ALL_COMPETICIONES_ABIERTAS = "";

	/**
	 * Visualizar todas las competiciones abiertas para poder inscribir a un atleta.
	 * 
	 * @author Pablo
	 * @throws BusinessException
	 */
	public List<CompeticionDTO> getCompeticionesAbiertas() throws BusinessException {
		List<CompeticionDTO> dtoList = new ArrayList<>();

		try {
			con = DbUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(SQL_GET_ALL_COMPETICIONES_ABIERTAS);

			ResultSet rs = ps.executeQuery();

		} catch (SQLException e) {
			throw new BusinessException(e);
		}

		return null;
	}

}
