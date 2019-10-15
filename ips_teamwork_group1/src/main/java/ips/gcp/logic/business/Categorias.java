package ips.gcp.logic.business;

import java.util.List;

import ips.gcp.logic.util.FileUtil;

/**
 * @author Adrian
 */
public class Categorias {

	private List<Categoria> categorias;
	
	public List<Categoria> getCategorias(){
		return categorias;
	}
	
	public void addCategoria(Categoria c) {
		categorias.add(c);
	}
	
	public List<Categoria> cargarCategorias(String filename) {
		return FileUtil.loadFile(filename);
	}
	
}
