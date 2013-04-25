package frsf.cidisi.exercise.tp1.datastructures;

public class Orientacion {
	public static final int ESTE  = 0;
	public static final int SUR   = 1;
	public static final int OESTE = 2;
	public static final int NORTE = 3;
	
	private int orientacion=ESTE;
	
	public Orientacion(int orientacion) {
		super();
		this.setOrientacion(orientacion);
	}

	public void girarIzq() {
		orientacion = (orientacion - 1);
		if(orientacion < 0) orientacion = NORTE;
	}
	
	public void girarDer() {
		orientacion = (orientacion + 1) % 4;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int orientacion) {
		if(orientacion >= ESTE && orientacion <= NORTE) {
			this.orientacion = orientacion;
		}
	}

	@Override
	public String toString() {
		String ret;
		
		switch(orientacion) {
		case ESTE: 	ret = "Este"; break;
		case SUR: 	ret = "Sur"; break;
		case OESTE: ret = "Oeste"; break;
		case NORTE: ret = "Norte"; break;
		
		default: ret = "undefined"; break;
		}
		
		return ret;
	}
	
	
}
