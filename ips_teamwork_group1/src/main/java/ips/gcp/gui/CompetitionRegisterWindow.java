package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ips.gcp.logic.Application;
import ips.gcp.logic.business.Justificante;
import ips.gcp.logic.dto.CompeticionDTO;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CompetitionRegisterWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JLabel lblIntroduzcaLosSiguientes;

	private Application app = null;
	private ReceiptWindow receiptWindow = null;
	private UserWindow userWindow = null;
	private CompetitionRegisterWindow crw = this;
	private List<CompeticionDTO> listCompetition = null;

	private DefaultComboBoxModel modelComboBox = null;
	
	public void createReceiptWindow(Justificante auxJust) {
		this.receiptWindow = new ReceiptWindow(this, app, auxJust);
		this.receiptWindow.setLocationRelativeTo(this);
		this.receiptWindow.setModal(true);
		this.receiptWindow.setVisible(true);
	}

	/**
	 * Create the dialog.
	 * 
	 * @param list
	 */
	public CompetitionRegisterWindow(UserWindow parent, Application app, List<CompeticionDTO> list) {
		this.userWindow = parent;
		this.app = app;
		this.listCompetition = list;

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnCenter = new JPanel();
			contentPanel.add(pnCenter, BorderLayout.CENTER);
			pnCenter.setLayout(new GridLayout(2, 1, 0, 0));
			{
				lblIntroduzcaLosSiguientes = new JLabel("Introduzca el email:");
				pnCenter.add(lblIntroduzcaLosSiguientes);
				lblIntroduzcaLosSiguientes.setBackground(Color.WHITE);
				lblIntroduzcaLosSiguientes.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
			}
			{
				txtEmail = new JTextField();
				lblIntroduzcaLosSiguientes.setLabelFor(txtEmail);
				pnCenter.add(txtEmail);
				txtEmail.setColumns(10);
			}
			{
				JLabel lblSeleccioneLaCompeticin = new JLabel("Seleccione la competici\u00F3n:");
				lblSeleccioneLaCompeticin.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
				pnCenter.add(lblSeleccioneLaCompeticin);
			}
			{
				JPanel pnComboBox = new JPanel();
				pnCenter.add(pnComboBox);
				pnComboBox.setLayout(new GridLayout(3, 1, 0, 0));
				{
					JPanel panel = new JPanel();
					pnComboBox.add(panel);
				}
				{
					JComboBox comboBox = new JComboBox();
					pnComboBox.add(comboBox);
					modelComboBox = new DefaultComboBoxModel(getCompetitions());
					comboBox.setModel(modelComboBox);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String email = txtEmail.getText();
						// this.app.solicitar(email, this.app.getCompeticionDTO());
						// Justificante aux = crw.app.solicitar(email, crw.app.get);
						String name = (String) modelComboBox.getSelectedItem();
						CompeticionDTO auxCompDto= null;
						for (CompeticionDTO competicionDTO : listCompetition) {
							if (name.equals(competicionDTO.getNombre())) {
								auxCompDto = competicionDTO;
							}
						}
						Justificante auxJust = crw.app.solicitar(email, auxCompDto);
						createReceiptWindow(auxJust);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private String[] getCompetitions() {
		String[] aux = new String[listCompetition.size()];
		for (int i = 0; i < listCompetition.size(); i++) {
			String nombreCombo = listCompetition.get(i).getNombre();
			aux[i] = nombreCombo;
		}
		return aux;
	}

}
