package frsf.cidisi.exercise.tp2.situationCalculus;

import frsf.cidisi.exercise.tp1.datastructures.Laberinto;
import frsf.cidisi.exercise.tp1.search.LaberintosEstado;
import frsf.cidisi.exercise.tp1.search.RonlyAgentPerception;
import frsf.cidisi.faia.agent.Action;

import java.util.List;
import java.util.Vector;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.environment.Environment;


public class LaberintosAmbiente extends Environment {

    public LaberintosAmbiente() {
        this.environmentState = new LaberintosEstado();
    }

    @Override
    public LaberintosEstado getEnvironmentState() {
        return (LaberintosEstado) super.getEnvironmentState();
    }

    @Override
    public boolean agentFailed(List<? extends Action> actions) {

        // TODO: Completar metodo. El agente puede fallar en este problema?? 

        return false;
    }

    @Override
    public RonlyAgentPerception getPercept() {
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

    /* Getters & Setters *****************************************************/
    public Laberinto getLaberintoActual() {
    	return ((LaberintosEstado) environmentState).getLaberintoActual();
    }
    
    @Override
    public String toString() {
        return this.environmentState.toString();
    }
}
