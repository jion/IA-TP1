package frsf.cidisi.exercise.tp1.search.actions;

import frsf.cidisi.exercise.datastructures.Laberinto;
import frsf.cidisi.exercise.datastructures.PairInt;
import frsf.cidisi.exercise.tp1.search.LaberintosEstado;
import frsf.cidisi.exercise.tp1.search.RonlyEstado;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class Avanzar extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	RonlyEstado agState = (RonlyEstado) s;
        Laberinto   laberinto = agState.getLaberinto();
        
        // Posicion actual del agente
        PairInt pos = agState.getPosicion();
        
        // Posicion actual del agente
        int row = agState.getPosicion().getFirst();		// Fila
        int col = agState.getPosicion().getSecond();	// Columna
        
        // PreConditions: El agente debe poder moverse hacia adelante
        switch(agState.getOrientacion()) {
        case RonlyEstado.NORTE:
        	if(!laberinto.consulta(Laberinto.PARED_ARRIBA, row, col)) { // Si NO hay pared arriba
        		if(laberinto.consulta(Laberinto.HAY_CANDADO, row-1, col)) {
        			if(agState.getTieneLlave()) row--; else return null;
        		} else {
        			row--;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.SUR:
        	if(!laberinto.consulta(Laberinto.PARED_ABAJO, row, col)) {
        		if(laberinto.consulta(Laberinto.HAY_CANDADO, row+1, col)) {
        			if(agState.getTieneLlave()) row++; else return null;
        		} else {
        			row++;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.ESTE:
        	if(!laberinto.consulta(Laberinto.PARED_DERECHA, row, col)) {
        		if(laberinto.consulta(Laberinto.HAY_CANDADO, row, col+1)) {
        			if(agState.getTieneLlave()) col++; else return null;
        		} else {
        			col++;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.OESTE:
        	if((col>0) && !laberinto.consulta(Laberinto.PARED_IZQUIERDA, row, col)) {
        		if(laberinto.consulta(Laberinto.HAY_CANDADO, row, col-1)) {
        			if(agState.getTieneLlave()) col--; else return null;
        		} else {
        			col--;
        		}
        		break;
        	}
        	return null;
        	
        }
        
        /* PostConditions:
         *   - El agente estará en su nueva posicion
         */
        agState.setPosicion(row, col);
 
        // Si hay llave, actualizar estado
        /* TODO: Aca deberia actualizarse el estado del laberinto
         *   agState.setllave(laberinto.takeLlave(row, col));
         */
        if(laberinto.consulta(Laberinto.HAY_LLAVE, pos)) { agState.setTieneLlave(true); }
        
        return agState;
    }

    /**
     * This method updates the agent state and the real world state.
     */
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

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(30);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "Avanzar";
    }
}