package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ips.gcp.logic.Application;
import ips.gcp.logic.dto.CompeticionDTO;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class UserReviewWindow extends JDialog {
	private JPanel pnBase;
	private JScrollPane spTabla;
	private JTable tCompeticiones;
	
	private DefaultTableModel tableModel = null;
	
	private MainWindow mainWindow = null;
	private Application app = null;
	
	
//	##################################
//			MÉTODOS PRIVADOS
//	##################################
	/**
	* @author Pablo
	*/
	private void showCompeticiones() {
		tableModel.getDataVector().clear();
		tableModel.fireTableDataChanged();
		
//		#### OBTENER LISTA DE COMPETICIONES EN DONDE SE INSCRIBIÓ UN ATLETA ####
//		
//		Object[] newRow = new Object[6];
//		
//		List<CompeticionDTO>list = app.getCompeticionesAbiertas();
//		
//		for(CompeticionDTO dto : list) {
//			newRow[0] = dto.getNombre();
//			newRow[1] = dto.getTipo();
//			newRow[2] = dto.getDistancia();
//			newRow[3] = dto.getCuota();
//			newRow[4] = dto.getFechaFinalInscripcion();
//			newRow[5] = dto.getFechaCompeticion();
//			
//			tableModel.addRow(newRow);
//		}
	}

	
	
	
//	##################################
//			WINDOW MAKER CODE
//	##################################

	/**
	 * Create the dialog.
	 */
	public UserReviewWindow() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 857, 431);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnBase(), BorderLayout.CENTER);
	}

	private JPanel getPnBase() {
		if (pnBase == null) {
			pnBase = new JPanel();
			pnBase.setBackground(Color.WHITE);
			pnBase.setLayout(new BorderLayout(0, 0));
			pnBase.add(getSpTabla(), BorderLayout.CENTER);
		}
		return pnBase;
	}
	private JScrollPane getSpTabla() {
		if (spTabla == null) {
			spTabla = new JScrollPane();
			spTabla.setViewportView(getTCompeticiones());
		}
		return spTabla;
	}
	private JTable getTCompeticiones() {
		if (tCompeticiones == null) {
			String[] columnHeaders = {"Nombre", "Estado de inscripcion", "Último cambio"};
			this.tableModel = new NotEditableTableModel(columnHeaders, 0);
			
			tCompeticiones = new JTable(tableModel);
			tCompeticiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tCompeticiones;
	}
}
