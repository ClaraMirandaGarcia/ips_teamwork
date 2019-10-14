package ips.gcp.jdbc;

import java.io.Console;
import java.sql.*;
import java.util.Calendar;

import ips.gcp.logic.dto.AtletaDTO;

public class DatabaseDemo {

	
	private final static String SQL_OBTAIN_ALL_ATLETAS = "SELECT idAtleta, dni, nombre, email, fechaNacimiento, sexo FROM ATLETA";
	
	private static Connection con;
	
	public static void main(String[] args) {
		Database db = new Database();
		db.createDatabase(false);
		db.loadDatabase();
		
		obtainAllAtletas();
	}

	private static void obtainAllAtletas() {
		try {		
			
			AtletaDTO dto = new AtletaDTO();
			
			con = DbUtil.getConnection();
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(SQL_OBTAIN_ALL_ATLETAS);
			
			while(rs.next()) {
				dto.setIdAtleta(rs.getInt("idAtleta"));
				dto.setDni(rs.getString("dni"));
				dto.setNombre(rs.getString("nombre"));
				dto.setEmail(rs.getString("email"));
				dto.setFechaNacimiento(rs.getString("fechaNacimiento"));
				dto.setSexo(rs.getString("sexo"));
				System.out.println(dto.getIdAtleta()+" "+dto.getDni()+" "+dto.getNombre()+" "+dto.getEmail()+" "+dto.getFechaNacimiento()+" "+dto.getSexo());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
