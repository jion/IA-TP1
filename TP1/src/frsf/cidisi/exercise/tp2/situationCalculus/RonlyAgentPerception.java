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
	//[x, y, entrada, salida, candado, llave, izquierda, derecha, abajo, arriba, level]
	
	private PairInt 	posicion;
	private boolean		entrada;
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
	
    public RonlyAgentPerception() {
        super();

		//TODO: Complete Method
    }

	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
		
	}
	
    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("perception([");
      //[x, y, entrada, salida, candado, llave, izquierda, derecha, abajo, arriba, level]
        // Adjacent cells
        result.append(this.posicion.getFirst());
        result.append(",");
        result.append(this.posicion.getSecond());
        result.append(",");
        result.append(this.entrada ? "entrada"	: "nada");
        result.append(",");
        result.append(this.salida  ? "salida"	: "nada");
        result.append(",");
        result.append(this.candado ? "candado"	: "nada");
        result.append(",");
        result.append(this.llave   ? "llave"	: "nada");
        result.append(",");
        result.append(this.izquierda ? "izquierda" : "nada");
        result.append(",");
        result.append(this.derecha ? "derecha"	: "nada");
        result.append(",");
        result.append(this.abajo   ? "abajo"	: "nada");
        result.append(",");
        result.append(this.arriba  ? "arriba"	: "nada");
        result.append(",");
        result.append(this.nivel);

        result.append("]");

        result.append(")");

        return result.toString();
    }
    
    /* Getters & Setters *****************************************************/	

	public void setPosicion(PairInt posicion) {
		this.posicion = posicion;
	}

	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}

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
		this.nivel = nivel;
	}
}
