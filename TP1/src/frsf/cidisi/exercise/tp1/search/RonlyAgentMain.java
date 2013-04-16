package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class RonlyAgentMain {

    public static void main(String[] args) throws PrologConnectorException {
        RonlyAgent agent = new RonlyAgent();

        LaberintosAmbiente environment = new LaberintosAmbiente();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
