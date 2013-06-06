package frsf.cidisi.exercise.tp2.situationCalculus;

import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.exercise.datastructures.Laberinto;
import frsf.cidisi.exercise.datastructures.PairInt;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class LaberintosAmbiente extends Environment {
    
	public LaberintosAmbiente() {
        // Create the environment state
        this.environmentState = new LaberintosEstado();
    }
	
	@Override
	public Perception getPercept() {
    	LaberintosEstado estado = (LaberintosEstado) environmentState;
    	
    	PairInt posRonly    = estado.getPosRonly();
    	Laberinto laberinto = estado.getLaberintoActual();
    	
    	/* Si Ronly se encuentra en la posicion de salida, quiere decir
    	 * que encontro la salida para el nivel anterior, y por lo tanto
    	 * pasamos de nivel para percibir el siguiente.
    	 * Caso contrario, percibirá nuevamente el nivel actual, en la
    	 * posicion donde se ha quedado, para que termine de resolver el
    	 * nivel (Esto último en realidad nunca pasa en este modelo, dado
    	 * que este if siempre va a dar true porque siempre se percibe
    	 * luego de resolver cada nivel)
    	 */
    	if(estado.isRonlyOnExit()) { estado.pasarNivel(); }

    	List<RonlyAgentPerception> list = new ArrayList(laberinto.getCols()*laberinto.getRows());
    	
    	for(int row=0; row < laberinto.getRows(); row++) {
    		for(int col=0; col < laberinto.getCols(); col++) {
    			int celda = laberinto.getCelda(row, col);
    			
    			// Se crea una nueva percepcion
    			RonlyAgentPerception perception = new RonlyAgentPerception();
    			
    			perception.setEntrada  (laberinto.consulta(Laberinto.ES_ENTRADA, row, col));
    			perception.setSalida   (laberinto.consulta(Laberinto.ES_SALIDA, row, col));
    			perception.setAbajo    (laberinto.consulta(Laberinto.PARED_ABAJO, row, col));
    			perception.setArriba   (laberinto.consulta(Laberinto.PARED_ARRIBA, row, col));
    			perception.setDerecha  (laberinto.consulta(Laberinto.PARED_DERECHA, row, col));
    			perception.setIzquierda(laberinto.consulta(Laberinto.PARED_IZQUIERDA, row, col));
    			perception.setCandado  (laberinto.consulta(Laberinto.HAY_CANDADO, row, col));
    			perception.setLlave    (laberinto.consulta(Laberinto.HAY_LLAVE, row, col));
    			perception.setNivel    (estado.getNivelActual());
    			perception.setPosicion (posRonly);
    			
    			list.add(perception);
    		}
    	}

		// Return the perception
    	//TODO: Que hacemos aca?
		return list.get(0);
	}
	
    /* Getters & Setters *****************************************************/
    public Laberinto getLaberintoActual() {
    	return ((LaberintosEstado) environmentState).getLaberintoActual();
    }
}
