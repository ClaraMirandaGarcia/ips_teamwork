package ips.gcp.logic.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ips.gcp.jdbc.DbUtil;
import ips.gcp.logic.business.BusinessException;
import ips.gcp.logic.dto.AtletaDTO;

public class AtletaBusiness {
	private final static String SQL_OBTAIN_ALL_ATLETAS = "SELECT idAtleta, dni, nombre, email, fechaNacimiento, sexo FROM ATLETA";
	private final static String SQL_INSERT = "insert into Atleta(idAtleta, dni, nombre, email, fechaNacimiento, sexo) values (?, ?, ?, ?, ?, ?)";
	private final static String CHECK_DNI = "select * from Atleta where dni = ?";
	private final static String CHECK_EMAIL = "select * from Atleta where email = ?";
	private final static String SQL_LAST_ID = "select max(idAtleta) from Atleta";

	/**
	 * @author Lucia
	 */
	public void addAtleta(AtletaDTO a) throws BusinessException {
		try {
			Connection c = DbUtil.getConnection();

			checkDni(a.getDni());
			checkEmail(a.getEmail());

			PreparedStatement pst = c.prepareStatement(SQL_INSERT);
			pst.setInt(1, calcularId()); // calcularId() 
			pst.setString(2, a.getDni());
			pst.setString(3, a.getNombre());
			pst.setString(4, a.getEmail());
			pst.setString(5, a.getFechaNacimiento());
			pst.setString(6, a.getSexo());

			pst.execute();

			c.close();

			printAtletas();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//throw new RuntimeException("Error connecting to the database");
		}

	}

	/**
	 * @author Lucia
	 */
	private void printAtletas() throws SQLException {
		Connection c = DbUtil.getConnection();

		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(SQL_OBTAIN_ALL_ATLETAS);

		AtletaDTO dto = new AtletaDTO();

		while (rs.next()) {
			dto.setIdAtleta(rs.getInt("idAtleta"));
			dto.setDni(rs.getString("dni"));
			dto.setNombre(rs.getString("nombre"));
			dto.setEmail(rs.getString("email"));
			dto.setFechaNacimiento(rs.getString("fechaNacimiento"));
			dto.setSexo(rs.getString("sexo"));
			System.out.println(dto.getIdAtleta() + " " + dto.getDni() + " " + dto.getNombre() + " " + dto.getEmail()
					+ " " + dto.getFechaNacimiento() + " " + dto.getSexo());
		}

		c.close();
	}

	/**
	 * @author Lucia
	 */
	private void checkDni(String dni) throws BusinessException {
		try {
			Connection c = DbUtil.getConnection();

			PreparedStatement pst = c.prepareStatement(CHECK_DNI);

			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				throw new BusinessException("Error: DNI already in the database");
			}
			
			c.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error connecting to the database");
		}

	}

	/**
	 * @author Lucia
	 */
	private void checkEmail(String email) throws BusinessException {
		try {
			Connection c = DbUtil.getConnection();

			PreparedStatement pst = c.prepareStatement(CHECK_EMAIL);

			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				throw new BusinessException("Error: email already in the database");
			}
			
			c.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error connecting to the database");
		}
	}

	/**
	 * @author Lucia
	 */
	private int calcularId() {
		try {
			Connection c = DbUtil.getConnection();

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(SQL_LAST_ID);
			
			rs.next();
			int res=  rs.getInt(1)+1;
			
			c.close();
			return res;
		} catch (SQLException e) {
			throw new RuntimeException("Error connecting to the database");
		}
	}
}
