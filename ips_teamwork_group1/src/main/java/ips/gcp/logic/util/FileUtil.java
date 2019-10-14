package ips.gcp.logic.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ips.gcp.logic.business.Categoria;

/**
 * @author Adrian
 */
public abstract class FileUtil {
	
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

}
