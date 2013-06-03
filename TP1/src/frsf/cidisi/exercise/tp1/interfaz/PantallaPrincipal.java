package frsf.cidisi.exercise.tp1.interfaz;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import frsf.cidisi.exercise.tp1.search.RonlyAgentPerception;
import frsf.cidisi.faia.simulator.events.EventHandler;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;

 
public class PantallaPrincipal  extends JFrame implements EventHandler {
    public static boolean RIGHT_TO_LEFT = false;

 
	private JLabel nivelLabel;
    int nivel=0;
    RonlyAgentPerception percepcion = null;
    
    @Override
	public void paint(Graphics g) {
		super.paint(g);
		nivelLabel.setText("Nivel: " + nivel);
	}
    
    public void addComponentsToPane(Container pane) {
       
        if (!(pane.getLayout() instanceof BorderLayout)) {
            pane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }
       
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                    java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }
        
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        
        Container north = rightFrame();
		
        Container center = leftFrame();//leftFrame();
        center.setPreferredSize(new Dimension(480,480));

        
        listPane.add(north);
        listPane.add(center);
        pane.add(listPane, BorderLayout.CENTER);
 
    }
    private Container rightFrame() {
		Container cp = new JPanel();
		nivelLabel = new JLabel("Nivel: " + nivel);
		nivelLabel.setFont(new Font ("PassingNotes", 0 , 24));
		cp.setLayout(new BorderLayout());
		cp.add(nivelLabel, BorderLayout.CENTER);
		
		return cp;
	}
    
    
	private Container topFrame() {
                Container cp = new JPanel();
                FlowLayout gl = new FlowLayout();
                gl.setHgap(5); gl.setVgap(5);
                cp.setLayout(gl);
               
                JButton button = new JButton(">");
        button.setPreferredSize(new Dimension(48, 48));
                cp.add(new JLabel("Estrategia: "));
                cp.add(new JComboBox<String>());
                cp.add(button);
 
                return cp;
    }
   
    private Container leftFrame() {
                PanelLaberinto pl = new PanelLaberinto();
           
                SimulatorEventNotifier.SubscribeEventHandler(EventType.PerceptionRecived, pl);
                SimulatorEventNotifier.SubscribeEventHandler(EventType.IterationFinished, pl);

                return pl;
    }
    
    
 
   
    private Container bottomFrame() {
                Container cp = new JPanel();
                FlowLayout gl = new FlowLayout();
                gl.setHgap(5); gl.setVgap(5);
                cp.setLayout(gl);
               
                JButton button = new JButton(">");
        button.setPreferredSize(new Dimension(48, 48));
                cp.add(new JLabel("Estrategia: "));
                cp.add(new JComboBox<String>());
                cp.add(button);
 
                return cp;
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private void createAndShowGUI() {
       
        //Create and set up the window.
        this.setTitle("Trabajo Práctico N°1 - Inteligencia Artificial - Ronly");
        this.setIconImage((new ImageIcon("images/ronly.png")).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(600,600));
        this.setResizable(false);
        
        //Set up the content pane.
        addComponentsToPane(this.getContentPane());
        pack();
        //Use the content pane's default BorderLayout. No need for
        //setLayout(new BorderLayout());
        //Display the window.
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
   
//    public static void main(String[] args) {
    public void lanzarGUI() {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        createAndShowGUI();

    }
        @Override
        public void runEventHandler(EventType evenType, Object[] params) {
        
        	switch(evenType) {
	            case PerceptionRecived: 
	        	percepcion = (RonlyAgentPerception) params[0]; 
	        	nivel++;
	        	this.repaint();
        	}
        }
}
