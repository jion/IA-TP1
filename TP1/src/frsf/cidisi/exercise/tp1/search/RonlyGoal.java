

package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class RonlyGoal extends GoalTest {

    @Override
    public boolean isGoalState (AgentState agentState) {
    	RonlyEstado agState = (RonlyEstado) agentState;

    	/* Verifico si el agente esta en estado de fiesta => Ganó! */
        return agState.isGoalReached();
	}
}