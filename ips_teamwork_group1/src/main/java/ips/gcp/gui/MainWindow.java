package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ips.gcp.jdbc.Database;
import ips.gcp.logic.Application;

public class MainWindow {

	private JFrame frmGestorDeCarreras;
	private Database db = null;
	private Application app;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmGestorDeCarreras.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		initDatabase();
		app = new Application();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestorDeCarreras = new JFrame();
		frmGestorDeCarreras.setTitle("Gestor de Carreras Populares");
		frmGestorDeCarreras.setBounds(100, 100, 800, 500);
		frmGestorDeCarreras.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnBase = new JPanel();
		pnBase.setBackground(Color.WHITE);
		frmGestorDeCarreras.getContentPane().add(pnBase, BorderLayout.CENTER);
		pnBase.setLayout(new GridLayout(1, 1, 0, 0));
		
		JLabel label = new JLabel("Welcome");
		pnBase.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 50));
		
		JPanel panel = new JPanel();
		frmGestorDeCarreras.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnUsuario = new JButton("Usuario");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ABRIR NUEVA VENTANA 'UserWindow'
			}
		});
		panel.add(btnUsuario);
		
		JButton btnOrganizador = new JButton("Organizador");
		btnOrganizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ABRIR NUEVA VENTANA 'AdminWindow'
			}
		});
		panel.add(btnOrganizador);
	}
	
	private void initDatabase() {
		db = new Database();
		db.createDatabase(true);
		
		/*
		 * Pendiente: mover el uso de este método a un botón en la ventana de 
		 * administrador para conseguir la persistencia de la database.
		 */
		db.loadDatabase(); 
	}

}
