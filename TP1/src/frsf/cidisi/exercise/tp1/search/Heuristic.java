package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.exercise.tp1.datastructures.PairInt;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class allows to define a function to be used by any
 * informed search strategy, like A Star or Greedy.
 */
public class Heuristic implements IEstimatedCostFunction {

    /**
     * It returns the estimated cost to reach the goal from a NTree node.
     */
    @Override
    public double getEstimatedCost(NTree node) {
        RonlyEstado agState = (RonlyEstado) node.getAgentState();
	
		//Method: Complete Method
        PairInt posSal   = agState.getposSalidas().get(0);
        PairInt posRonly = agState.getposicion();
        
        return Math.abs(posSal.getFirst() - posRonly.getFirst()) + Math.abs(posSal.getSecond() - posRonly.getSecond());
    }
}
