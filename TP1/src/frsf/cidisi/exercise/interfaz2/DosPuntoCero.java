package frsf.cidisi.exercise.interfaz2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import frsf.cidisi.exercise.tp2.situationCalculus.LaberintosEstado;
import frsf.cidisi.exercise.tp2.situationCalculus.RonlyEstado;
import frsf.cidisi.faia.simulator.events.EventHandler;
import frsf.cidisi.faia.simulator.events.EventType;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DosPuntoCero extends JFrame implements EventHandler {

	private JPanel contentPane;
	private PanelLaberinto pl;
	private LaberintosEstado estadoAmbiente;
	private RonlyEstado estadoRonly;
	
	// Etiquetas de Estado del agente
	JLabel lblNivel;
	JLabel lblSituacion;
	JLabel lblPosicion;
	JLabel lblOrientacion;
	JLabel lblTengoLlave;
	JLabel lblCantPasadas;
	JSpinner spinner;
	

	public void setEstadoAmbiente(LaberintosEstado estadoAmbiente) {
		this.estadoAmbiente = estadoAmbiente;
	}

	public void setEstadoRonly(RonlyEstado estadoRonly) {
		this.estadoRonly = estadoRonly;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DosPuntoCero frame = new DosPuntoCero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DosPuntoCero() {
		setTitle("Trabajo Práctico N°2 - Inteligencia Artificial - Ronly v2.0");
		this.setIconImage((new ImageIcon("images/ronly.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600,600));
		this.setResizable(false);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(2, 2));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNivel = new JLabel("Estrategia:");
		panel.add(lblNivel);
		
		JLabel lblX = new JLabel("C\u00E1lculo Situacional");
		panel.add(lblX);
		
		pl = new PanelLaberinto();
		contentPane.add(pl, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {2, 80};
		gbl_panel_2.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_8 = new JLabel("Estado del agente");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_8.gridwidth = 2;
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 0;
		panel_2.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JLabel lblNewLabel = new JLabel("Nivel:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		this.lblNivel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_2.add(this.lblNivel, gbc_lblNewLabel_1);
		
		JLabel lblSituacion = new JLabel("Situacion:");
		lblSituacion.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblSituacion = new GridBagConstraints();
		gbc_lblSituacion.anchor = GridBagConstraints.WEST;
		gbc_lblSituacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSituacion.gridx = 0;
		gbc_lblSituacion.gridy = 2;
		panel_2.add(lblSituacion, gbc_lblSituacion);
		
		this.lblSituacion = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 2;
		panel_2.add(this.lblSituacion, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Posici\u00F3n:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		lblPosicion = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 3;
		panel_2.add(lblPosicion, gbc_lblNewLabel_4);
		
		JLabel lblOrientacin = new JLabel("Orientaci\u00F3n:");
		lblOrientacin.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblOrientacin = new GridBagConstraints();
		gbc_lblOrientacin.anchor = GridBagConstraints.WEST;
		gbc_lblOrientacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrientacin.gridx = 0;
		gbc_lblOrientacin.gridy = 4;
		panel_2.add(lblOrientacin, gbc_lblOrientacin);
		
		lblOrientacion = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 4;
		panel_2.add(lblOrientacion, gbc_lblNewLabel_5);
		
		JLabel lblTengoLlave = new JLabel("Tengo llave?:");
		lblTengoLlave.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblTengoLlave = new GridBagConstraints();
		gbc_lblTengoLlave.anchor = GridBagConstraints.WEST;
		gbc_lblTengoLlave.insets = new Insets(0, 0, 5, 5);
		gbc_lblTengoLlave.gridx = 0;
		gbc_lblTengoLlave.gridy = 5;
		panel_2.add(lblTengoLlave, gbc_lblTengoLlave);
		
		this.lblTengoLlave = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 5;
		panel_2.add(this.lblTengoLlave, gbc_lblNewLabel_6);
		
		JLabel lblCantDeVeces = new JLabel("Cant. de pasadas:");
		lblCantDeVeces.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblCantDeVeces = new GridBagConstraints();
		gbc_lblCantDeVeces.anchor = GridBagConstraints.WEST;
		gbc_lblCantDeVeces.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantDeVeces.gridx = 0;
		gbc_lblCantDeVeces.gridy = 6;
		panel_2.add(lblCantDeVeces, gbc_lblCantDeVeces);
		
		this.lblCantPasadas = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 6;
		panel_2.add(this.lblCantPasadas, gbc_lblNewLabel_7);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 7;
		panel_2.add(separator, gbc_separator);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(300, 0, 2000, 1));
		spinner.setValue(pl.getSleepTime());
		spinner.addChangeListener(
                new ChangeListener(){
                   public void stateChanged(ChangeEvent e) {
                	   pl.setSleepTime((Integer) spinner.getModel().getValue());

                   }
                }
        );
		
		JLabel lblVelocidad = new JLabel("Velocidad:");
		GridBagConstraints gbc_lblVelocidad = new GridBagConstraints();
		gbc_lblVelocidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblVelocidad.gridx = 0;
		gbc_lblVelocidad.gridy = 9;
		panel_2.add(lblVelocidad, gbc_lblVelocidad);
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 9;
		panel_2.add(spinner, gbc_spinner);
		
		JCheckBox checkBoxPasos = new JCheckBox("Mostrar cuenta-pasos");
		checkBoxPasos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JCheckBox cb = (JCheckBox)arg0.getSource();
				pl.setShowPasos(cb.isSelected());
			}
		});
		GridBagConstraints gbc_CheckBoxPasos = new GridBagConstraints();
		gbc_CheckBoxPasos.anchor = GridBagConstraints.WEST;
		gbc_CheckBoxPasos.insets = new Insets(0, 0, 5, 0);
		gbc_CheckBoxPasos.gridwidth = 2;
		gbc_CheckBoxPasos.gridx = 0;
		gbc_CheckBoxPasos.gridy = 10;
		panel_2.add(checkBoxPasos, gbc_CheckBoxPasos);
		
		JCheckBox chckbxMostrarMigas = new JCheckBox("Mostrar migas");
		chckbxMostrarMigas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JCheckBox cb = (JCheckBox)arg0.getSource();
				pl.setShowMigas(cb.isSelected());
			}
		});
		GridBagConstraints gbc_chckbxMostrarMigas = new GridBagConstraints();
		gbc_chckbxMostrarMigas.anchor = GridBagConstraints.WEST;
		gbc_chckbxMostrarMigas.gridwidth = 2;
		gbc_chckbxMostrarMigas.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxMostrarMigas.gridx = 0;
		gbc_chckbxMostrarMigas.gridy = 11;
		panel_2.add(chckbxMostrarMigas, gbc_chckbxMostrarMigas);
	}

	@Override
	public void runEventHandler(EventType evenType, Object[] params) {
    	if(estadoAmbiente!=null && estadoRonly != null) {

			this.lblNivel.setText( estadoRonly.getLevel().toString() );
			this.lblSituacion.setText( estadoRonly.getSituation().toString());
			this.lblPosicion.setText( estadoRonly.getPosicion().toString() );
			this.lblOrientacion.setText( estadoRonly.getOrientacion().toString() );
			this.lblTengoLlave.setText( estadoRonly.tieneLlave().toString() );
			this.lblCantPasadas.setText( estadoRonly.getCantPasadas().toString() );
			
			pl.inicializarPercepcion(estadoAmbiente,estadoRonly);

	    	this.repaint();
    	}
	}

}
