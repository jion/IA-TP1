package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

/**
 * @author Grupo X
 *
 * Esta clase implementa el objetivo parcial del agente en cada laberinto,
 * el cual consiste en llegar a la salida del mismo para poder asi superar
 * el nivel.
 * Este objetivo es el que debe pasarse al algoritmo de busqueda, el cual
 * no debe confundirse con el objetivo general de Ronly que consiste en
 * superar todos los niveles.
 */
public class RonlyPartialGoal extends GoalTest {
	
	@Override
    public boolean isGoalState (AgentState agentState) {
		RonlyEstado agState = (RonlyEstado) agentState;
		
		/* Verifica que el agente se encuentre en alguna de las casillas
		 * marcadas como posiciones de salida. Este es el objetivo parcial
		 * que debe cumplir el algoritmo de busqueda para poder pasar al
		 * siguiente nivel.
		 */
        return agState.getposSalidas().contains(agState.getposicion());
	}
}