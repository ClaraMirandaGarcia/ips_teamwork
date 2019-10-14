package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ips.gcp.logic.Application;
import ips.gcp.logic.dto.CompeticionDTO;

public class UserWindow extends JDialog {
	
	private JPanel pnCompeticiones;
	private JScrollPane spTabla;
	private JTable tCompeticiones;
	
	private DefaultTableModel tableModel = null;
	private JPanel pnInscribirse;
	private JButton btnInscribirme;
	
	private JPanel pnButtonInscr;
	private JPanel pnRelleno;
	private JPanel pnButtonCancel;
	private JButton btnCancelar;
	
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
		
		Object[] newRow = new Object[6];

		List<CompeticionDTO> list = app.getCompeticionesAbiertas();
		
		for(CompeticionDTO dto : list) {
			newRow[0] = dto.getNombre();
			newRow[1] = dto.getTipo();
			newRow[2] = dto.getDistancia();
			newRow[3] = dto.getCuota();
			newRow[4] = dto.getFechaFinalInscripcion();
			newRow[5] = dto.getFechaCompeticion();
			
			tableModel.addRow(newRow);
		}
	}
	
	
	
	
//	##################################
//			WINDOW MAKER CODE
//	##################################
	/**
	 * Create the dialog.
	 */
	public UserWindow(MainWindow parent, Application app) {
		setTitle("Gestor de Carreras Populares: Usuario");
		this.mainWindow = parent;
		this.app = app;
		
		setBounds(100, 100, 857, 431);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnCompeticiones(), BorderLayout.CENTER);
		getContentPane().add(getPnInscribirse(), BorderLayout.EAST);
		
		//Init
		showCompeticiones();
	}
	
	private JPanel getPnCompeticiones() {
		if (pnCompeticiones == null) {
			pnCompeticiones = new JPanel();
			pnCompeticiones.setBackground(Color.WHITE);
			pnCompeticiones.setLayout(new BorderLayout(0, 0));
			pnCompeticiones.add(getSpTabla(), BorderLayout.CENTER);
		}
		return pnCompeticiones;
	}
	private JScrollPane getSpTabla() {
		if (spTabla == null) {
			spTabla = new JScrollPane();
			spTabla.setBackground(Color.WHITE);
			spTabla.setViewportView(getTCompeticiones());
		}
		return spTabla;
	}
	private JTable getTCompeticiones() {
		if (tCompeticiones == null) {
			String[] columnHeaders = {"Nombre", "Tipo", "Distancia", "Cuota", "Fin de inscripcion", "Fecha de competicion"};
			this.tableModel = new NotEditableTableModel(columnHeaders, 0);
			
			tCompeticiones = new JTable(tableModel);
			tCompeticiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tCompeticiones.getModel());
			tCompeticiones.setRowSorter(sorter);

			List<RowSorter.SortKey> sortKeys = new ArrayList<>();
			sortKeys.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
			sorter.setSortKeys(sortKeys);
		}
		return tCompeticiones;
	}
	private JPanel getPnInscribirse() {
		if (pnInscribirse == null) {
			pnInscribirse = new JPanel();
			pnInscribirse.setBackground(Color.WHITE);
			pnInscribirse.setLayout(new GridLayout(3, 1, 0, 0));
			pnInscribirse.add(getPnButtonInscr());
			pnInscribirse.add(getPnRelleno());
			pnInscribirse.add(getPnButtonCancel());
		}
		return pnInscribirse;
	}
	private JButton getBtnInscribirme() {
		if (btnInscribirme == null) {
			btnInscribirme = new JButton("Inscribirme");
			btnInscribirme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Código para inscribirse (Historia de usuario de Clara)
				}
			});
		}
		return btnInscribirme;
	}
	private JPanel getPnButtonInscr() {
		if (pnButtonInscr == null) {
			pnButtonInscr = new JPanel();
			pnButtonInscr.add(getBtnInscribirme());
		}
		return pnButtonInscr;
	}
	private JPanel getPnRelleno() {
		if (pnRelleno == null) {
			pnRelleno = new JPanel();
		}
		return pnRelleno;
	}
	private JPanel getPnButtonCancel() {
		if (pnButtonCancel == null) {
			pnButtonCancel = new JPanel();
			pnButtonCancel.setLayout(new BorderLayout(0, 0));
			pnButtonCancel.add(getBtnCancelar(), BorderLayout.SOUTH);
		}
		return pnButtonCancel;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

}
