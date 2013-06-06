package frsf.cidisi.exercise.interfaz;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import frsf.cidisi.exercise.datastructures.Laberinto;
import frsf.cidisi.exercise.datastructures.PairInt;
import frsf.cidisi.exercise.tp1.search.RonlyAgentPerception;
import frsf.cidisi.exercise.tp1.search.RonlyEstado;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.simulator.events.EventHandler;
import frsf.cidisi.faia.simulator.events.EventType;

public class EscenarioRonly extends JPanel implements EventHandler {
	private static final long serialVersionUID = 1L;

	private Graphics graphics;

	// Almacena la percepcion actual
	private RonlyAgentPerception percepcion;

	// Almacenan la posicion actual de Ronly
	private int rowRonly;
	private int colRonly;
	private int orientacion;

	// Determina si debo o no imprimir los objetos
	private boolean llave;
	private boolean candado;

	// Mientras sea true, dibujo a Ronly pensante
	private boolean firstAction;

	// Imagenes
	Hashtable<Integer, ImageIcon> images;
	
	Graphics offgc;
	Image    offscreen = null;

	public EscenarioRonly() {
		super();
		percepcion = null;

		// Inicializo las imagenes
		images = new Hashtable<Integer, ImageIcon>();
		images.put(0x00, new ImageIcon("images/0.png"));
		images.put(0x01, new ImageIcon("images/1.png"));
		images.put(0x02, new ImageIcon("images/2.png"));
		images.put(0x03, new ImageIcon("images/3.png"));
		images.put(0x04, new ImageIcon("images/4.png"));
		images.put(0x05, new ImageIcon("images/5.png"));
		images.put(0x06, new ImageIcon("images/6.png"));
		images.put(0x07, new ImageIcon("images/7.png"));
		images.put(0x08, new ImageIcon("images/8.png"));
		images.put(0x09, new ImageIcon("images/9.png"));
		images.put(0x0A, new ImageIcon("images/A.png"));
		images.put(0x0B, new ImageIcon("images/B.png"));
		images.put(0x0C, new ImageIcon("images/C.png"));
		images.put(0x0D, new ImageIcon("images/D.png"));
		images.put(0x0E, new ImageIcon("images/E.png"));
		// images.put(0x00, new ImageIcon("images/F.png"));

		// Ronly
		images.put(0x100, new ImageIcon("images/RonlyAtras.png"));
		images.put(0x101, new ImageIcon("images/RonlyEste.png"));
		images.put(0x102, new ImageIcon("images/Ronly.png"));
		images.put(0x103, new ImageIcon("images/RonlyOeste.png"));

		images.put(0x200, new ImageIcon("images/RonlyAtrasLlave.png"));
		images.put(0x201, new ImageIcon("images/RonlyEsteLlave.png"));
		images.put(0x202, new ImageIcon("images/RonlyFrenteLlave.png"));
		images.put(0x203, new ImageIcon("images/RonlyOesteLlave.png"));

		// create the offscreen buffer and associated Graphics
		offscreen = createImage(this.getSize().width, this.getSize().height);
		//offgc = offscreen.getGraphics();
	}

