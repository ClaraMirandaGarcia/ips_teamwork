package ips.gcp.logic;

import ips.gcp.jdbc.Database;
import ips.gcp.logic.business.CompeticionBusiness;
import ips.gcp.logic.dto.CompeticionDTO;

public class Application {
	
	private Database db;
	
	public Application(Database database) {
		this.db = database;
	}
	
	public CompeticionDTO getCompeticionesAbiertas() {
		CompeticionDTO dto = new CompeticionBusiness().getCompeticionesAbiertas();
		
		return dto;
	}

}
