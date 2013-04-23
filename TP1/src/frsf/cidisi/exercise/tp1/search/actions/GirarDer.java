package frsf.cidisi.exercise.tp1.search.actions;

import frsf.cidisi.exercise.tp1.search.*;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GirarDer extends SearchAction {

    /**
     * This method updates a tree node state when the search process is running.
     * It does not updates the real world state.
     */
    @Override
    public SearchBasedAgentState execute(SearchBasedAgentState s) {
    	RonlyEstado agState = (RonlyEstado) s;
        
        // TODO: Use this conditions
        // PreConditions: null
        // PostConditions: null
        switch(agState.getorientacion()) {
        case RonlyEstado.NORTE:
        	agState.setorientacion(RonlyEstado.ESTE);
        	break;
        case RonlyEstado.SUR:
        	agState.setorientacion(RonlyEstado.OESTE);
        	break;
        case RonlyEstado.ESTE:
        	agState.setorientacion(RonlyEstado.SUR);
        	break;
        case RonlyEstado.OESTE:
        	agState.setorientacion(RonlyEstado.NORTE);
        	break;
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

        // Update the real world
        
        // Update the agent state
        switch(agState.getorientacion()) {
        case RonlyEstado.NORTE:
        	agState.setorientacion(RonlyEstado.ESTE);
        	break;
        case RonlyEstado.SUR:
        	agState.setorientacion(RonlyEstado.OESTE);
        	break;
        case RonlyEstado.ESTE:
        	agState.setorientacion(RonlyEstado.SUR);
        	break;
        case RonlyEstado.OESTE:
        	agState.setorientacion(RonlyEstado.NORTE);
        	break;
        }
        
        return environmentState;

    }

    /**
     * This method returns the action cost.
     */
    @Override
    public Double getCost() {
        return new Double(500);
    }

    /**
     * This method is not important for a search based agent, but is essensial
     * when creating a calculus based one.
     */
    @Override
    public String toString() {
        return "GirarDer";
    }
}