package frsf.cidisi.exercise.tp1.search;

import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import frsf.cidisi.exercise.tp1.search.actions.Avanzar;
import frsf.cidisi.exercise.tp1.search.actions.GiraIzq;
import frsf.cidisi.exercise.tp1.search.actions.GirarDer;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.Search;

public class RonlyAgent extends SearchBasedAgent {

	/* PartialProblem es el subproblema a resolver en cada ciclo
	 * de percepcion, esto es, resolver el laberinto actual donde
	 * se encuentra posicionado el agente.
	 */
	private Problem partialProblem;
	
	public RonlyAgent() {
		/* Goals */
		RonlyGoal agGoal = new RonlyGoal();				// El objetivo General del agente
		RonlyPartialGoal ptGoal = new RonlyPartialGoal();	// El objetivo Parcial del agente
		 
		/* Actions */
		Vector<SearchAction> operators = new Vector<SearchAction>();
		operators.addElement(new Avanzar());
		operators.addElement(new GirarDer());
		operators.addElement(new GiraIzq());
		
		/* Estado */
		RonlyEstado state = new RonlyEstado();
		this.setAgentState(state);
		
		/* Problemas */
		Problem agProblem = new Problem(agGoal, state, operators);
		Problem ptProblem = new Problem(ptGoal, state, operators);
		 
        /* Seteamos el problema general y parcial del agente */
        this.setProblem(agProblem);
        this.setPartialProblem(ptProblem);
        
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public List<SearchAction> selectAction() {

        // Create the search strategy
        IStepCostFunction cost = new CostFunction();
        IEstimatedCostFunction heuristic = new Heuristic(); 
        AStarSearch strategy = new AStarSearch(cost, heuristic);          

        // Create a Search object with the strategy
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.WHITHOUT_TREE);//.XML_TREE);

        // Set the Search searchSolver.
        this.setSolver(searchSolver);

		// Ask the solver for the best action
        List<SearchAction> path = null;
        
        try {
            path = this.getSolver().solve(new Object[]{this.getPartialProblem()});
            // System.out.println(path.toString()); //TODO: Debug. Borrar.
        } catch (Exception ex) {
            Logger.getLogger(RonlyAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return path;
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
    
    @Override
	public Search getSolver() {
		// TODO Auto-generated method stub
		return (Search) super.getSolver();
	}

	/* Getters & Setters *****************************************************/
    public Problem getPartialProblem() {
    	return partialProblem;
    }

    public void setPartialProblem(Problem partialProblem) {
    	this.partialProblem = partialProblem;
    }
}
