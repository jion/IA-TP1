package frsf.cidisi.exercise.tp2.situationCalculus;

import frsf.cidisi.exercise.tp1.datastructures.Laberinto;
import frsf.cidisi.exercise.tp1.datastructures.PairInt;
import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.situationcalculus.KnowledgeBase;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RonlyEstado extends KnowledgeBase {
	
	public static final int NORTE = 0;
	public static final int ESTE  = 1;
	public static final int SUR   = 2;
	public static final int OESTE = 3;
	
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
    
    private int				cantDeGiros;    //	 Lleva la cuenta de la cantidad de giros

    private boolean 		ultimoNivel;	//   Me encuentro en el ultimo nivel?
	
    
    public RonlyEstado() throws PrologConnectorException {

		//TODO: Replace file name  
        super("kb/ronly.pl");

        this.initState();
    }

    @Override
    public void initState() {
    	this.posicion   = new PairInt(0,0);
		this.posLlave   = new PairInt(0,0);
		this.posSalidas = new ArrayList<PairInt>();
		
    	// En realidad el agente nunca sabra su posicion real actual hasta no
    	// realizar la primera percepcion.
    	this.posicion.setPair(0,0);
		this.orientacion = ESTE;
		
		this.llave = false;
		
		this.posLlave.setPair(0,0);
		this.posSalidas.clear();
		this.ultimoNivel = false;
		this.cantDeGiros = 0;
    }
    
    @Override
    public ActionFactory getActionFactory() {
        return RonlyAgentActionFactory.getInstance();
    }

    @Override
    public String getSituationPredicate() {
        return "actualSituation";
    }

    @Override
    public String getBestActionPredicate() {
        return "bestAction";
    }

    @Override
    public String getExecutedActionPredicate() {
        return "action";
    }

    @Override
    public void updateState(Perception p) {
        this.tell((SituationCalculusPerception) p);
    }

    @Override
    public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("\n  Posicion: "+ this.getPosicion());
		str.append("\n  Orientacion: "+ this.getOrientacion());
		str.append("\n  Tengo llave?: "+ this.isLlave());
		str.append("\n  Laberinto percibido: ");
		str.append("\n   ");
		str.append(laberinto.toString());
		
		return str.toString();
    }

    // The following methods are agent-specific:
     public PairInt getPosicion(){
        return posicion;
     }
     
     public void setPosicion(int x, int y){
    	 posicion.setFirst(x);
    	 posicion.setSecond(y);
     }
     
     public int getOrientacion(){
        return orientacion;
     }
     
     public void setOrientacion(int arg){
        orientacion = arg;
     }
     
     public boolean getTieneLlave(){
        return llave;
     }
     
     public void setTieneLlave(boolean tieneLlave){
        llave = tieneLlave;
     }
     
     public PairInt getPosLlave(){
        return posLlave;
     }
     
     public void setposLlave(PairInt arg){
        posLlave = arg;
     }
     
     public List<PairInt> getPosSalidas(){
        return posSalidas;
     }
     
     public void setPosSalidas(List<PairInt> arg){
        posSalidas = arg;
     }
     
     public Laberinto getLaberinto(){
        return laberinto;
     }
     
     public void setLaberinto(Laberinto laberinto){
        this.laberinto = laberinto;
     }
	
    public boolean isUltimoNivel() {
 		return ultimoNivel;
 	}

 	protected void setUltimoNivel(boolean ultimoNivel) {
 		this.ultimoNivel = ultimoNivel;
 	}

	protected void setPosicion(PairInt posicion) {
		this.posicion = posicion;
	}

	protected void setLlave(boolean llave) {
		this.llave = llave;
	}

	protected void setPosLlave(PairInt posLlave) {
		this.posLlave = posLlave;
	}

	public boolean isLlave() {
		return llave;
	}


	public int getCantDeGiros() {
		return cantDeGiros;
	}

	public void aumentarCantidadDeGiros() {
		this.cantDeGiros++;
	}
}
