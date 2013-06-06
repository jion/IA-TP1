package frsf.cidisi.exercise.tp2.situationCalculus.actions;

import frsf.cidisi.exercise.datastructures.Laberinto;
import frsf.cidisi.exercise.tp2.situationCalculus.LaberintosEstado;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class TomarLlave extends SituationCalculusAction {

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		LaberintosEstado estado = (LaberintosEstado) est;
    	
    	Laberinto lab = estado.getLaberintoActual();
    	int row = estado.getPosRonly().getFirst();
    	int col = estado.getPosRonly().getSecond();
    	
    	if(estado.getRonly().tieneLlave() &&
    			lab.consulta(Laberinto.HAY_LLAVE, row, col))
    	{
    		estado.tomarLlave(row, col);
    	}
    	
		return estado;
	}

	@Override
	public String toString() {
		return "TomarLlave";
	}

}
