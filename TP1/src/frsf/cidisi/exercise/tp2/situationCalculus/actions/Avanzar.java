package frsf.cidisi.exercise.tp2.situationCalculus.actions;

import frsf.cidisi.exercise.tp1.search.LaberintosEstado;
import frsf.cidisi.exercise.tp1.search.RonlyEstado;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Avanzar extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    	LaberintosEstado environmentState = (LaberintosEstado) est;
    	RonlyEstado agState = ((RonlyEstado) ast);

        // TODO: Use this conditions
        // PreConditions: null
        // PostConditions: null
        
    	// Posicion actual del agente
        int row = agState.getPosicion().getFirst();		// Fila
        int col = agState.getPosicion().getSecond();	// Columna
        
        // Update the agent state
        switch(agState.getOrientacion()) {
        case RonlyEstado.NORTE:
        	row--;
        	break;
        case RonlyEstado.SUR:
        	row++;
        	break;
        case RonlyEstado.ESTE:
        	col++;
        	break;
        case RonlyEstado.OESTE:
        	col--;
        	break;
        }

    	// Update the real world
        agState.setPosicion(row,col);
        environmentState.setPosRonly(row, col);

    	return environmentState;
    }

    @Override
    public String toString() {
        return "Avanzar";
    }
}

