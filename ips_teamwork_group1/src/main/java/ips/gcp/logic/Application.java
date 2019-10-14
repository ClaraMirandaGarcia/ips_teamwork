package ips.gcp.logic;

import java.util.List;

import ips.gcp.logic.business.BusinessException;
import ips.gcp.logic.business.CompeticionBusiness;
import ips.gcp.logic.dto.CompeticionDTO;
import ips.gcp.logic.exception.ApplicationException;

public class Application {
	
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
}
