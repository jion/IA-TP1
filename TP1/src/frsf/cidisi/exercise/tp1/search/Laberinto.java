package frsf.cidisi.exercise.tp1.search;

import java.util.List;

import frsf.cidisi.faia.state.datastructure.Pair;

public class Laberinto {
	
	/* Mascaras utilizadas para consultar la información específica
	 * de cada celda.
	 */
	public static final int PARED_ARRIBA = 0x01;
	public static final int PARED_ABAJO = 0x02;
	public static final int PARED_IZQUIERDA = 0x04;
	public static final int PARED_DERECHA = 0x08;
	public static final int HAY_LLAVE = 0x10;
	public static final int HAY_CANDADO = 0x20;
	public static final int ES_SALIDA = 0x40;
	public static final int ES_ENTRADA = 0x40;
	
	/**
	 * Contiene la informacion completa del laberinto.
	 */
	byte[][] data;
	
	// Listas de elementos relevantes en el laberinto
	private List<Pair<Integer, Integer>> llaves;
	private List<Pair<Integer, Integer>> candados;
	private List<Pair<Integer, Integer>> salidas;
	private List<Pair<Integer, Integer>> entradas;
	
	/* Constructores *********************************************************/
	protected Laberinto() {
		super();
	}
	
	public Laberinto(byte[][] data) {
		this();

		this.data = data;
		initInfo();
		
	}
	
	/* Private methods *******************************************************/
	private void initInfo() {
		//TODO: Inicializar las listas de elementos relevantes.
	}
	
	/* Public methods ********************************************************/
	public boolean consulta(int consulta, Pair<Integer, Integer> pair) {
		return consulta(consulta, pair.getFirst(), pair.getSecond());
	}
	
	/**
	 * Hace una consulta de mascara sobre una celda especifica del laberinto
	 * para obtener informacion relevante acerca de la misma. Permite generar
	 * consultas mas rapidas y complejas que preguntando mediante los metodos
	 * "is"
	 * 
	 * @param consulta Flags de Laberinto separadas con ORs
	 * @param x fila del laberinto
	 * @param y	columna del laberinto
	 * @return true o false dependiendo si lo consultado es o no verdadero
	 */
	public boolean consulta(int consulta, int x, int y) {
		if(x < data.length && y < data[x].length) {
			return ( (data[x][y] & consulta) != 0 ); 
		}
		return false;
	}
	
	/* Metodos "is". Pueden ser utilizados en vez del metodo consulta aunque
	 * solo contestan una pregunta a la vez.
	 * 
	 */
	public boolean isEntrada(int x, int y) {
		return isEntrada(new Pair<Integer, Integer>(x,y));
	}
	
	public boolean isEntrada(Pair<Integer, Integer> pair) {
		return entradas.contains(pair);
	}
	
	public boolean isSalida(int x, int y) {
		return isSalida(new Pair<Integer, Integer>(x,y));
	}
	
	public boolean isSalida(Pair<Integer, Integer> pair) {
		return salidas.contains(pair);
	}
	
	public boolean isCandado(int x, int y) {
		return isCandado(new Pair<Integer, Integer>(x,y));
	}
	
	public boolean isCandado(Pair<Integer, Integer> pair) {
		return candados.contains(pair);
	}
	
	public boolean isLlave(int x, int y) {
		return isLlave(new Pair<Integer, Integer>(x,y));
	}
	
	public boolean isLlave(Pair<Integer, Integer> pair) {
		return llaves.contains(pair);
	}
	
	/* Getters & Setters *****************************************************/
	public List<Pair<Integer, Integer>> getPosEntradas() {
		return this.entradas;
	}
	
	public List<Pair<Integer, Integer>> getPosSalidas() {
		return this.salidas;
	}
	
	public List<Pair<Integer, Integer>> getPosLlaves() {
		return this.llaves;
	}
	
	public List<Pair<Integer, Integer>> getPosCandados() {
		return this.candados;
	}
	
}
