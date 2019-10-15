package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ips.gcp.logic.Application;
import ips.gcp.logic.business.Justificante;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class ReceiptWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtCompeticion;
	private JTextField txtCategoria;
	private JTextField txtFecha;
	private JTextField txtCantidad;

	private CompetitionRegisterWindow competitionRegisterWindow = null;
	private Application app = null;
	private Justificante just = null;

	/**
	 * Create the dialog.
	 * 
	 * @param auxJust
	 */
	public ReceiptWindow(CompetitionRegisterWindow competitionRegisterWindow, Application app, Justificante auxJust) {
		this.competitionRegisterWindow = competitionRegisterWindow;
		this.app = app;
		this.just = auxJust;

		setBounds(100, 100, 513, 436);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnReceipt = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnReceipt.getLayout();
			flowLayout.setAlignment(FlowLayout.LEADING);
			contentPanel.add(pnReceipt, BorderLayout.NORTH);
			{
				JLabel lblRecibo = new JLabel("Recibo:");
				lblRecibo.setFont(new Font("Segoe UI Light", Font.PLAIN, 20));
				pnReceipt.add(lblRecibo);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			{
				JPanel pnLabels = new JPanel();
				panel.add(pnLabels);
				pnLabels.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblNombre = new JLabel("Nombre:");
					lblNombre.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
					pnLabels.add(lblNombre);
				}
				{
					JLabel lblCompeticin = new JLabel("Competici\u00F3n:");
					lblCompeticin.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
					pnLabels.add(lblCompeticin);
				}
				{
					JLabel lblCategora = new JLabel("Categor\u00EDa:");
					lblCategora.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
					pnLabels.add(lblCategora);
				}
				{
					JLabel lblFechaDeInscripcin = new JLabel("Fecha de inscripci\u00F3n:");
					lblFechaDeInscripcin.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
					pnLabels.add(lblFechaDeInscripcin);
				}
				{
					JLabel lblCantidadAbonada = new JLabel("Cantidad abonada:");
					lblCantidadAbonada.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
					pnLabels.add(lblCantidadAbonada);
				}
			}
			{
				JPanel pnData = new JPanel();
				panel.add(pnData);
				pnData.setLayout(new GridLayout(0, 1, 0, 0));
				{
					txtName = new JTextField();
					txtName.setEditable(false);
					pnData.add(txtName);
					txtName.setColumns(10);
					txtName.setText(just.getNombre());
				}
				{
					txtCompeticion = new JTextField();
					txtCompeticion.setEditable(false);
					pnData.add(txtCompeticion);
					txtCompeticion.setColumns(10);
					txtCompeticion.setText(just.getIdCompeticion());
				}
				{
					txtCategoria = new JTextField();
					txtCategoria.setEditable(false);
					pnData.add(txtCategoria);
					txtCategoria.setColumns(10);
					txtCategoria.setText(just.getCategoria());
				}
				{
					txtFecha = new JTextField();
					txtFecha.setEditable(false);
					pnData.add(txtFecha);
					txtFecha.setColumns(10);
					txtFecha.setText(just.getFecha());
				}
				{
					txtCantidad = new JTextField();
					txtCantidad.setEditable(false);
					pnData.add(txtCantidad);
					txtCantidad.setColumns(10);
					txtCantidad.setText(String.valueOf(just.getAmount()));
				}
			}
			{
				JPanel pnEmpty = new JPanel();
				panel.add(pnEmpty);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
