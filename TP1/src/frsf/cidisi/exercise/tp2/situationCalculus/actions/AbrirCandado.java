package frsf.cidisi.exercise.tp2.situationCalculus.actions;

import frsf.cidisi.exercise.datastructures.Laberinto;
import frsf.cidisi.exercise.tp2.situationCalculus.LaberintosEstado;
import frsf.cidisi.exercise.tp2.situationCalculus.RonlyEstado;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class AbrirCandado extends SituationCalculusAction {

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    	LaberintosEstado estado = (LaberintosEstado) est;
    	
    	Laberinto lab = estado.getLaberintoActual();
    	int row = estado.getPosRonly().getFirst();
    	int col = estado.getPosRonly().getSecond();
    	
    	if(estado.getRonly().tieneLlave() &&
    			lab.consulta(Laberinto.ES_SALIDA, row, col))
    	{
    		estado.abrirCandado(row, col);
    	}
    	
		return estado;
	}

	@Override
	public String toString() {
		return "abrirCandado";
	}

}
