package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.datastructure.Pair;

public class RonlyAgentPerception extends Perception {

	/* Sensores:
	 * 	- percepcionLaberinto: El laberinto actual
	 *  - won: true si se ha llegado a la meta final (no hay mas niveles)
	 */
	private Laberinto percepcionLaberinto;
	private Pair<Integer, Integer> posInicial;
	private Boolean won;

	public  RonlyAgentPerception() {
    	super();
    }

    public RonlyAgentPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {

        LaberintosAmbiente environment = (LaberintosAmbiente) environmentIn;
        LaberintosEstado environmentState =
                environment.getEnvironmentState();
       
        // Se percibe del ambiente un laberinto entero a la vez
        // (El siguente nivel no resuelto). Se setea a null si
        // ya no quedan niveles por completar
        this.percepcionLaberinto = environmentState.getLaberintoActual();
        
        // true: No quedan mas laberintos. (Ganaste!)
        // false: La princesa está en el otro castillo...
        this.won = environmentState.isFinished();
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        
        if(getWon())
        	{ str.append("Fin del juego!"); }
        else
        	{ str.append("Nuevo laberinto"); }
        
        return str.toString();
    }

    // The following methods are agent-specific:

    /* Getters & Setters *****************************************************/	
	public Laberinto getPercepcionLaberinto(){
		return percepcionLaberinto;
	}

	public void setPercepcionLaberinto(Laberinto percepcionLaberinto){
		this.percepcionLaberinto = percepcionLaberinto;
	}
	
	public Pair<Integer, Integer> getPosInicial() {
		return posInicial;
	}

	public void setPosInicial(Pair<Integer, Integer> posInicial) {
		this.posInicial = posInicial;
	}

	public Boolean getWon() {
		return won;
	}
	
	public void setWon(Boolean won) {
		this.won = won;
	}

}