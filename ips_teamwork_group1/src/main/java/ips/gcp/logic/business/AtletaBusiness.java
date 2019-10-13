package ips.gcp.logic.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ips.gcp.jdbc.Database;
import ips.gcp.jdbc.DbUtil;
import ips.gcp.logic.dto.AtletaDTO;

public class AtletaBusiness {
	private final static String SQL_INSERT = "insert into Atleta(idAtleta, dni, nombre, email, fechaNacimiento, sexo) values (?, ?, ?, ?, ?, ?)";

	public void addAtleta(AtletaDTO a) {
		try {
			Database database = new Database();
			database.createDatabase(true);
			database.loadDatabase();

			Connection c = database.getConnection();
			
			check(a);

			PreparedStatement pst = c.prepareStatement(SQL_INSERT);
			pst.setInt(1, calcularId()); // not implemented yet!!!!
			pst.setString(2, a.getDni());
			pst.setString(3, a.getNombre());
			pst.setString(4, a.getEmail());
			pst.setString(5, a.getFechaNacimiento());
			pst.setString(6, a.getSexo());
			
			pst.execute();

			c.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error connection to the database");
		}

	}

	private void check(AtletaDTO a) {
		//IDEA COPIADA DE RI, AUN NO FUNCIONA!!!!!!!!!!!!!!!!!!!!!!!!
		
		/*pst2 = c.prepareStatement(CHECK);
		pst2.setString(1, this.mechanic.dni);
		rs2=pst2.executeQuery();
		
		//or if(rs2.next())
		rs2.next();
		if(rs2.getRow()!=0) {
			throw new BusinessException("Error: DNI already in the database");
		} */
	}

	private int calcularId() {
		// TODO Auto-generated method stub
		//select id from atletas order by id rownum=1 (sumar uno y returnear)
		return 0;
	}
}
