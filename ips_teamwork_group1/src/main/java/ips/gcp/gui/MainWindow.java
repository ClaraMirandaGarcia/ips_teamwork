package ips.gcp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ips.gcp.jdbc.Database;
import ips.gcp.logic.Application;

public class MainWindow {

	private JFrame frame;
	private Database db = null;
	private Application app = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initDatabase() {
		db = new Database();
		db.createDatabase(true);
		db.loadDatabase();
	}

}
