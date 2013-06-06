package frsf.cidisi.exercise.datastructures;

public class Ronly {
	PairInt posicion;
	Orientacion orientacion;
	boolean tieneLlave;
	
	
	public Ronly() {
		super();
		posicion = new PairInt(0, 0);
		orientacion = new Orientacion(Orientacion.ESTE);
		tieneLlave=false;
	}
	
	// Getters & Setters //////////////////////////////////////////////////////
	
	/* Posicion **************************************************************/
	public PairInt getPosicion() {
		return posicion;
	}
	public int getRow() {
		return posicion.getFirst();
	}
	public int getCol() {
		return posicion.getSecond();
	}
	
	public void setPosicion(PairInt posicion) {
		this.posicion = posicion;
	}
	public void setPosicion(int row, int col) {
		this.posicion.setFirst(row);
		this.posicion.setFirst(col);
	}
	public void setFila(int row) {
		this.posicion.setFirst(row);
	}
	public void setColumna(int col) {
		this.posicion.setSecond(col);
	}
	
	/* Orientacion ***********************************************************/
	public int getOrientacion() {
		return orientacion.getOrientacion();
	}
	public void setOrientacion(int orientacion) {
		this.orientacion.setOrientacion(orientacion);
	}
	
	public void girarIzq() {
		orientacion.girarIzq();
	}
	
	public void girarDer() {
		orientacion.girarDer();
	}

	/* Llave *****************************************************************/
	public boolean tieneLlave() {
		return tieneLlave;
	}
	public void setTieneLlave(boolean tieneLlave) {
		this.tieneLlave = tieneLlave;
	}
	
}
