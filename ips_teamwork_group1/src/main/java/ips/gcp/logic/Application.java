package ips.gcp.logic;

import ips.gcp.logic.business.CompeticionBusiness;
import ips.gcp.logic.dto.CompeticionDTO;

public class Application {
	
	/**
	 * @author Pablo
	 */
	public CompeticionDTO getCompeticionesAbiertas() {
		CompeticionDTO dto = new CompeticionBusiness().getCompeticionesAbiertas();
		
		return dto;
	}
}
