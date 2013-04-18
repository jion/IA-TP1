package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class RonlyAgentPerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;
	
	
	/* Sensores:
	 * 	- percepcionLaberinto: El laberinto actual
	 *  - won: true si se ha llegado a la meta final (no hay mas niveles)
	 */
	private int[][] percepcionLaberinto;
	private Boolean won;

	
	
	public  RonlyAgentPerception() {
    	//TODO: Complete Method
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
        // false: La princesa est� en el otro castillo...
        this.won = environmentState.isFinished();
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method
        if(getWon())
        	{ str.append("Fin del juego!"); }
        else
        	{ str.append("Nuevo laberinto"); }
        
        return str.toString();
    }

    // The following methods are agent-specific:

    /* Getters & Setters *****************************************************/	
	public int[][] getPercepcionLaberinto(){
		return percepcionLaberinto;
	}

	public void setPercepcionLaberinto(int[][] percepcionLaberinto){
		this.percepcionLaberinto = percepcionLaberinto;
	}
	
	public Boolean getWon() {
		return won;
	}
	
	public void setWon(Boolean won) {
		this.won = won;
	}

}