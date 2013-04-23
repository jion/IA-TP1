

package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

/**
 * @author Grupo X
 *
 * Esta clase implementa el objetivo general del agente en cada laberinto,
 * el cual consiste en resolver todos los niveles del juego.
 */
public class RonlyGoal extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    	RonlyEstado agState = (RonlyEstado) agentState;

    	/* Verifico si el agente se encuentra en alguna de las
    	 * posiciones de salida del último nivel */
        return agState.isUltimoNivel() &&
        	agState.getposSalidas().contains(agState.getposicion());
	}
}
