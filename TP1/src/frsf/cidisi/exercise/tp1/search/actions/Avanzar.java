package frsf.cidisi.exercise.tp1.search.actions;

import frsf.cidisi.exercise.tp1.search.*;
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
        
        // Posicion actual del agente
        int x = agState.getposicion().getFirst();	// Fila
        int y = agState.getposicion().getSecond();	// Columna
        
        // PreConditions: El agente debe poder moverse hacia adelante
        switch(agState.getorientacion()) {
        case RonlyEstado.NORTE:
        	if((agState.getlaberinto()[x][y] & LaberintosEstado.PARED_ARRIBA) == 0) {
        		if((agState.getlaberinto()[x-1][y] & LaberintosEstado.HAY_CANDADO) != 0) {
        			if(agState.getllave()) x--; else return null;
        		} else {
        			x--;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.SUR:
        	if((agState.getlaberinto()[x][y] & LaberintosEstado.PARED_ABAJO) == 0) {
        		if((agState.getlaberinto()[x+1][y] & LaberintosEstado.HAY_CANDADO) != 0) {
        			if(agState.getllave()) x++; else return null;
        		} else {
        			x++;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.ESTE:
        	if((agState.getlaberinto()[x][y] & LaberintosEstado.PARED_DERECHA) == 0) {
        		if((agState.getlaberinto()[x][y+1] & LaberintosEstado.HAY_CANDADO) != 0) {
        			if(agState.getllave()) y++; else return null;
        		} else {
        			y++;
        		}
        		break;
        	}
        	return null;
        case RonlyEstado.OESTE:
        	if((y>0) && (agState.getlaberinto()[x][y] & LaberintosEstado.PARED_IZQUIERDA) == 0) {
        		if((agState.getlaberinto()[x][y-1] & LaberintosEstado.HAY_CANDADO) != 0) {
        			if(agState.getllave()) y--; else return null;
        		} else {
        			y--;
        		}
        		break;
        	}
        	return null;
        	
        }
        
        /* PostConditions:
         *   - El agente estará en su nueva posicion
         */
        agState.setposicion(x, y);
 
        // Si hay llave, actualizar estado
        if((agState.getlaberinto()[x][y] & LaberintosEstado.HAY_LLAVE) != 0) {
        	//TODO: Aca deberia actualizarse el estado del laberinto
        	agState.setllave(true);
        }
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
