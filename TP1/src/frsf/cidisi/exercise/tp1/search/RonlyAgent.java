package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.exercise.tp1.search.actions.GirarDer;
import frsf.cidisi.exercise.tp1.search.actions.GiraIzq;
import frsf.cidisi.exercise.tp1.search.actions.Avanzar;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

public class RonlyAgent extends SearchBasedAgent {

    public RonlyAgent() {

        // The Agent Goal
        RonlyGoal agGoal = new RonlyGoal();

        // The Agent State
        RonlyEstado agState = new RonlyEstado();
        this.setAgentState(agState);

        // Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.addElement(new GirarDer());	
        operators.addElement(new GiraIzq());	
        operators.addElement(new Avanzar());	

        // Create the Problem which the agent will resolve
        Problem problem = new Problem(agGoal, agState, operators);
        this.setProblem(problem);
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

        // Create the search strategy
        IStepCostFunction cost = new CostFunction();
        IEstimatedCostFunction heuristic = new Heuristic(); 
        AStarSearch strategy = new AStarSearch(cost, heuristic);          

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.GRAPHVIZ_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

        // Ask the solver for the best action
        Action selectedAction = null;
        try {
            selectedAction =
                    this.getSolver().solve(new Object[]{this.getProblem()});
        } catch (Exception ex) {
            Logger.getLogger(RonlyAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;

    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }
}
