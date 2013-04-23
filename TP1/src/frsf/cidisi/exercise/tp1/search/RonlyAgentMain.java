package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.exercise.tp1.gui.LaberintosGui;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;

public class RonlyAgentMain {

    public static void main(String[] args) throws PrologConnectorException {
        RonlyAgent agent = new RonlyAgent();

        LaberintosAmbiente environment = new LaberintosAmbiente();
        
        SimulatorEventNotifier.SubscribeEventHandler(EventType.IterationFinished, LaberintosGui.getInstance());
        
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
