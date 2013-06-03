package frsf.cidisi.exercise.tp2.situationCalculus;

import frsf.cidisi.exercise.tp1.datastructures.Laberinto;
import frsf.cidisi.exercise.tp1.datastructures.PairInt;
import frsf.cidisi.exercise.tp1.search.LaberintosAmbiente;
import frsf.cidisi.exercise.tp1.search.LaberintosEstado;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.environment.Environment;

public class RonlyAgentPerception extends SituationCalculusPerception {

	/* Sensores:
	 * 	- laberinto:   El laberinto percibido
	 *  - posInicial:  Posicion inicial del agente
	 *  - ultimoNivel: true si se trata del último nivel (no hay mas niveles)
	 */
	private Laberinto	laberinto;
	private PairInt 	posInicial;
	private boolean		ultimoNivel;
	
    public RonlyAgentPerception() {
        super();

		//TODO: Complete Method
    }

    @Override
    public void initPerception(Agent agent, Environment environmentIn) {

        LaberintosAmbiente environment = (LaberintosAmbiente) environmentIn;
        LaberintosEstado environmentState =
                environment.getEnvironmentState();

    }

    @Override
    public String toString() {
        StringBuffer perceptionString = new StringBuffer("perception([");

        //TODO: Complete Method

        return perceptionString.toString();
    }
    
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
