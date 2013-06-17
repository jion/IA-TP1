package frsf.cidisi.exercise.tp2.situationCalculus;

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
    	
    	Laberinto laberinto = estado.getLaberintoActual();
    	
    	// Se crea una nueva percepcion (Por default todo es false)
		RonlyAgentPerception perception = new RonlyAgentPerception();
		
		perception.setNivel    (estado.getNivelActual());
		
    	if(laberinto != null) { // Si quedan laberintos por resolver
    		
    		PairInt posRonly    = estado.getPosRonly();
    		
	    	// Celda que se va a percibir (La correspondiente a la posicion de Ronly)
	    	int row = posRonly.getFirst();
	    	int col = posRonly.getSecond();

			perception.setSalida   (laberinto.consulta(Laberinto.ES_SALIDA,       row, col));
			perception.setAbajo    (laberinto.consulta(Laberinto.PARED_ABAJO,     row, col));
			perception.setArriba   (laberinto.consulta(Laberinto.PARED_ARRIBA,    row, col));
			perception.setDerecha  (laberinto.consulta(Laberinto.PARED_DERECHA,   row, col));
			perception.setIzquierda(laberinto.consulta(Laberinto.PARED_IZQUIERDA, row, col));
			perception.setCandado  (laberinto.consulta(Laberinto.HAY_CANDADO,     row, col));
			perception.setLlave    (laberinto.consulta(Laberinto.HAY_LLAVE,       row, col));
    	} else {
    		perception.setNoLevels(true);
    	}

		// Return the perception
		return perception;
	}
	
    /* Getters & Setters *****************************************************/
    public Laberinto getLaberintoActual() {
    	return ((LaberintosEstado) environmentState).getLaberintoActual();
    }
    
    public LaberintosEstado getEstado() {
    	return (LaberintosEstado) environmentState;
    }

	@Override
	public String toString() {
		return getEstado().toString();
	}
    
}
