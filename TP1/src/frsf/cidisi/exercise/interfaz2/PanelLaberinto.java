package frsf.cidisi.exercise.interfaz2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frsf.cidisi.exercise.datastructures.Laberinto;
import frsf.cidisi.exercise.datastructures.Orientacion;
import frsf.cidisi.exercise.tp1.search.RonlyAgentPerception;
import frsf.cidisi.exercise.tp2.situationCalculus.LaberintosAmbiente;
import frsf.cidisi.exercise.tp2.situationCalculus.LaberintosEstado;
import frsf.cidisi.exercise.tp2.situationCalculus.RonlyEstado;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.simulator.events.EventHandler;
import frsf.cidisi.faia.simulator.events.EventType;

public class PanelLaberinto extends JPanel implements EventHandler {
	private static final long serialVersionUID = 1L;

	private boolean ultimoNivel;
	private int sleepTime;
	

	/*Posicion inicial del agente en el laberinto*/

	int colRonly;
	int rowRonly;

	/*variable que se utiliza para setear la imagen de ronly al comienzo de cada laberinto*/

	boolean primerRonly = true;


	/* Orientacion del agente*/
	int orientacionActual;

	/* acciones: esta lista contiene la secuencia de acciones que el agente debe llevar a cabo para resolver los
	 * diferentes laberintos que se le plantean
	 * laberinto: contiene el laberinto que el agente debe resolver.
	 */

	List<Action> acciones = null;
	Laberinto laberinto = null;
	RonlyEstado estadoRonly;
	int nivelActual;

	/*llave indica si el agente posee la llave para abrir el candado, esta se setea cuando el agente pasa
	 * sobre una posición con llave. Como el ambiente no se modifica, la llave no se elimina del ambiente,
	 * pero esta accion si modifica el estado del agente, cambiando su variable interna llave.
	 */
	boolean llave;
	boolean candado;

	//variable auxiliar que permite controlar el dibujo del laberinto en el JPanel
	int contador =1;

	private boolean showPasos;

	private boolean showMigas;

