package frsf.cidisi.exercise.tp1.search;

import java.util.Arrays;

import frsf.cidisi.exercise.datastructures.Laberinto;
import frsf.cidisi.exercise.datastructures.PairInt;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class HeuristicAStartSearch implements IEstimatedCostFunction {

    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    @Override
    public double getEstimatedCost(NTree node) {
        RonlyEstado agState = (RonlyEstado) node.getAgentState();
	
		//Method: Complete Method
        PairInt posRonly = agState.getPosicion();
        PairInt posLlave = agState.getPosLlave();
        
        int dist[] = new int[agState.getPosSalidas().size()];
        int i=0;
        for(PairInt s: agState.getPosSalidas()) {
        	if(agState.isLlave() || !agState.getLaberinto().consulta(Laberinto.HAY_CANDADO, s)) {
        		dist[i] = Math.abs(s.getFirst() - posRonly.getFirst()) + Math.abs(s.getSecond() - posRonly.getSecond());
        	} else {
        		dist[i] = Math.abs(posLlave.getFirst() - posRonly.getFirst()) + Math.abs(posLlave.getSecond() - posRonly.getSecond());
        		dist[i] = dist[i] + Math.abs(posLlave.getFirst() - s.getFirst()) + Math.abs(posLlave.getSecond() - s.getSecond());
        	}
        	i++;
        }
        Arrays.sort(dist);
        
        return dist[0];
    }
}
