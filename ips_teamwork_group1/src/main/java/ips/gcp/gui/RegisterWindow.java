package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ips.gcp.logic.Application;
import ips.gcp.logic.dto.AtletaDTO;
import ips.gcp.logic.util.FileUtil;

/**
 * @author Lucia
 */
public class RegisterWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JLabel lblEmail;
	private JLabel lblDni;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtDNI;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox comboBoxDia;
	private JComboBox comboBoxMes;
	private JComboBox comboBoxYear;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	
	private Application app = null;
	private MainWindow mainWindow = null;

	/**
	 * Create the dialog.
	 */
	public RegisterWindow(MainWindow parent, Application app) {
		this.mainWindow = parent;
		this.app = app;
		
		setTitle("Registro");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblNombre());
		contentPanel.add(getLblEmail());
		contentPanel.add(getLblDni());
		{
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(10, 152, 78, 25);
			contentPanel.add(lblSexo);
		}
		{
			JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
			lblFechaDeNacimiento.setBounds(10, 192, 121, 25);
			contentPanel.add(lblFechaDeNacimiento);
		}
		contentPanel.add(getTxtNombre());
		contentPanel.add(getTxtEmail());
		contentPanel.add(getTxtDNI());
		contentPanel.add(getRdbtnHombre());
		contentPanel.add(getRdbtnMujer());
		contentPanel.add(getComboBoxDia());
		contentPanel.add(getComboBoxMes());
		contentPanel.add(getcomboBoxYear());
		contentPanel.add(getLblApellidos());
		contentPanel.add(getTxtApellidos());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if ((!txtNombre.getText().isEmpty()) && (!txtEmail.getText().isEmpty())
								&& (!txtDNI.getText().isEmpty()) && (!txtApellidos.getText().isEmpty())) {
							createAtleta();
						} else {
							JOptionPane.showMessageDialog(null, "All textfields must be filled");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 11, 78, 25);
		}
		return lblNombre;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("E-mail:");
			lblEmail.setBounds(10, 83, 78, 25);
		}
		return lblEmail;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(10, 119, 78, 25);
		}
		return lblDni;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(69, 13, 131, 20);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}

	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setBounds(47, 85, 131, 20);
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}

	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setBounds(47, 121, 131, 20);
			txtDNI.setColumns(10);
		}
		return txtDNI;
	}

	private JRadioButton getRdbtnHombre() {
		if (rdbtnHombre == null) {
			rdbtnHombre = new JRadioButton("Hombre");
			rdbtnHombre.setSelected(true);
			buttonGroup.add(rdbtnHombre);
			rdbtnHombre.setBounds(47, 153, 65, 23);
		}
		return rdbtnHombre;
	}

	private JRadioButton getRdbtnMujer() {
		if (rdbtnMujer == null) {
			rdbtnMujer = new JRadioButton("Mujer");
			buttonGroup.add(rdbtnMujer);
			rdbtnMujer.setBounds(135, 153, 65, 23);
		}
		return rdbtnMujer;
	}

	private JComboBox getComboBoxDia() {
		if (comboBoxDia == null) {
			comboBoxDia = new JComboBox();
			for (int j = 1; j < 32; j++) {
				comboBoxDia.addItem(j);
			}
			comboBoxDia.setBounds(147, 194, 53, 20);
		}
		return comboBoxDia;
	}

	private JComboBox getComboBoxMes() {
		if (comboBoxMes == null) {
			comboBoxMes = new JComboBox();
			comboBoxMes.setBounds(210, 194, 84, 20);
			comboBoxMes.setModel(new DefaultComboBoxModel<Object>(new String[] { "01 ", "02", "03",
					"04", "05", "06", "07", "08", "09 ", "10", "11", "12" }));
		}
		return comboBoxMes;
	}

	private JComboBox getcomboBoxYear() {
		if (comboBoxYear == null) {
			comboBoxYear = new JComboBox();
			for (int j = 1910; j < 2019; j++) {
				comboBoxYear.addItem(j);
			}
			comboBoxYear.setBounds(304, 194, 70, 20);
		}
		return comboBoxYear;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(10, 47, 78, 25);
		}
		return lblApellidos;
	}

	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setBounds(69, 47, 131, 20);
			txtApellidos.setColumns(10);
		}
		return txtApellidos;
	}

	private void createAtleta() {
		String name = txtNombre.getText();
		name = name + " " + txtApellidos.getText();
		String email = txtEmail.getText();
		String dni = txtDNI.getText();
		String sexo;
		if (rdbtnHombre.isSelected()) {
			sexo = "Hombre";
		} else {
			sexo = "Mujer";
		}
		String fecha = comboBoxDia.getSelectedItem().toString() + "-" + comboBoxMes.getSelectedItem().toString() + "-"
				+ comboBoxYear.getSelectedItem().toString();
		
		AtletaDTO a = new AtletaDTO();
		a.setNombre(name);
		a.setDni(dni);
		a.setEmail(email);
		a.setSexo(sexo);
		a.setFechaNacimiento(fecha);
		
		new Application().addAtleta(a);
		
		FileUtil.saveUserFile(a);
	}
}
