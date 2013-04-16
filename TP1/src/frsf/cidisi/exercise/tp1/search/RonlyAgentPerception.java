package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class RonlyAgentPerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	/* Sensores:
	 * 	- percepcionLaberinto: El laberinto actual
	 *  - won: true si se ha llegado a la meta final
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
    	
    	//TODO: Aca deberia actiualizarse el estado del agente si llego a la meta?
        //RonlyAgent agent = (RonlyAgent) agentIn;
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

        //TODO: Complete Method
        return this.percepcionLaberinto.toString();//str.toString();
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