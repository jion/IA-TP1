package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class LaberintosAmbiente extends Environment {

    public LaberintosAmbiente() {
        // Create the environment state
        this.environmentState = new LaberintosEstado();
    }

    public LaberintosEstado getEnvironmentState() {
        return (LaberintosEstado) super.getEnvironmentState();
    }

    /**
     * This method is called by the simulator. Given the Agent, it creates
     * a new perception reading, for example, the agent position.
     * @param agent
     * @return A perception that will be given to the agent by the simulator.
     */
    @Override
    public  RonlyAgentPerception getPercept() {
        // Create a new perception to return
         RonlyAgentPerception perception = new RonlyAgentPerception();
		
		//TODO : Set the perceptions sensors
        
        // Return the perception
        return perception;
    }

    
    public String toString() {
        return environmentState.toString();
    }

    
    public boolean agentFailed(Action actionReturned) {

        LaberintosEstado envState =
                this.getEnvironmentState();

        // TODO: Complete Method        

        return false;
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
    
    
}
