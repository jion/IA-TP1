package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.exercise.tp1.interfaz.PantallaPrincipal;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;

public class RonlyAgentMain {

    public static void main(String[] args) throws PrologConnectorException {
        RonlyAgent agent = new RonlyAgent();

        LaberintosAmbiente environment = new LaberintosAmbiente();
        PantallaPrincipal pp = new PantallaPrincipal();
        
        SimulatorEventNotifier.SubscribeEventHandler(EventType.IterationFinished, pp);
        SimulatorEventNotifier.SubscribeEventHandler(EventType.PerceptionRecived, pp);
        
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
