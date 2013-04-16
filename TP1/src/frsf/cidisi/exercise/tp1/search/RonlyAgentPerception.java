package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class RonlyAgentPerception extends Perception {

	//TODO: Setup Statics
    //public static int UNKNOWN_PERCEPTION = -1;   
	
	
	//TODO: Setup Sensors
	private int[][] percepcionlaberinto;
	
 

    public  RonlyAgentPerception() {
    	//TODO: Complete Method
    }

    public RonlyAgentPerception(Agent agent, Environment environment) {
        super(agent, environment);
    }

    /**
     * This method is used to setup the perception.
     */
    @Override
    public void initPerception(Agent agentIn, Environment environmentIn) {
    	
    	//TODO: Complete Method
        
        //RonlyAgent agent = (RonlyAgent) agentIn;
        //LaberintosAmbiente environment = (LaberintosAmbiente) environmentIn;
        //LaberintosEstado environmentState =
        //        environment.getEnvironmentState();
       
        
    }
    
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        //TODO: Complete Method

        return str.toString();
    }

    // The following methods are agent-specific:
    //TODO: Complete this section with the agent-specific methods
	
     public int[][] getpercepcionlaberinto(){
        return percepcionlaberinto;
     }
     public void setpercepcionlaberinto(int[][] arg){
        this.percepcionlaberinto = arg;
     }
	
   
}
