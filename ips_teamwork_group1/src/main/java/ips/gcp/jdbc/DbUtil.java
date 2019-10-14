package ips.gcp.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ips.gcp.logic.exception.ApplicationException;


public abstract class DbUtil {
	
	String driver;
	static String url;
	
	/** Obtencion de la url de conexion que debe implementarse en la subclase */
	public static String getUrl() {
		return url;
	}
	
	/** Obtiene un objeto conexion para esta base de datos */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(getUrl());
	}

	//Documentacion de apache dbutils:
	//https://commons.apache.org/proper/commons-dbutils/apidocs/index.html
	//https://commons.apache.org/proper/commons-dbutils/examples.html
	
	/**
	 * Metodo simple para ejecutar todas las sentencias sql que se encuentran en un archivo, teniendo en cuenta:
	 * <br/>- Cada sentencia DEBE finalizar en ; pudiendo ocupar varias lineas
	 * <br/>- Se permiten comentarios de linea (--)
	 * <br/>- Todas las sentencias drop se ejecutan al principio, 
	 * y se ignoran los fallos en caso de que no exista la tabla (solo para drop)
	 */
	public void executeScript(String fileName) {
		List<String> lines;
		try {
			lines=Files.readAllLines(Paths.get(fileName));
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		//separa las sentencias sql en dos listas, una para drop y otra para el resto pues se ejecutaran de forma diferente
		List<String> batchUpdate = new ArrayList<>();
		List<String> batchDrop = new ArrayList<>();
		StringBuilder previousLines=new StringBuilder(); //guarda lineas anteriores al separador (;)
		for (String line : lines) {
			line=line.trim();
			if (line.length()==0 || line.startsWith("--")) //ignora lineas vacias comentarios de linea
				continue;
			if (line.endsWith(";")) {
				String sql=previousLines.toString()+line;
				//separa drop del resto
				if (line.toLowerCase().startsWith("drop"))
					batchDrop.add(sql);
				else
					batchUpdate.add(sql);
				//nueva linea
				previousLines=new StringBuilder();
			} else {
				previousLines.append(line+" ");
			}
		}
		//Ejecuta todas las sentencias, primero los drop (si existen)
		if (!batchDrop.isEmpty())
			this.executeBatchNoFail(batchDrop);
		if (!batchUpdate.isEmpty())
			this.executeBatch(batchUpdate);
	}

	/**
	 * Ejecuta un conjunto de sentencias sql de actualizacion en un unico batch
	 */
	private void executeBatch(List<String> sqls) {
		try (Connection cn=DriverManager.getConnection(getUrl());
			Statement stmt = cn.createStatement()) {
				for (String sql : sqls)
					stmt.addBatch(sql);
				stmt.executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Ejecuta un conjunto de sentencias sql de actualizacion en un unico batch, sin causar excepcion cuando falla la ejecucion
	 * (usado normalmente para borrar tablas de la bd, que fallarian si no existen)
	 */
	private void executeBatchNoFail(List<String> sqls) {
		try (Connection cn=DriverManager.getConnection(getUrl());
			Statement stmt = cn.createStatement()) {
				for (String sql : sqls)
					executeWithoutException(stmt,sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private void executeWithoutException(Statement stmt, String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			//no causa excepcion intencionadamente
		}		
	}

}
