package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;
import frsf.cidisi.faia.state.datastructure.Pair;

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
        Pair<Integer,Integer> posSal   = agState.getposSalidas().get(0);
        Pair<Integer,Integer> posRonly = agState.getposicion();
        
        return Math.abs(posSal.getFirst() - posRonly.getFirst()) + Math.abs(posSal.getSecond() - posRonly.getSecond());
    }
}
