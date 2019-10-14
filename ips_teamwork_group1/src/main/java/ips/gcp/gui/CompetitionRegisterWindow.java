package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class CompetitionRegisterWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JLabel lblIntroduzcaLosSiguientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CompetitionRegisterWindow dialog = new CompetitionRegisterWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CompetitionRegisterWindow() {
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
				lblIntroduzcaLosSiguientes.setFont(new Font("Segoe UI Light", Font.PLAIN, 22));
			}
			{
				txtEmail = new JTextField();
				lblIntroduzcaLosSiguientes.setLabelFor(txtEmail);
				pnCenter.add(txtEmail);
				txtEmail.setColumns(10);
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
