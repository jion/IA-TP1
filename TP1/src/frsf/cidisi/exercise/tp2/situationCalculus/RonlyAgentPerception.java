package frsf.cidisi.exercise.tp2.situationCalculus;

import frsf.cidisi.exercise.datastructures.PairInt;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.environment.Environment;

public class RonlyAgentPerception extends SituationCalculusPerception {

	/* Sensores:
	 * 	- laberinto:   El laberinto percibido
	 *  - posInicial:  Posicion inicial del agente
	 *  - ultimoNivel: true si se trata del último nivel (no hay mas niveles)
	 */
	//[arriba,abajo,izquierda,derecha],[llave,candado,salida],N

	private boolean		salida;
	private boolean		candado;
	private boolean		llave;
	
	// Hay pared en alguna orientacion?
	private boolean		izquierda;
	private boolean		derecha;
	private boolean		abajo;
	private boolean		arriba;
	
	// A que nivel pertenece la celda percibida?
	private int			nivel;
	
	// No hay mas niveles?
	boolean noLevels;
	
    public RonlyAgentPerception() {
        super();
        salida= false;
        candado= false;
        llave= false;
        izquierda= false;
        derecha= false;
        abajo= false;
        arriba= false;
        noLevels=false;
    }

	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		
	}
	
    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("percepcion(");
        if(noLevels) {
        	result.append("nada,nada,noHayMas");
        } else {
        	result.append("[");
        	result.append(this.arriba 	  ? "paredArriba"	: "nada");
	        result.append(",");
	        result.append(this.abajo 	  ? "paredAbajo"	: "nada");
	        result.append(",");
	        result.append(this.izquierda  ? "paredIzq"	: "nada");
	        result.append(",");
	        result.append(this.derecha	  ? "paredDer"	: "nada");
	        result.append("],");
	        
        	result.append("[");
        	result.append(this.llave 	? "llave"	: "nada");
	        result.append(",");
	        result.append(this.candado  ? "candado"	: "nada");
	        result.append(",");
	        result.append(this.salida	? "salida"	: "nada");
	        result.append("],");
	        
	        result.append(this.nivel);
        }

        result.append(")");

        return result.toString();
    }
    
    /* Getters & Setters *****************************************************/	

	public void setSalida(boolean salida) {
		this.salida = salida;
	}

	public void setCandado(boolean candado) {
		this.candado = candado;
	}

	public void setLlave(boolean llave) {
		this.llave = llave;
	}

	public void setIzquierda(boolean izquierda) {
		this.izquierda = izquierda;
	}

	public void setDerecha(boolean derecha) {
		this.derecha = derecha;
	}

	public void setAbajo(boolean abajo) {
		this.abajo = abajo;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel + 1;
	}
	public void setNoLevels(boolean noLevels) {
		this.noLevels = noLevels;
	}
	
}
