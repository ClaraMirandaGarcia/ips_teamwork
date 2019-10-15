package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ips.gcp.logic.Application;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompetitionRegisterWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JLabel lblIntroduzcaLosSiguientes;
	
	private Application app = null;
	private ReceiptWindow receiptWindow = null;
	private UserWindow userWindow = null;


	public void createReceiptWindow() {
		this.receiptWindow = new ReceiptWindow(this, app);
		this.receiptWindow.setLocationRelativeTo(this);
		this.receiptWindow.setModal(true);
		this.receiptWindow.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public CompetitionRegisterWindow(UserWindow parent, Application app) {
		this.userWindow = parent;
		this.app = app;
		
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String email = txtEmail.getText();
						//this.app.solicitar(email, this.app.getCompeticionDTO());
						createReceiptWindow();
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

}