	/* ********************************************************************** */
	/* Metodos redefinidos del canvas *************************************** */
	/* ********************************************************************** */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(offscreen, 0, 0, this);
	}

	/* ********************************************************************** */
	/* Manejador de eventos recibidos del simulador ************************* */
	/* ********************************************************************** */
	@Override
	public void runEventHandler(EventType eventType, Object[] params) {

		switch(eventType) {
		case PerceptionRecived:
			percepcion = (RonlyAgentPerception) params[0];
			
			firstAction = true;
			cargarPercepcion(percepcion);
			
			break;
		case IterationFinished:
			if(percepcion==null) break;
			List<Action> acciones = (List<Action>) params[0];

			animarRonly(acciones);
			//dibujarNivelSuperado(percepcion.getUltimoNivel());
			break;
		default:
		}
	}

	/* ********************************************************************** */
	/* Metodos privados del componente ************************************** */
	/* ********************************************************************** */
	/**
	 * Create the panel.
	 */

	private void cargarPercepcion(RonlyAgentPerception percepcion) {
		// Se considera a efectos de la reolución de este TP que la orientación inicial
		// siempre es hacia el Este
		orientacion= RonlyEstado.ESTE;

		// Se obtiene el laberinto a resolver y la secuencia de acciones que el agente
		// debe llevar a cabo para poder salir del mismo
		Laberinto laberinto = percepcion.getLaberinto();
		//acciones = listaAcciones;

		/*Seteamos los valores de la posición inicial de Ronly */
		rowRonly = 30 + (percepcion.getPosInicial().getFirst()  * 48);
		colRonly = 10 + (percepcion.getPosInicial().getSecond() * 48);

		llave   = false;
		candado = false;

		//Se crea la panel principal donde se graficará el laberinto a resolver por el agente
		this.setBounds(0,0,((laberinto.getCols()*48)+17),((laberinto.getRows()*48)+37));
	}
	
	private void dibujarNivel() {
		Laberinto laberinto = percepcion.getLaberinto();
	
		// create the offscreen buffer and associated Graphics
		offscreen = createImage(this.getSize().width, this.getSize().height);
		offgc = offscreen.getGraphics();

		ImageIcon imagen=null;
		ImageIcon elemento=null;

		//se setea la posicion inicial para comenzar a dibujar
		int x = 10;
		int y = 30;

		//se obtiene el tamaño del laberinto

		int filas = laberinto.getRows();
		int columnas = laberinto.getCols();

		//se recorre el laberinto para dibujar en cada par (row, col) la imagen que representa las paredes que
		//tiene indicadas esa celda.
		for (int row=0;row < filas; row++){
			for (int col =0; col < columnas; col ++){
				// Imprimo Laberinto **********************
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
		}
		// Imprimo Objetos ************************

		// Imprimo Ronly  *************************
		// Dibuja el agente dentro del laberinto en la posicion
		ImageIcon ronly=null;
		if (firstAction){
			ronly = new ImageIcon("images/RonlyPensando.png");
			firstAction = false;
		}
		else if (!llave && !firstAction){
			switch (orientacion){
			case 1: {                                                                                       //Ronly mira al norte
				ronly = new ImageIcon("images/RonlyAtras.png");
				break;
			}
			case 2: {                                                                                       //Ronly mira al este
				ronly = new ImageIcon("images/RonlyEste.png");
				break;
			}
			case 3: {                                                                                       //Ronly mira al sur
				ronly = new ImageIcon("images/Ronly.png");
				break;
			}
			case 4: {                                                                                       //Ronly mira al oeste
				ronly = new ImageIcon("images/RonlyOeste.png");
				break;
			}

			}
		}
		else {
			switch (orientacion){
			case 1: {                                                                                       //Ronly mira al norte
				ronly = new ImageIcon("images/RonlyAtrasLlave.png");

				break;
			}
			case 2: {                                                                                       //Ronly mira al este
				ronly = new ImageIcon("images/RonlyEsteLlave.png");
				break;
			}
			case 3: {                                                                                       //Ronly mira al sur
				ronly = new ImageIcon("images/RonlyFrenteLlave.png");
				break;
			}
			case 4: {                                                                                       //Ronly mira al oeste
				ronly = new ImageIcon("images/RonlyOesteLlave.png");
				break;
			}

			}
		}
		offgc.drawImage (ronly.getImage(), colRonly, rowRonly, this);
        
		// Dibujo el nuevo laberinto
		this.repaint();
	}

    /**
     * Este método recorre la Lista de acciones que el agente debe llevar a cabo para salir
     * del laberinto, y por cada una de ellas invoca al método moverSigRonly, pasandole como
     * argumento un String que le indica la acción a realizar
     */
	private void animarRonly(List<Action> acciones) {
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
            Laberinto laberinto = percepcion.getLaberinto();
            
            if (accion.equals("GirarDer")){
                    if(orientacion == 3){
                    	orientacion=0;
                    }
                    else
                    	orientacion++;
            }
            else if (accion.equals("GiraIzq")){
                    if(orientacion == 0){
                    	orientacion = 3;
                    }
                    else
                    	orientacion--;
            }
            if (accion.equals("Avanzar")){
                    switch (orientacion){
                    case 0: {                                                                                                                               //NORTE
                            if ((rowRonly==10 && colRonly==10)||(colRonly==((laberinto.getCols()*48)+10) && rowRonly==10))
                                    puedoAvanzar = false;
                            else
                                    rowRonly-=48;
                            break;
                    }
                    case 1:{                                                                                                                                //ESTE
                            if ((rowRonly==10 && colRonly==(laberinto.getCols()*48)+10)||(colRonly==((laberinto.getCols()*48)+10) && rowRonly==(laberinto.getCols()*48)+10))
                                    puedoAvanzar = false;
                            else
                                    colRonly+=48;
                            break;
                    }
                    case 2:{                                                                                                                                //SUR
                            if ((colRonly==(laberinto.getCols()*48)+10 && rowRonly==(laberinto.getCols()*48)+10)||(colRonly==10 && rowRonly==(laberinto.getCols()*48)+10))
                                    puedoAvanzar = false;
                            else
                                    rowRonly+=48;
                            break;
                    }
                    case 3:{                                                                                                                                //OESTE
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
                    repaint();
            }
            else
                    System.out.println("La acción " + accion + "no es una acción válida");
           
    }
    
	private void avanzar() {
		// Rectangle r = getAffectedArea(oldX, oldY, imageX, imageY, imageWidth,
		// imageHeight);
		// repaint(r);
	}

	private void girarIzquierda() {

	}

	private void girarDerecha() {

	}

}
