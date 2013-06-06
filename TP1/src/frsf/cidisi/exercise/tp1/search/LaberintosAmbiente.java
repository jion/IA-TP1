package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.exercise.datastructures.Laberinto;
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
    	
    	/* Si Ronly se encuentra en la posicion de salida, quiere decir
    	 * que encontro la salida para el nivel anterior, y por lo tanto
    	 * pasamos de nivel para percibir el siguiente.
    	 * Caso contrario, percibirá nuevamente el nivel actual, en la
    	 * posicion donde se ha quedado, para que termine de resolver el
    	 * nivel (Esto último en realidad nunca pasa en este modelo, dado
    	 * que este if siempre va a dar true porque siempre se percibe
    	 * luego de resolver cada nivel)
    	 */
    	if(estado.isRonlyOnExit()) { estado.pasarNivel(); }
    	
		// Se crea una nueva percepcion
		RonlyAgentPerception perception = new RonlyAgentPerception();
		
        // Se percibe del ambiente un laberinto entero a la vez
        // (El siguente nivel no resuelto).
		perception.setLaberinto(this.getLaberintoActual());
		
        // Se percibe la posicion inicial de Ronly
		perception.setPosInicial(estado.getPosRonly().clone());
		
        // true: No quedan mas laberintos. (Is the FINAL COUNTDOWN!)
        // false: La princesa está en el otro castillo...
		perception.setUltimoNivel(estado.isUltimoNivel());

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
    public Laberinto getLaberintoActual() {
    	return ((LaberintosEstado) environmentState).getLaberintoActual();
    }
}
