package frsf.cidisi.exercise.tp2.situationCalculus;

import javax.swing.UIManager;

import frsf.cidisi.exercise.interfaz2.DosPuntoCero;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SituationCalculusBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;

public class RonlyAgentMain {

    /**
     * @param args
     * @throws PrologConnectorException
     */
    public static void main(String[] args) throws PrologConnectorException {
        
        RonlyAgent agent = new RonlyAgent();
        LaberintosAmbiente environment = new LaberintosAmbiente();
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        DosPuntoCero pp = new DosPuntoCero();
        
        pp.setEstadoAmbiente(environment.getEstado());
        pp.setEstadoRonly(agent.getAgentState());
        
        SimulatorEventNotifier.SubscribeEventHandler(EventType.IterationFinished, pp);

        pp.setVisible(true);
        
        SituationCalculusBasedAgentSimulator simulator =
                new SituationCalculusBasedAgentSimulator(environment, agent);

        simulator.start();
    }
}
