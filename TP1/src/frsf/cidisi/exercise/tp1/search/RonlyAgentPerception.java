package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.exercise.tp1.datastructures.Laberinto;
import frsf.cidisi.exercise.tp1.datastructures.PairInt;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class RonlyAgentPerception extends Perception {

	/* Sensores:
	 * 	- laberinto:   El laberinto percibido
	 *  - posInicial:  Posicion inicial del agente
	 *  - ultimoNivel: true si se trata del último nivel (no hay mas niveles)
	 */
	private Laberinto	laberinto;
	private PairInt 	posInicial;
	private boolean		ultimoNivel;

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

    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        
        if(getUltimoNivel())
        	{ str.append("Ultimo laberinto"); }
        else
        	{ str.append("Laberinto Intermedio"); }
        
        return str.toString();
    }

    // The following methods are agent-specific:

    /* Getters & Setters *****************************************************/	
	public Laberinto getLaberinto(){
		return laberinto;
	}

	public void setLaberinto(Laberinto percepcionLaberinto){
		this.laberinto = percepcionLaberinto;
	}
	
	public PairInt getPosInicial() {
		return posInicial;
	}

	public void setPosInicial(PairInt posInicial) {
		this.posInicial = posInicial;
	}

	public Boolean getUltimoNivel() {
		return ultimoNivel;
	}
	
	public void setUltimoNivel(Boolean ultimoNivel) {
		this.ultimoNivel = ultimoNivel;
	}

}