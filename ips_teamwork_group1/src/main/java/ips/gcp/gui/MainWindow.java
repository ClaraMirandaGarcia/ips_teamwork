package ips.gcp.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ips.gcp.jdbc.Database;
import ips.gcp.logic.Application;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	
	// CardLayout Code
	private final static String WELCOME = "pnChoiceInit";
	private final static String USUARIO = "pnUsuario";
	
	// Logic Code
	private Database db = null;
	private Application app;
	
	// Gui Windows Code
	private UserWindow userWindow = null;
	private RegisterWindow registerWindow = null;
	
	private JPanel pnChoiceInit;
	private JPanel pnBaseLbl;
	private JLabel lblBienvenido;
	private JPanel pnButtonsUserOrg;
	private JButton btnUsuario;
	private JButton btnOrganizador;
	private JPanel pnUsuario;
	private JPanel pnBaseSelect;
	private JLabel lblPorFavorSeleccione;
	private JPanel pnButtonsRegMostrar;
	private JButton btnRegistrarme;
	private JButton btnMostrarCompeticiones;
	private JPanel pnAtras;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	##################################
//			MÉTODOS PRIVADOS
//	##################################
	private void initDatabase() {
		db = new Database();
		db.createDatabase(true);
		
		/*
		 * Pendiente: mover el uso de este método a un botón en la ventana de 
		 * administrador para conseguir la persistencia de la database.
		 */
		db.loadDatabase(); 
	}
	
	public void createUserWindow() {
		this.userWindow = new UserWindow(this, app);
		this.userWindow.setLocationRelativeTo(this);
		this.userWindow.setModal(true);
		this.userWindow.setVisible(true);
	}
	
	public void createRegisterWindow() {
		this.registerWindow = new RegisterWindow(this, app);
		this.registerWindow.setLocationRelativeTo(this);
		this.registerWindow.setModal(true);
		this.registerWindow.setVisible(true);
	}
	
	// Shows the pane corresponding to the code
	// Also, sets the default button of each of the panes
	public void showPane(String paneStringCode) {
		((CardLayout)contentPane.getLayout()).show(contentPane, paneStringCode);
		
		if(paneStringCode.equals(WELCOME)) {
			this.getRootPane().setDefaultButton(btnUsuario);
		} else {
			if(paneStringCode.equals(USUARIO)) {
				this.getRootPane().setDefaultButton(btnRegistrarme);
			} 
		}
	}


//	##################################
//			WINDOW MAKER CODE
//	##################################

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Gestor de Carreras Populares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnChoiceInit(), WELCOME);
		contentPane.add(getPnUsuario(), USUARIO);
		
		initDatabase();
		app = new Application();
	}

	private JPanel getPnChoiceInit() {
		if (pnChoiceInit == null) {
			pnChoiceInit = new JPanel();
			pnChoiceInit.setBackground(Color.WHITE);
			pnChoiceInit.setLayout(new BorderLayout(0, 0));
			pnChoiceInit.add(getPnBaseLbl(), BorderLayout.CENTER);
			pnChoiceInit.add(getPnButtonsUserOrg(), BorderLayout.SOUTH);
		}
		return pnChoiceInit;
	}
	private JPanel getPnBaseLbl() {
		if (pnBaseLbl == null) {
			pnBaseLbl = new JPanel();
			pnBaseLbl.setBackground(Color.WHITE);
			pnBaseLbl.setLayout(new BorderLayout(0, 0));
			pnBaseLbl.add(getLblBienvenido(), BorderLayout.CENTER);
		}
		return pnBaseLbl;
	}
	private JLabel getLblBienvenido() {
		if (lblBienvenido == null) {
			lblBienvenido = new JLabel("Bienvenido");
			lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
			lblBienvenido.setFont(new Font("Segoe UI Light", Font.PLAIN, 50));
		}
		return lblBienvenido;
	}
	private JPanel getPnButtonsUserOrg() {
		if (pnButtonsUserOrg == null) {
			pnButtonsUserOrg = new JPanel();
			pnButtonsUserOrg.add(getBtnUsuario());
			pnButtonsUserOrg.add(getBtnOrganizador());
		}
		return pnButtonsUserOrg;
	}
	private JButton getBtnUsuario() {
		if (btnUsuario == null) {
			btnUsuario = new JButton("Usuario");
			btnUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showPane(USUARIO);
				}
			});
		}
		return btnUsuario;
	}
	private JButton getBtnOrganizador() {
		if (btnOrganizador == null) {
			btnOrganizador = new JButton("Organizador");
		}
		return btnOrganizador;
	}
	private JPanel getPnUsuario() {
		if (pnUsuario == null) {
			pnUsuario = new JPanel();
			pnUsuario.setBackground(Color.WHITE);
			pnUsuario.setLayout(new BorderLayout(0, 0));
			pnUsuario.add(getPnBaseSelect(), BorderLayout.CENTER);
			pnUsuario.add(getPnButtonsRegMostrar(), BorderLayout.SOUTH);
			pnUsuario.add(getPnAtras(), BorderLayout.NORTH);
		}
		return pnUsuario;
	}
	private JPanel getPnBaseSelect() {
		if (pnBaseSelect == null) {
			pnBaseSelect = new JPanel();
			pnBaseSelect.setBackground(Color.WHITE);
			pnBaseSelect.setLayout(new BorderLayout(0, 0));
			pnBaseSelect.add(getLblPorFavorSeleccione());
		}
		return pnBaseSelect;
	}
	private JLabel getLblPorFavorSeleccione() {
		if (lblPorFavorSeleccione == null) {
			lblPorFavorSeleccione = new JLabel("Por favor, seleccione una opci\u00F3n:");
			lblPorFavorSeleccione.setHorizontalAlignment(SwingConstants.CENTER);
			lblPorFavorSeleccione.setBackground(Color.WHITE);
			lblPorFavorSeleccione.setFont(new Font("Segoe UI Light", Font.PLAIN, 30));
		}
		return lblPorFavorSeleccione;
	}
	private JPanel getPnButtonsRegMostrar() {
		if (pnButtonsRegMostrar == null) {
			pnButtonsRegMostrar = new JPanel();
			pnButtonsRegMostrar.add(getBtnRegistrarme());
			pnButtonsRegMostrar.add(getBtnMostrarCompeticiones());
		}
		return pnButtonsRegMostrar;
	}
	private JButton getBtnRegistrarme() {
		if (btnRegistrarme == null) {
			btnRegistrarme = new JButton("Registrarme");
			btnRegistrarme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createRegisterWindow();
				}
			});
		}
		return btnRegistrarme;
	}
	private JButton getBtnMostrarCompeticiones() {
		if (btnMostrarCompeticiones == null) {
			btnMostrarCompeticiones = new JButton("Mostrar competiciones");
			btnMostrarCompeticiones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createUserWindow();
				}
			});
		}
		return btnMostrarCompeticiones;
	}
	private JPanel getPnAtras() {
		if (pnAtras == null) {
			pnAtras = new JPanel();
			pnAtras.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) pnAtras.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnAtras.add(getBtnVolver());
		}
		return pnAtras;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showPane(WELCOME);
				}
			});
			btnVolver.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnVolver;
	}
}
