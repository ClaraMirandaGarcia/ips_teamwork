package ips.gcp.logic;

import java.util.List;

import ips.gcp.logic.business.AtletaBusiness;
import ips.gcp.logic.business.BusinessException;
import ips.gcp.logic.business.Categorias;
import ips.gcp.logic.business.CompeticionBusiness;
import ips.gcp.logic.business.InscripcionBusiness;
import ips.gcp.logic.business.Justificante;
import ips.gcp.logic.dto.AtletaDTO;
import ips.gcp.logic.dto.CompeticionDTO;
import ips.gcp.logic.exception.ApplicationException;

public class Application {

	private InscripcionBusiness inscripcionBusiness;
	private Categorias categorias = new Categorias();

	/**
	 * @author Pablo
	 */
	public List<CompeticionDTO> getCompeticionesAbiertas() {
		List<CompeticionDTO> dtoList;
		try {
			dtoList = new CompeticionBusiness().getCompeticionesAbiertas();
		} catch (BusinessException e) {
			throw new ApplicationException("Error: no se pudieron obtener las competiciones" + e);
		}

		return dtoList;
	}

	/**
	 * @author Lucia
	 */
	public void addAtleta(AtletaDTO a) {
		try {
			new AtletaBusiness().addAtleta(a);
		} catch (BusinessException e) {
			throw new ApplicationException("Error: datos(email/dni) repetidos" + e);
		}
	}

//	/**
//	 * 
//	 * @author Adrian
//	 */
//	public void createInscripcion(CompeticionDTO dto) {
//		this.competicion = dto;
//	}

	public Justificante solicitar(String email, CompeticionDTO dto) {
		categorias.cargarCategorias("src/main/resources/categories.txt");
		inscripcionBusiness = new InscripcionBusiness(categorias);
		try {
			Justificante just = inscripcionBusiness.solicita(email, dto);
			return just;
		} catch (BusinessException e) {
			throw new ApplicationException(e.getMessage());
		}
	}
}
