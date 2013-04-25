package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

/**
 * This class can be used in any search strategy like
 * Uniform Cost.
 */
public class CostFunctionPuntoA implements IStepCostFunction {

    /**
     * This method calculates the cost of the given NTree node.
     */
    @Override
    public double calculateCost(NTree node) {
    	RonlyEstado agState = (RonlyEstado) node.getAgentState();

        return agState.getCantDeGiros() * 5;
    }
}
