package frsf.cidisi.exercise.tp2.situationCalculus.actions;

import frsf.cidisi.exercise.tp2.situationCalculus.LaberintosEstado;
import frsf.cidisi.exercise.tp2.situationCalculus.RonlyEstado;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Salir extends SituationCalculusAction {

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    	LaberintosEstado estado = (LaberintosEstado) est;
    	RonlyEstado agState = ((RonlyEstado) ast);
    	
        // PreConditions: Estar en la posicion de salida, mirando al este.
        // PostConditions: Pasar al siguiente nivel.
    	if(estado.isRonlyOnExit()) { estado.pasarNivel(); }
    	
    	//TODO: Sacar de aca!!!
    	//agState.advanceToNextLevel();
    	
		return estado;
	}

	@Override
	public String toString() {
		return "salir";
	}

}
