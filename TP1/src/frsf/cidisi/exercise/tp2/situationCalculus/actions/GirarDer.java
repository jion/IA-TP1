package frsf.cidisi.exercise.tp2.situationCalculus.actions;

import frsf.cidisi.exercise.tp1.search.LaberintosEstado;
import frsf.cidisi.exercise.tp1.search.RonlyEstado;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GirarDer extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        LaberintosEstado environmentState = (LaberintosEstado) est;
        RonlyEstado agState = ((RonlyEstado) ast);

        // TODO: Use this conditions
        // PreConditions: null
        // PostConditions: null

        // Update the real world
        
        // Update the agent state
        switch(agState.getOrientacion()) {
        case RonlyEstado.NORTE:
        	agState.setOrientacion(RonlyEstado.ESTE);
        	break;
        case RonlyEstado.SUR:
        	agState.setOrientacion(RonlyEstado.OESTE);
        	break;
        case RonlyEstado.ESTE:
        	agState.setOrientacion(RonlyEstado.SUR);
        	break;
        case RonlyEstado.OESTE:
        	agState.setOrientacion(RonlyEstado.NORTE);
        	break;
        }
        
        return environmentState;
    }

    @Override
    public String toString() {
        return "GirarDer";
    }
}

