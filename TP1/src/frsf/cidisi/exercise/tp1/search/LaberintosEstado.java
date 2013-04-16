package frsf.cidisi.exercise.tp1.search;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class LaberintosEstado extends EnvironmentState {
	
	//TODO: Setup Variables
    private int[][] laberinto;
    private int nivelActual;
	
    public LaberintosEstado() {
        
        //TODO: Complete Method
    	/*
			// laberinto = initData0;
			// nivelActual = initData1;
        */
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {

        //TODO: Complete Method
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {
        String str = "";

        //TODO: Complete Method

        return str;
    }

	//TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
	
     public int[][] getlaberinto(){
        return laberinto;
     }
     public void setlaberinto(int[][] arg){
        laberinto = arg;
     }
     public int getnivelActual(){
        return nivelActual;
     }
     public void setnivelActual(int arg){
        nivelActual = arg;
     }
	

}

