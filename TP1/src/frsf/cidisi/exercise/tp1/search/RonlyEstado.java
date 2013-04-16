package frsf.cidisi.exercise.tp1.search;

import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.datastructure.Pair;


/**
 * Represent the internal state of the Agent.
 */
public class RonlyEstado extends SearchBasedAgentState {
	
	public static final int NORTE = 1;
	public static final int SUR   = 2;
	public static final int ESTE  = 3;
	public static final int OESTE = 4;
	
	// Posibles acciones del Agente
	public static final int GIR_IZQ = 1;
	public static final int GIR_DER = 2;
	public static final int AVANZAR = 3;

	// Variables de estado del Agente
	private int[][]						laberinto;		// \
    private Pair<Integer,Integer>		posicion;		//  | �D�nde Estoy?
    private int 						orientacion;	// /
    
    private boolean 					llave;			//   �Qu� poseo?
    
    private Pair<Integer,Integer>		posLlave;		// \ �D�nde est�n los objetos importantes?
    private List<Pair<Integer,Integer>> posSalidas;		// / 
    
    //private List<SearchAction> 			path;			//   �Qu� decido hacer?
    
    private boolean 					goalReached;	//   ����Gan�?!?!?! --> Fiesta ISI!
	

	public RonlyEstado() {
    	super();

    	this.posicion = new Pair<Integer, Integer>(0,0);
		this.orientacion = ESTE;
		
		this.llave = false;
		
		this.posLlave = new Pair<Integer, Integer>(0,0);
		this.posSalidas = new ArrayList<Pair<Integer, Integer>>();
		this.goalReached = false;
    }
    
   /* public EstadoRonly(int[][] laberinto,
    		Pair<Integer, Integer> posInicial,
    		List<Pair<Integer, Integer>> posSalidas,
    		boolean llave,
    		Pair<Integer, Integer> posLlave,
    		int orientacion) {

		this.laberinto = laberinto;
    	this.posicion = posInicial;
		this.orientacion = orientacion;
		
		this.llave = llave;
		
		this.posLlave = posLlave;
		this.posSalidas = posSalidas;

		//this.path = new ArrayList<SearchAction>();
		
        this.initState();
    }*/
    
    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
        
	//TODO: Complete Method
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
    	laberinto = ((RonlyAgentPerception) p).getPercepcionLaberinto();
    	
    	/* Recorre el nuevo laberinto para guardar las posiciones
    	 * de los objetos que se encuentran en el mismo.
    	 */
    	for(int fila=0; fila < 10; fila++) {
    		for(int columna=0; columna < 10; columna++) {
    			int celda = laberinto[fila][columna];
    			
    			if((celda & LaberintosEstado.HAY_LLAVE) > 0) { posLlave.setPair(fila, columna); }
    			if((celda & LaberintosEstado.ES_SALIDA) > 0) {
    				posSalidas.add(new Pair<Integer, Integer>(fila, columna));
    			}
    		}
    	}
    	
    	/* Actualiza la posicion inicial del agente para el siguiente
    	 * laberinto. Dada la simplificacion del TP, solo cambiar�a
    	 * la columna.
    	 */
    	posicion.setSecond(0);
    	
    	// Si hay llave en la posicion inicial, la tomamos.
    	int celdaActual= laberinto[posicion.getFirst()][posicion.getSecond()];
    	llave = ((celdaActual & LaberintosEstado.HAY_LLAVE) > 0) ? true : false;

    	this.setGoalReached(((RonlyAgentPerception) p).getWon());
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
   	
     public Pair<Integer, Integer> getposicion(){
        return posicion;
     }
     public void setposicion(int x, int y){
    	 posicion.setFirst(x);
    	 posicion.setSecond(y);
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
     public Pair<Integer, Integer> getposLlave(){
        return posLlave;
     }
     public void setposLlave(Pair<Integer, Integer> arg){
        posLlave = arg;
     }
     public List<Pair<Integer,Integer>> getposSalidas(){
        return posSalidas;
     }
     public void setposSalidas(List<Pair<Integer,Integer>> arg){
        posSalidas = arg;
     }
     public int[][] getlaberinto(){
        return laberinto;
     }
     public void setlaberinto(int[][] arg){
        laberinto = arg;
     }
	
    public boolean isGoalReached() {
 		return goalReached;
 	}

 	public void setGoalReached(boolean goalReached) {
 		this.goalReached = goalReached;
 	}
}

