package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.environment.Environment;

public class LaberintosAmbiente extends Environment {

    public LaberintosAmbiente() {
        // Create the environment state
        this.environmentState = new LaberintosEstado();
    }

    public LaberintosEstado getEnvironmentState() {
        return (LaberintosEstado) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public  RonlyAgentPerception getPercept() {
    	LaberintosEstado estado = (LaberintosEstado) environmentState;
    	
		// Se crea una nueva percepcion
		RonlyAgentPerception perception = new RonlyAgentPerception();
		
		// Se percibe del ambiente un laberinto entero a la vez
		// (El siguente nivel no resuelto). Se setea a null si
		// ya no quedan niveles por completar
		perception.setPercepcionLaberinto(this.getNextLevel());
		
		// Setup de la posicion inicial
		perception.setPosInicial(estado.getPosRonly());
		
        // true: No quedan mas laberintos. (Ganaste!)
        // false: La princesa está en el otro castillo...
		perception.setWon(estado.isFinished());

		// Return the perception
		return perception;
    }

    
    public String toString() {
        return environmentState.toString();
    }

    
    public boolean agentFailed(Action actionReturned) {

    	LaberintosEstado envState =
                this.getEnvironmentState();

        // TODO: Completar metodo. El agente puede fallar en este problema??       

        return false;
    }

	//TODO: Complete this section with agent-specific methods
    /* Getters & Setters *****************************************************/
    public int[][] getNextLevel() {
    	return ((LaberintosEstado) environmentState).getLaberintoActual();
    }
}
