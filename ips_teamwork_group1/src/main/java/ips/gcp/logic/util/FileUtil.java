package ips.gcp.logic.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ips.gcp.logic.business.Categoria;
import ips.gcp.logic.dto.AtletaDTO;


public abstract class FileUtil {
	
	/**
	 * @author Adrian
	 */
	public static List<Categoria> loadFile (String inputFileName) {
	    String line;
	    String[] data = null;	   
	    List<Categoria> categorias = new ArrayList<>();
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(inputFileName));
	    		while (file.ready()) {
	    			line = file.readLine();
	    			data = line.split("@");
	    			categorias.add(new Categoria(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
	    		}
	    		file.close();
	    } catch (FileNotFoundException fnfe) {
	      System.out.println(inputFileName + " file not found.");
	    } catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    } 
	    return categorias;
	}
	
	/**
	 * @author Lucia
	 */
	public static int saveUserFile(AtletaDTO a) {
		String fileName = "src/files/"+a.getEmail()+".dat";
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter(fileName, true));
			fichero.write("¡Has sido registrado! Tus datos: \n");
			fichero.write("Nombre: "+a.getNombre()+"\n");
			fichero.write("Sexo: "+a.getSexo()+"\n");
			fichero.write("DNI: "+a.getDni()+"\n");
			fichero.write("Email: "+a.getEmail()+"\n");
			fichero.write("Fecha de Nacimiento: "+a.getFechaNacimiento()+"\n");
			fichero.write("\n");
			
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			String fecha = year + "-" + month + "-" + day;
			fichero.write("Fecha de Registro: "+fecha);
			
			fichero.close();
			return (0);
		} catch (FileNotFoundException fnfe) {
			System.out.println("The file could not be saved");
			return (-1);
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
			return (-2);
		}
	}

}
