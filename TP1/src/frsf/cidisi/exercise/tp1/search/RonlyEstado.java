package frsf.cidisi.exercise.tp1.search;

import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.exercise.tp1.datastructures.Laberinto;
import frsf.cidisi.exercise.tp1.datastructures.PairInt;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;


/**
 * Represent the internal state of the Agent.
 */
public class RonlyEstado extends SearchBasedAgentState {
	
	public static final int NORTE = 1;
	public static final int ESTE  = 2;
	public static final int SUR   = 3;
	public static final int OESTE = 4;
	
	// Posibles acciones del Agente
	public static final int GIR_IZQ = 1;
	public static final int GIR_DER = 2;
	public static final int AVANZAR = 3;

	// Variables de estado del Agente
	private Laberinto		laberinto;		// \
    private PairInt			posicion;		//  | ¿Dónde Estoy?
    private int 			orientacion;	// /
    
    private boolean 		llave;			//   ¿Qué poseo?
    
    private PairInt			posLlave;		// \ ¿Dónde están los objetos importantes?
    private List<PairInt>	posSalidas;		// / 

    private boolean 		ultimoNivel;	//   Me encuentro en el ultimo nivel?
	

	public RonlyEstado() {
    	super();

    	this.posicion   = new PairInt(0,0);
		this.posLlave   = new PairInt(0,0);
		this.posSalidas = new ArrayList<PairInt>();
		
		this.initState();
    }
    
    /**
     * This method is optional, and sets the initial state of the agent.
     */
    @Override
    public void initState() {
    	// TODO: Tal vez debería inicializarse en base a su ubicacion en el ambiente
    	this.posicion.setPair(0,0);
		this.orientacion = ESTE;
		
		this.llave = false;
		
		this.posLlave.setPair(0,0);
		this.posSalidas.clear();
		this.ultimoNivel = false;
    }
    
    /**
     * This method clones the state of the agent. It's used in the search
     * process, when creating the search tree.
     */
    @Override
    public SearchBasedAgentState clone() {
        
		//TODO: Esto es ya Completo?
    	RonlyEstado state = new RonlyEstado();
    	
    	state.setLaberinto(this.laberinto);
    	state.setLlave(this.llave);
    	state.setOrientacion(this.orientacion);
    	state.setposicion(this.posicion.getFirst(), this.posicion.getSecond());
    	state.setPosLlave(this.posLlave);
    	state.setPosSalidas(this.posSalidas);
    	
    	state.setUltimoNivel(this.ultimoNivel);
    	
        return state;
    }

    /**
     * This method is used to update the Agent State when a Perception is
     * received by the Simulator.
     */
    @Override
    public void updateState(Perception p) {
    	RonlyAgentPerception rap = (RonlyAgentPerception) p;

    	// Percibí el último nivel?
    	this.setUltimoNivel(rap.getUltimoNivel());
    	
    	// Percibo el laberinto
		laberinto = rap.getLaberinto();

		// Reinicializo el estado
		this.setLlave(false);
		this.posSalidas.clear();

    	/* Recorre el nuevo laberinto para guardar las posiciones
    	 * de los objetos que se encuentran en el mismo.
    	 */
    	for(int row=0; row < 10; row++) {
    		for(int col=0; col < 10; col++) {

    			if(laberinto.consulta(Laberinto.HAY_LLAVE, row, col)) {
    				posLlave.setPair(row, col);
    			}
    			
    			if(laberinto.consulta(Laberinto.ES_SALIDA, row, col)) {
    				posSalidas.add(new PairInt(row, col));
    			}

    		}
    	}
    	
    	/* Actualiza la posicion inicial del agente para el siguiente
    	 * laberinto. Dada la simplificacion del TP, solo cambiaría
    	 * la columna, pero se percepciona la posicion completa para
    	 * hacerlo mas generico y permitir al agente conocer la posicion
    	 * inicial del primer laberinto sin tener que considerarla fija en (0,0)
    	 */
    	posicion = rap.getPosInicial().clone();
    	
    	// Si hay llave en la posicion inicial, la tomamos.
    	llave = laberinto.consulta(Laberinto.HAY_LLAVE, this.posicion);

    	return;
    }

    /**
     * This method returns the String representation of the agent state.
     */
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("\n  Posicion: "+ this.getposicion());
		str.append("\n  Orientacion: "+ this.getOrientacion());
		str.append("\n  Tengo llave?: "+ this.isLlave());
		str.append("\n  Laberinto percibido: ");
		str.append("\n   ");
		str.append(laberinto.toString());
		
		return str.toString();
	}


    /**
     * This method is used in the search process to verify if the node already
     * exists in the actual search.
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj != null && (obj.getClass() == RonlyEstado.class)) {
    		RonlyEstado givenState = (RonlyEstado) obj;
    		
    		boolean ret = true;
    		ret = this.isLlave() == givenState.isLlave(); // Camino con/sin llave
    		ret = ret && this.getPosicion().equals(givenState.getPosicion());
    		ret = ret && (this.getOrientacion() == givenState.getorientacion());
    		
    		return ret;
    	}
    		
        return false;
    }

    //TODO: Complete this section with agent-specific methods
    // The following methods are agent-specific:
   	
     public PairInt getposicion(){
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
     
     public PairInt getposLlave(){
        return posLlave;
     }
     
     public void setposLlave(PairInt arg){
        posLlave = arg;
     }
     
     public List<PairInt> getposSalidas(){
        return posSalidas;
     }
     
     public void setposSalidas(List<PairInt> arg){
        posSalidas = arg;
     }
     
     public Laberinto getlaberinto(){
        return laberinto;
     }
     
     public void setlaberinto(Laberinto laberinto){
        this.laberinto = laberinto;
     }
	
    public boolean isUltimoNivel() {
 		return ultimoNivel;
 	}

 	protected void setUltimoNivel(boolean ultimoNivel) {
 		this.ultimoNivel = ultimoNivel;
 	}

	protected void setLaberinto(Laberinto laberinto) {
		this.laberinto = laberinto;
	}

	protected void setPosicion(PairInt posicion) {
		this.posicion = posicion;
	}

	protected void setOrientacion(int orientacion) {
		this.orientacion = orientacion;
	}

	protected void setLlave(boolean llave) {
		this.llave = llave;
	}

	protected void setPosLlave(PairInt posLlave) {
		this.posLlave = posLlave;
	}

	protected void setPosSalidas(List<PairInt> posSalidas) {
		this.posSalidas = posSalidas;
	}

	public Laberinto getLaberinto() {
		return laberinto;
	}

	public PairInt getPosicion() {
		return posicion;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public boolean isLlave() {
		return llave;
	}

	public PairInt getPosLlave() {
		return posLlave;
	}

	public List<PairInt> getPosSalidas() {
		return posSalidas;
	}
	
}

