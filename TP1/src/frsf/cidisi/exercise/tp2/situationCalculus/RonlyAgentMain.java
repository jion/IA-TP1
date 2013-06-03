package frsf.cidisi.exercise.tp2.situationCalculus;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SituationCalculusBasedAgentSimulator;

public class RonlyAgentMain {

    /**
     * @param args
     * @throws PrologConnectorException
     */
    public static void main(String[] args) throws PrologConnectorException {
        
        RonlyAgent agent = new RonlyAgent();
        LaberintosAmbiente environment = new LaberintosAmbiente();

        SituationCalculusBasedAgentSimulator simulator =
                new SituationCalculusBasedAgentSimulator(environment, agent);

        simulator.start();
    }
}
