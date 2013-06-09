package frsf.cidisi.exercise.datastructures;

import java.util.LinkedList;
import java.util.List;

public class Laberinto {
	
	/* Mascaras utilizadas para consultar la información específica
	 * de cada celda.
	 */
	public static final int PAREDES 		= 0x0F;
	public static final int TOKENS 			= 0xF0;
	
	public static final int PARED_NADA		= 0x00;
	public static final int PARED_ARRIBA 	= 0x01;
	public static final int PARED_ABAJO 	= 0x02;
	public static final int PARED_IZQUIERDA = 0x04;
	public static final int PARED_DERECHA 	= 0x08;

	public static final int HAY_LLAVE 	= 0x10;
	public static final int HAY_CANDADO = 0x20;
	public static final int ES_SALIDA 	= 0x40;
	public static final int ES_ENTRADA 	= 0x80;
	
	/**
	 * Contiene la informacion completa del laberinto.
	 */
	char[][] data;
	
	// Listas de elementos relevantes en el laberinto
	private List<PairInt> llaves;
	private List<PairInt> candados;
	private List<PairInt> salidas;
	private List<PairInt> entradas;
	
	/* Constructores *********************************************************/
	protected Laberinto() {
		super();
	}
	
	public Laberinto(char[][] data) {
		this();

		this.data = data;
		initInfo();
		
	}
	
	/* Private methods *******************************************************/
	private void initInfo() {
		//TODO: Inicializar las listas de elementos relevantes.
		// news
		llaves   = new LinkedList<PairInt>();
		candados = new LinkedList<PairInt>();
		entradas = new LinkedList<PairInt>();
		salidas  = new LinkedList<PairInt>();

    	/* Recorre el nuevo laberinto para guardar las posiciones
    	 * de los objetos relevantes que se encuentran en el mismo.
    	 */
    	for(int row=0; row < getRows(); row++) {
    		for(int col=0; col < getCols(); col++) {
    			if(consulta(HAY_LLAVE,   row, col)) { llaves.add(new PairInt(row,col)); }
    			if(consulta(HAY_CANDADO, row, col)) { candados.add(new PairInt(row,col)); }
    			if(consulta(ES_ENTRADA,  row, col)) { entradas.add(new PairInt(row,col)); }
    			if(consulta(ES_SALIDA,   row, col)) { salidas.add(new PairInt(row,col)); }
    			
    		}
    	}
    	
	}

	/* Public methods ********************************************************/
	public boolean consulta(int consulta, PairInt pair) {
		return consulta(consulta, pair.getFirst(), pair.getSecond());
	}
	
	/**
	 * Hace una consulta de mascara sobre una celda especifica del laberinto
	 * para obtener informacion relevante acerca de la misma. Permite generar
	 * consultas mas rapidas y complejas que preguntando mediante los metodos
	 * "is"
	 * 
	 * @param consulta Flags de Laberinto separadas con ORs
	 * @param row fila del laberinto
	 * @param col	columna del laberinto
	 * @return true o false dependiendo si lo consultado es o no verdadero
	 */
	public boolean consulta(int consulta, int row, int col) {
		if(row < data.length && col < data[row].length) {
			return ( (data[row][col] & consulta) != 0 ); 
		}
		return false;
	}
	
	public boolean setCelda(int row, int col, int mask, boolean value) {
		if(row < data.length && col < data[row].length) {

			if(value) {
				data[row][col] |= mask;
			} else {
				data[row][col] &= ~mask;
			}
			
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		// Imprimo los techos
		for(int j=0; j < data[0].length; j++) {
			
			if( consulta(PARED_ARRIBA, 0, j) ) {
				str.append(" _");
			} else {
				str.append("  ");
			}
			
		}
		
		// Imprimo el resto
		for(int i=0; i < data.length; i++) {
			str.append("\n   ");

			for(int j=0; j < data[i].length; j++) {
				if( consulta(PARED_IZQUIERDA, i, j) ) {
					str.append("|");
				} else {
					str.append(" ");
				}
				
				if( consulta(PARED_ABAJO, i, j) ) {
					str.append("_");
				} else {
					str.append(" ");
				}
			}
			
			// Paredes a la derecha del laberinto
			if( consulta(PARED_DERECHA, i, (data[i].length - 1) ) ) {
				str.append("|");
			} else {
				str.append(" ");
			}
			
			// Info extra
			switch(i) {
			case 1:
				str.append(" Entradas:\t" + entradas.toString());
				break;
			case 2:
				str.append(" Salidas:\t" + salidas.toString());
				break;
			case 3:
				str.append(" Llaves:\t" + llaves.toString());
				break;
			case 4:
				str.append(" Candados:\t" + candados.toString());
				break;
			default:
				break;
			}
		}
		
		return str.toString();
	}

	/* Metodos "is". Pueden ser utilizados en vez del metodo consulta aunque
	 * solo contestan una pregunta a la vez.
	 * 
	 */
	public boolean isEntrada(int row, int col) {
		return isEntrada(new PairInt(row,col));
	}
	
	public boolean isEntrada(PairInt pair) {
		return entradas.contains(pair);
	}
	
	public boolean isSalida(int row, int col) {
		return isSalida(new PairInt(row,col));
	}
	
	public boolean isSalida(PairInt pair) {
		return salidas.contains(pair);
	}
	
	public boolean isCandado(int row, int col) {
		return isCandado(new PairInt(row,col));
	}
	
	public boolean isCandado(PairInt pair) {
		return candados.contains(pair);
	}
	
	public boolean isLlave(int row, int col) {
		return isLlave(new PairInt(row,col));
	}
	
	public boolean isLlave(PairInt pair) {
		return llaves.contains(pair);
	}
	
	/* Getters & Setters *****************************************************/
	public List<PairInt> getPosEntradas() {
		return this.entradas;
	}
	
	public List<PairInt> getPosSalidas() {
		return this.salidas;
	}
	
	public List<PairInt> getPosLlaves() {
		return this.llaves;
	}
	
	public List<PairInt> getPosCandados() {
		return this.candados;
	}
	
	public int getCols() {
		return data[0].length;
	}

	public int getRows() {
		return data.length;
	}

	public char getCelda(int row, int col) {
		if(row >= 0 && row < this.getRows()
				&& col >=0 && col < this.getCols())
		{
			return data[row][col];
		}
			
		return 0;
	}
}
