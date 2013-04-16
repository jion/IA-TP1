package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

/**
 * Represent the internal state of the Agent.
 */
public class RonlyEstado extends SearchBasedAgentState {
	
	//TODO: Setup Variables
    private int[] posicion;
    private int orientacion;
    private boolean llave;
    private int[] posLlave;
    //private Other posSalidas;
    private int[][] laberinto;
	

    public RonlyEstado() {
    
    	//TODO: Complete Method
    	/*
			// posicion = initData0;
			// orientacion = initData1;
			// llave = initData2;
			// posLlave = initData3;
			// posSalidas = initData4;
			// laberinto = initData5;
        */
        this.initState();
    }

    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
		//TODO: Complete Method
		
        return null;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
        
        //TODO: Complete Method
    }

    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
	//TODO: Complete Method

    }

    /**
     * This method returns the String representation of the agent state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
       
       //TODO: Complete Method
        
        return true;
    }

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     public int[] getposicion(){
        return posicion;
     }
     public void setposicion(int[] arg){
        posicion = arg;
     }
     public int getorientacion(){
        return orientacion;
     }
     public void setorientacion(int arg){
        orientacion = arg;
     }
     public boolean getllave(){
        return llave;
     }
     public void setllave(boolean arg){
        llave = arg;
     }
     public int[] getposLlave(){
        return posLlave;
     }
     public void setposLlave(int[] arg){
        posLlave = arg;
     }
//     public Other getposSalidas(){
//        return posSalidas;
//     }
//     public void setposSalidas(Other arg){
//        posSalidas = arg;
//     }
     public int[][] getlaberinto(){
        return laberinto;
     }
     public void setlaberinto(int[][] arg){
        laberinto = arg;
     }
	
}

