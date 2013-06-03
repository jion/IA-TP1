
package frsf.cidisi.exercise.tp2.situationCalculus;

import java.util.List;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.NoAction;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusAction;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusBasedAgent;
import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.solver.situationcalculus.SituationCalculus;

public class RonlyAgent extends SituationCalculusBasedAgent {

    private Action lastAction = NoAction.getInstance();

    public RonlyAgent() throws PrologConnectorException {
        this.setAgentState(new RonlyEstado());
    }

    @Override
    public void tell(List<? extends Action> actions) {
        RonlyEstado kb = this.getAgentState();
        kb.tell(actions);
    }

    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }

    @Override
    public List<SituationCalculusAction> selectAction() {
        this.setSolver(new SituationCalculus());

        List<SituationCalculusAction> selectedActions = null;
        try {
            selectedActions = (List<SituationCalculusAction>) this.getSolver().solve(new Object[]{this.getAgentState()});
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return selectedActions;
    }

    @Override
    public RonlyEstado getAgentState() {
        RonlyEstado agentState = (RonlyEstado) super.getAgentState();

        return agentState;
    }

    public Action getLastAction() {
        return this.lastAction;
    }
}
