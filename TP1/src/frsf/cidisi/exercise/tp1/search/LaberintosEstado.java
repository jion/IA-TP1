package frsf.cidisi.exercise.tp1.search;

import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class LaberintosEstado extends EnvironmentState {
	
	public static final int PARED_ARRIBA = 0x01;
	public static final int PARED_ABAJO = 0x02;
	public static final int PARED_IZQUIERDA = 0x04;
	public static final int PARED_DERECHA = 0x08;
	public static final int HAY_LLAVE = 0x10;
	public static final int HAY_CANDADO = 0x20;
	public static final int ES_SALIDA = 0x40;
	
    private static final int[][] nivel1 = {
    							{0x03, 0x09, 0x0B, 0x01, 0x03, 0x09, 0x05, 0x03, 0x0B, 0x09},
								{0x0D, 0x04, 0x03, 0x08, 0x0D, 0x0E, 0x04, 0x01, 0x03, 0x08},
								{0x0C, 0x06, 0x09, 0x0E, 0x0C, 0x05, 0x08, 0x06, 0x0B, 0x0C},
								{0x06, 0x03, 0x00, 0x03, 0x0A, 0x0E, 0x06, 0x03, 0x09, 0x0C},
								{0x0D, 0x0D, 0x0C, 0x05, 0x01, 0x03, 0x03, 0x09, 0x0C, 0x0C},
								{0x06, 0x08, 0x0C, 0x0E, 0x0C, 0x0D, 0x0D, 0x0C, 0x0C, 0x0E},
								{0x05, 0x02, 0x02, 0x03, 0x08, 0x06, 0x08, 0x0C, 0x06, 0x09},
								{0x0C, 0x07, 0x09, 0x0D, 0x0C, 0x05, 0x08, 0x04, 0x01, 0x08},
								{0x04, 0x01, 0x0A, 0x06, 0x02, 0x0A, 0x0E, 0x0C, 0x1E, 0x0E},
								{0x0E, 0x06, 0x03, 0x03, 0x03, 0x03, 0x0B, 0x06, 0x03, 0x53}};
	
    private List<int[][]> laberintos;
    private int nivelActual;
	
    public LaberintosEstado() {
    	super();
    	
    	laberintos = new ArrayList<int[][]>();
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {
    	nivelActual=0;

    	laberintos.add(nivel1);
    	// laberintos.add(nivel2);
    	// laberintos.add(nivel3);
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

    // The following methods are agent-specific:
    /* Getters & Setters *****************************************************/
     public int[][] getLaberintoActual(){
    	 if(!isFinished()) { return laberintos.get(nivelActual); }
    	 
    	 return null;
     }

     public int getnivelActual(){
        return nivelActual;
     }

	public Boolean isFinished() {
		return (nivelActual >= laberintos.size());
	}
}

