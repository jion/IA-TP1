package frsf.cidisi.exercise.tp1.search.actions;

import frsf.cidisi.exercise.tp1.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;
import frsf.cidisi.faia.state.datastructure.Pair;

public class Avanzar extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	RonlyEstado agState = (RonlyEstado) s;
        Laberinto   laberinto = agState.getlaberinto();
        
        // Posicion actual del agente
        Pair<Integer, Integer> pos = agState.getposicion();
        
        // Posicion actual del agente
        int row = agState.getposicion().getFirst();	// Fila
        int col = agState.getposicion().getSecond();	// Columna
        
        // PreConditions: El agente debe poder moverse hacia adelante
        switch(agState.getorientacion()) {
        case RonlyEstado.NORTE:
        	if(!laberinto.consulta(Laberinto.PARED_ARRIBA, row, col)) { // Si NO hay pared arriba
        		if(laberinto.consulta(Laberinto.HAY_CANDADO, row-1, col)) {
        			if(agState.getllave()) row--; else return null;
        		} else {
        			row--;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.SUR:
        	if(!laberinto.consulta(Laberinto.PARED_ABAJO, row, col)) {
        		if(laberinto.consulta(Laberinto.HAY_CANDADO, row+1, col)) {
        			if(agState.getllave()) row++; else return null;
        		} else {
        			row++;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.ESTE:
        	if(!laberinto.consulta(Laberinto.PARED_DERECHA, row, col)) {
        		if(laberinto.consulta(Laberinto.HAY_CANDADO, row, col+1)) {
        			if(agState.getllave()) col++; else return null;
        		} else {
        			col++;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.OESTE:
        	if((col>0) && !laberinto.consulta(Laberinto.PARED_IZQUIERDA, row, col)) {
        		if(laberinto.consulta(Laberinto.HAY_CANDADO, row, col-1)) {
        			if(agState.getllave()) col--; else return null;
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
        agState.setposicion(row, col);
 
        // Si hay llave, actualizar estado
        /* TODO: Aca deberia actualizarse el estado del laberinto
         *   agState.setllave(laberinto.takeLlave(row, col));
         */
        if(laberinto.consulta(Laberinto.HAY_LLAVE, pos)) { agState.setllave(true); }
        
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
        int x = agState.getposicion().getFirst();	// Fila
        int y = agState.getposicion().getSecond();	// Columna
        
        if (true) {
            // Update the agent state
            switch(agState.getorientacion()) {
            case RonlyEstado.NORTE:
            	x--;
            	break;
            case RonlyEstado.SUR:
            	x++;
            	break;
            case RonlyEstado.ESTE:
            	y++;
            	break;
            case RonlyEstado.OESTE:
            	y--;
            	break;
            }
            agState.setposicion(x,y);

        	// Update the real world
        	if(agState.getposSalidas().contains(agState.getposicion())) {
        		environmentState.pasarNivel();
        		if(environmentState.isFinished()) agState.setGoalReached(true); // TODO: Donde se debe actualizar realmente????
        	}
        	
            return environmentState;
        }

        return null;
    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(0);
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
