package frsf.cidisi.exercise.tp2.situationCalculus.actions;

import frsf.cidisi.exercise.tp2.situationCalculus.LaberintosEstado;
import frsf.cidisi.exercise.tp2.situationCalculus.RonlyEstado;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GirarDer extends SituationCalculusAction {

    @Override
    public EnvironmentState execute(AgentState ast, EnvironmentState est) {
        LaberintosEstado environmentState = (LaberintosEstado) est;
        RonlyEstado agState = ((RonlyEstado) ast);
        
        // Precondiciones
        	// -- No hay precondiciones
        
        // Update the real world
        int actualOrientation = environmentState.getOrientation();
        environmentState.setOrientation((actualOrientation + 1) % 4);
        
        return environmentState;
    }

    @Override
    public String toString() {
        return "girarDer";
    }
}