	public void setShowPasos(boolean showPasos) {
		this.showPasos = showPasos;
	}
	/**
	 * Constructor de la clase PantallaPrincipal.
	 * Se setean las propiedades del JFrame, se le añade un JPanel para dibujar el laberinto
	 */
	public PanelLaberinto() {

		super();
		this.setSleepTime(300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		this.setMinimumSize(new Dimension(480,480));
		setOpaque(false);


	}
	/**
	 * Create the panel.
	 */

	public void inicializarPercepcion (LaberintosEstado laberintos, RonlyEstado estadoRonly){
		
		if(laberintos.getLaberintoActual() == null) {
			this.dibujarNivelSuperado(true);
		} else {
this.setSize(laberintos.getPosRonly().getSecond()*48,laberintos.getPosRonly().getFirst()*48);
			//Se considera a efectos de la reolución de este TP que la orientación inicial siempre es hacia el Este
			orientacionActual= laberintos.getOrientation();
	
			//Se obtiene el laberinto a resolver y la secuencia de acciones que el agente debe llevar a cabo
			//para poder salir del mismo
	
			laberinto = laberintos.getLaberintoActual();
			this.estadoRonly = estadoRonly;
			
			/*Seteamos los valores de la posición inicial de Ronly */
			rowRonly = 40 +(laberintos.getPosRonly().getFirst()*48);
			colRonly= 10 + (laberintos.getPosRonly().getSecond()*48);
	
			llave = laberintos.getRonly().tieneLlave();
			candado = false;
	
			primerRonly = false;
			ultimoNivel = laberintos.isUltimoNivel();
	
			//Se crea la panel principal donde se graficará el laberinto a resolver por el agente
			this.setBounds(0,0,((laberinto.getCols()*48)+17),((laberinto.getRows()*48)+37));
			this.setVisible(true);
			this.repaint();
			try {
				Thread.sleep(this.getSleepTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	/**
	 * Se redefine el método Paint para dibujar el laberinto.
	 */
	public void paint (Graphics g)
	{  
		super.paint(g);
		if(this.laberinto == null) return;
		
		ImageIcon imagen = null;
		ImageIcon ronly = null;
		ImageIcon elemento = null;    
		Graphics offgc;
		Image offscreen = null;
		Dimension d = size();

		// create the offscreen buffer and associated Graphics
		offscreen = createImage(d.width, d.height);
		offgc = offscreen.getGraphics();

		// Se setea, segun la orientacion del agente, la imagen que lo representará en el laberinto
		// además verifica si el agente posee o no la llave que le permite abrir

		if (primerRonly){
			ronly = new ImageIcon("images/RonlyPensando.png");
		}
		else if (!llave && !primerRonly){
			switch (orientacionActual){
			case Orientacion.NORTE: {                                                                                       //Ronly mira al norte
				ronly = new ImageIcon("images/RonlyAtras.png");
				break;
			}
			case Orientacion.ESTE: {                                                                                       //Ronly mira al este
				ronly = new ImageIcon("images/RonlyEste.png");
				break;
			}
			case Orientacion.SUR: {                                                                                       //Ronly mira al sur
				ronly = new ImageIcon("images/Ronly.png");
				break;
			}
			case Orientacion.OESTE: {                                                                                       //Ronly mira al oeste
				ronly = new ImageIcon("images/RonlyOeste.png");
				break;
			}

			}
		}
		else {
			switch (orientacionActual){
			case Orientacion.NORTE: {                                                                                       //Ronly mira al norte
				ronly = new ImageIcon("images/RonlyAtrasLlave.png");

				break;
			}
			case Orientacion.ESTE: {                                                                                       //Ronly mira al este
				ronly = new ImageIcon("images/RonlyEsteLlave.png");
				break;
			}
			case Orientacion.SUR: {                                                                                       //Ronly mira al sur
				ronly = new ImageIcon("images/RonlyFrenteLlave.png");
				break;
			}
			case Orientacion.OESTE: {                                                                                       //Ronly mira al oeste
				ronly = new ImageIcon("images/RonlyOesteLlave.png");
				break;
			}

			}
		}

		//se setea la posicion inicial para comenzar a dibujar
		int x = 10;
		int y = 40;

		//se obtiene el tamaño del laberinto

		int filas = laberinto.getRows();
		int columnas = laberinto.getCols();

		//se recorre el laberinto para dibujar en cada par (row, col) la imagen que representa las paredes que
		//tiene indicadas esa celda.
		for (int row=0;row < filas; row++){
			for (int col =0; col < columnas; col ++){

				switch (laberinto.getCelda(row, col) & Laberinto.PAREDES){
				case Laberinto.PARED_NADA:{                                                                                                            
					imagen = new ImageIcon("images/0.png");
					break;
				}
				case Laberinto.PARED_ARRIBA:{                                                                                          
					imagen = new ImageIcon("images/1.png");
					break;
				}
				case Laberinto.PARED_ABAJO:{                                                                                                            //PARED_ABAJO
					imagen = new ImageIcon("images/2.png");
					break;
				}
				case Laberinto.PARED_ARRIBA | Laberinto.PARED_ABAJO:{  
					imagen = new ImageIcon("images/3.png");
					break;
				}
				case Laberinto.PARED_IZQUIERDA:{                                                                                                                //PARED_IZQUIERDA
					imagen = new ImageIcon("images/4.png");
					break;
				}
				case Laberinto.PARED_ARRIBA | Laberinto.PARED_IZQUIERDA:{              
					imagen = new ImageIcon("images/5.png");
					break;
				}
				case Laberinto.PARED_IZQUIERDA | Laberinto.PARED_ABAJO:{                                                                                                                //PARED_IZQUIERDA y PARED_ABAJO
					imagen = new ImageIcon("images/6.png");
					break;
				}
				case Laberinto.PARED_ARRIBA | Laberinto.PARED_IZQUIERDA | Laberinto.PARED_ABAJO:{      
					imagen = new ImageIcon("images/7.png");
					break;
				}
				case Laberinto.PARED_DERECHA:{
					imagen = new ImageIcon("images/8.png");
					break;
				}
				case Laberinto.PARED_ARRIBA | Laberinto.PARED_DERECHA:{
					imagen = new ImageIcon("images/9.png");
					break;
				}
				case Laberinto.PARED_DERECHA |Laberinto.PARED_ABAJO:{
					imagen = new ImageIcon("images/A.png");
					break;
				}
				case Laberinto.PARED_ARRIBA | Laberinto.PARED_DERECHA | Laberinto.PARED_ABAJO:{
					imagen = new ImageIcon("images/B.png");
					break;
				}
				case Laberinto.PARED_DERECHA | Laberinto.PARED_IZQUIERDA:{
					imagen = new ImageIcon("images/C.png");
					break;
				}
				case Laberinto.PARED_IZQUIERDA |Laberinto.PARED_ARRIBA | Laberinto.PARED_DERECHA:{
					imagen = new ImageIcon("images/D.png");
					break;
				}
				case Laberinto.PARED_IZQUIERDA | Laberinto.PARED_ABAJO | Laberinto.PARED_DERECHA:{
					imagen = new ImageIcon("images/E.png");
					break;
				}
				}

				//evaluamos si hay esquinas que dibujar

				ImageIcon esquina1 = new ImageIcon("images/vacia.png");
				ImageIcon esquina2 = new ImageIcon("images/vacia.png");
				ImageIcon esquina3 = new ImageIcon("images/vacia.png");
				ImageIcon esquina4 = new ImageIcon("images/vacia.png");


				if ((row!=0)&&(col!=0)&&
						(laberinto.consulta(Laberinto.PARED_IZQUIERDA, row-1, col) ||
								laberinto.consulta(Laberinto.PARED_ARRIBA, row, col-1)) &&
								(!laberinto.consulta(Laberinto.PARED_ARRIBA, row, col) &&
										!laberinto.consulta(Laberinto.PARED_IZQUIERDA, row, col))){
					esquina1 = new ImageIcon ("images/ArribaIzquierda.png");
				}
				if (col!=(columnas-1)&& row!=0 &&
						(laberinto.consulta(Laberinto.PARED_DERECHA, row-1, col)||
								laberinto.consulta(Laberinto.PARED_ARRIBA, row, col+1))&&
								(!laberinto.consulta(Laberinto.PARED_ARRIBA, row, col) &&
										!laberinto.consulta(Laberinto.PARED_DERECHA, row, col))){
					esquina2 = new ImageIcon ("images/ArribaDerecha.png");
				}
				if (col!=(columnas-1)&& row!=(filas-1) &&
						(laberinto.consulta(Laberinto.PARED_ABAJO, row, col+1)||
								laberinto.consulta(Laberinto.PARED_DERECHA, row+1, col))&&
								(!laberinto.consulta(Laberinto.PARED_DERECHA, row, col) &&
										!laberinto.consulta(Laberinto.PARED_ABAJO, row, col))){
					esquina3 = new ImageIcon ("images/AbajoDerecha.png");
				}
				if (col!=0 && row!= (filas-1) &&
						(laberinto.consulta(Laberinto.PARED_IZQUIERDA, row+1, col)||
								laberinto.consulta(Laberinto.PARED_ABAJO, row, col-1))&&
								(!laberinto.consulta(Laberinto.PARED_ABAJO, row, col)  &&
										!laberinto.consulta(Laberinto.PARED_IZQUIERDA, row, col))){
					esquina4 = new ImageIcon ("images/AbajoIzquierda.png");
				}
				if ((row!=0) &&
						laberinto.consulta(Laberinto.ES_ENTRADA, row, col) &&
						!laberinto.consulta(Laberinto.PARED_ARRIBA, row, col) &&
						laberinto.consulta(Laberinto.PARED_IZQUIERDA, row-1, col)){
					esquina1 = new ImageIcon ("images/ArribaIzquierda.png");
				}
				if ((row!=filas-1) &&
						laberinto.consulta(Laberinto.ES_ENTRADA, row, col) &&
						!laberinto.consulta(Laberinto.PARED_ABAJO, row, col) &&
						laberinto.consulta(Laberinto.PARED_IZQUIERDA, row+1, col)){
					esquina4 = new ImageIcon ("images/AbajoIzquierda.png");
				}
				if ((row!=0) &&
						laberinto.consulta(Laberinto.ES_SALIDA, row, col) &&
						!laberinto.consulta(Laberinto.PARED_ARRIBA, row, col) &&
						laberinto.consulta(Laberinto.PARED_DERECHA, row-1, col)){
					esquina2 = new ImageIcon ("images/ArribaDerecha.png");
				}
				if ((row!=filas-1) &&
						laberinto.consulta(Laberinto.ES_SALIDA, row, col) &&
						!laberinto.consulta(Laberinto.PARED_ABAJO, row, col) &&
						laberinto.consulta(Laberinto.PARED_DERECHA, row+1, col)){
					esquina3 = new ImageIcon ("images/AbajoDerecha.png");
				}

				//Se dibuja la imagen correspondiente a la caleda actual, y además se dibujan las terminaciones
				//(esquinas) si las hubiera.

				offgc.drawImage (imagen.getImage(), x + (col*48) , y + (row * 48), this);
				offgc.drawImage(esquina1.getImage(), x + (col*48) , y + (row * 48), this );
				offgc.drawImage(esquina2.getImage(), x + (col*48) , y + (row * 48), this );
				offgc.drawImage(esquina3.getImage(), x + (col*48) , y + (row * 48), this );
				offgc.drawImage(esquina4.getImage(), x + (col*48) , y + (row * 48), this );

				//TODO: Se escribe el numero de veces q se paso por la celda
				if(showPasos) {
					offgc.drawString(estadoRonly.getCantPasadas(row,col).toString(),x+24 + (col*48),y+24 + (row * 48));
				}
				if(showMigas && estadoRonly.tieneMiga(row,col)) {
					offgc.drawString("M",x+24 + (col*48),y+24 + (row * 48));
				}
				
				// El siguiente Switch evalua si en la posición actual hay una llave o un candado. Si lo hay setea la variable
				//elemento con la imagen correspondiente

				switch (laberinto.getCelda(row, col) & Laberinto.TOKENS & ~(Laberinto.ES_SALIDA)){
				case Laberinto.HAY_LLAVE: {                                                                                                             //LLAVE
					if (!llave && !candado)
						elemento = new ImageIcon("images/llave.png");                                  
					break;
				}
				case Laberinto.HAY_CANDADO: {                                                                                                           //CANDADO
					if(!candado)
						elemento = new ImageIcon("images/candado.png");
					break;
				}
				}  //fin Switch

				//Si hay algun elemento, llave o candado en la posicion actual lo grafica, caso contrario no.
				if (elemento != null)
					offgc.drawImage (elemento.getImage(), x + (col*48) , y + (row * 48), this);    

				elemento = null;

			}

			// Dibuja el agente dentro del laberinto en la posicion
			offgc.drawImage (ronly.getImage(), colRonly, rowRonly, this);


		}      
		// do normal redraw
		//paint(offgc);
		// transfer offscreen to window
		g.drawImage(offscreen, 0, 0, this);

	} //fin paint


	/**
	 * Este método recorre la Lista de acciones que el agente debe llevar a cabo para salir del laberinto,
	 * y por cada una de ellas invoca al método moverSigRonly, pasandole como argumento un String que le
	 * indica la acción a realizar
	 */
	private void moverRonly() {
		for (int accion =0;accion < acciones.size(); accion++){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moverSigRonly((acciones.get(accion)).toString());
		}
	}


	/**
	 * Este método actualiza las variables que refieren a la posicion y orientacion actual del agente
	 * dentro del laberinto que este está intentando resolver.
	 *
	 * @param accion: string que representa la siguiente acción que el agente debe resolver, este puede
	 * tomar valores: girarDer, girarIzq, Avanzar.
	 */
	private void moverSigRonly(String accion) {

		boolean puedoAvanzar = true;

		if (accion.equals("GirarDer")){
			if(orientacionActual == 4){
				orientacionActual=1;
			}
			else
				orientacionActual++;
		}
		else if (accion.equals("GiraIzq")){
			if(orientacionActual == 1){
				orientacionActual= 4;
			}
			else
				orientacionActual--;
		}
		if (accion.equals("Avanzar")){
			switch (orientacionActual){
			case Orientacion.NORTE: {                                                                                                                               //NORTE
				if ((rowRonly==10 && colRonly==10)||(colRonly==((laberinto.getCols()*48)+10) && rowRonly==10))
					puedoAvanzar = false;
				else
					rowRonly-=48;
				break;
			}
			case Orientacion.ESTE:{                                                                                                                                //ESTE
				if ((rowRonly==10 && colRonly==(laberinto.getCols()*48)+10)||(colRonly==((laberinto.getCols()*48)+10) && rowRonly==(laberinto.getCols()*48)+10))
					puedoAvanzar = false;
				else
					colRonly+=48;
				break;
			}
			case Orientacion.SUR:{                                                                                                                                //SUR
				if ((colRonly==(laberinto.getCols()*48)+10 && rowRonly==(laberinto.getCols()*48)+10)||(colRonly==10 && rowRonly==(laberinto.getCols()*48)+10))
					puedoAvanzar = false;
				else
					rowRonly+=48;
				break;
			}
			case Orientacion.OESTE:{                                                                                                                                //OESTE
				if ((colRonly==10 && rowRonly==10)||(colRonly ==10 && rowRonly==(laberinto.getCols()*48)+10))
					puedoAvanzar = false;
				else
					colRonly-=48;
				break;
			}
			default:
			}      
		}

		if((laberinto.getCelda((rowRonly -10)/48, (colRonly-10)/48)& Laberinto.TOKENS & ~(Laberinto.ES_SALIDA)) == Laberinto.HAY_LLAVE){
			llave=true;
		}

		//si hay un candado en la posicion cambia el valor de la variable candado para que este no se dibuje mas.
		if(((laberinto.getCelda((rowRonly -10)/48, (colRonly-10)/48)& Laberinto.TOKENS & ~(Laberinto.ES_SALIDA)) == Laberinto.HAY_CANDADO)&& llave){
			candado=true;
			llave=false;
		}

		if(puedoAvanzar){
			primerRonly=false;
			repaint();
		}
		else
			System.out.println("La acción " + accion + "no es una acción válida");

	}



	public void runEventHandler(EventType eventType, Object[] params) {

		this.repaint();
		
	}


	/**
	 * Este método muestra una ventana emergente con el mensaje "Ronly ha
	 * superado todos los laberintos.Ha cumplido su objetivo!" si es que el
	 * agente logró resolver el último laberinto del conjunto propuesto
	 * En caso contrario muestra una ventana emergente con el mensaje "Nivel
	 * superado!" Indicando que el agente logró pasar el nivel actual y
	 * continuará con el ciclo percepción-acción para poder cumplir su objetivo.  
	 *
	 * @param ultimoNivel: valor booleano que indica si el nivel que el agente está
	 * intentando resolver es el último.
	 */

	private void dibujarNivelSuperado(boolean ultimoNivel) {


		//Si es el ultimo nivel se muestra un mensaje indicando que se ha cumplido el objetivo (superar
		//un conjunto de laberintos propuestos) y el programa termina su ejecucion.
		//En caso contrario se muestra un mensaje indicando que el nivel ha sido superado, y se continua
		//con la ejecucion del proximo laberinto

		if (ultimoNivel){
			JOptionPane.showMessageDialog(null,"",

					"Fin de juego", JOptionPane.PLAIN_MESSAGE, new ImageIcon("images/RonlyGanador.png"));

		}
		else{
			JOptionPane.showMessageDialog(null,"Nivel Superado!");

		}


	} // fin dibujarNivelSuperado

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public int getSleepTime() {
		return sleepTime;
	}
	public void setShowMigas(boolean showMigas) {
		this.showMigas = showMigas;
	}

}