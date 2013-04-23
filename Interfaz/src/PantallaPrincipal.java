import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class PantallaPrincipal extends JFrame {

    private JPanel contentPane;
    private int[][] laberinto = {{0x03, 0x09, 0x07, 0x01, 0x03, 0x09, 0x05, 0x03, 0x0B, 0x0D},
    		{0x0D, 0x04, 0x03, 0x08, 0x0D, 0x0E, 0x04, 0x01, 0x03, 0x08},
    		{0x0C, 0x06, 0x09, 0x0E, 0x0C, 0x05, 0x08, 0x06, 0x0B, 0x0C},
    		{0x06, 0x03, 0x00, 0x03, 0x0A, 0x0E, 0x06, 0x03, 0x09, 0x0C},
    		{0x0D, 0x0D, 0x0C, 0x05, 0x01, 0x03, 0x03, 0x09, 0x0C, 0x0C},
    		{0x06, 0x08, 0x0C, 0x0E, 0x0C, 0x0D, 0x0D, 0x0C, 0x0C, 0x0E},
    		{0x05, 0x02, 0x02, 0x03, 0x08, 0x06, 0x08, 0x0C, 0x06, 0x09},
    		{0x0C, 0x07, 0x09, 0x0D, 0x0C, 0x05, 0x08, 0x04, 0x01, 0x08},
    		{0x04, 0x01, 0x0A, 0x06, 0x02, 0x0A, 0x0E, 0x0C, 0x1E, 0x0E},
    		{0x0E, 0x06, 0x03, 0x03, 0x03, 0x03, 0x0B, 0x06, 0x03, 0x63}}; 
    	
    	/*{  {0x05, 0x03, 0x03, 0x01, 0x03, 0x03, 0x03, 0x03, 0x03, 0x63},
			{0x06, 0x03, 0x0B, 0x04, 0x03, 0x03, 0x03, 0x03, 0x03, 0x09},
			{0x03, 0x09, 0x0D, 0x06, 0x03, 0x03, 0x01, 0x01, 0x09, 0x0C},
			{0x0D, 0x0C, 0x06, 0x03, 0x03, 0x03, 0x0A, 0x0C, 0x0C, 0x0C},
			{0x04, 0x00, 0x03, 0x01, 0x03, 0x03, 0x03, 0x08, 0x0C, 0x0C},
			{0x0C, 0x0C, 0x05, 0x08, 0x0D, 0x05, 0x09, 0x0E, 0x0C, 0x0C},
			{0x0E, 0x0C, 0x0C, 0x0C, 0x0C, 0x0C, 0x06, 0x03, 0x0A, 0x0C},
			{0x03, 0x0A, 0x1E, 0x0C, 0x04, 0x08, 0x05, 0x01, 0x09, 0x0C},
			{0x05, 0x03, 0x03, 0x0A, 0x0E, 0x0C, 0x0E, 0x0C, 0x0C, 0x0C},
			{0x06, 0x03, 0x0B, 0x07, 0x03, 0x02, 0x03, 0x0A, 0x06, 0x0A}};*/
    
    
    int xRonly = 100;
	int yRonly = 100;
	
	int contador =1;
	
	int orientacionActual = 2;

	String[] acciones = {"Avanzar", "GirarDer", "Avanzar", "Avanzar", "GiraIzq", "Avanzar", "GirarDer", "Avanzar", "Avanzar", "Avanzar", "Avanzar", "GiraIzq", "Avanzar", "Avanzar", "GiraIzq", "Avanzar", "Avanzar", "GirarDer", "Avanzar", "Avanzar", "Avanzar", "GirarDer", "Avanzar", "Avanzar", "Avanzar", "GiraIzq", "Avanzar", "GirarDer", "Avanzar", "GirarDer", "GirarDer", "Avanzar", "GiraIzq", "Avanzar", "GiraIzq", "Avanzar", "Avanzar", "GiraIzq", "Avanzar", "Avanzar"};
    	
    	
    	/*{  {0x05, 0x03, 0x01, 0x03, 0x09, 0x0D, 0x07, 0x03, 0x03, 0x09},
    		{0x06, 0x0B, 0x0C, 0x17, 0x08, 0x04, 0x03, 0x03, 0x03, 0x0A},
    		{0x05, 0x01, 0x02, 0x0B, 0x0C, 0x06, 0x09, 0x05, 0x03, 0x43},
    		{0x0E, 0x0C, 0x07, 0x01, 0x02, 0x01, 0x0A, 0x0C, 0x05, 0x09},
    		{0x05, 0x02, 0x0B, 0x0C, 0x05, 0x0A, 0x0D, 0x0C, 0x0C, 0x0C},
    		{0x0C, 0x07, 0x03, 0x08, 0x0C, 0x07, 0x00, 0x02, 0x0A, 0x0E},
    		{0x0C, 0x05, 0x09, 0x0C, 0x06, 0x03, 0x08, 0x05, 0x03, 0x0B},
    		{0x06, 0x08, 0x0C, 0x06, 0x09, 0x05, 0x0A, 0x0C, 0x05, 0x63},
    		{0x05, 0x02, 0x0A, 0x0D, 0x0C, 0x06, 0x03, 0x02, 0x08, 0x0D},
    		{0x02, 0x03, 0x03, 0x0A, 0x06, 0x03, 0x03, 0x03, 0x02, 0x0A}};*/
    	
    		/* Nivel 0:  {{0x03, 0x09, 0x07, 0x01, 0x03, 0x09, 0x05, 0x03, 0x0B, 0x0D},
    		{0x0D, 0x04, 0x03, 0x08, 0x0D, 0x0E, 0x04, 0x01, 0x03, 0x08},
    		{0x0C, 0x06, 0x09, 0x0E, 0x0C, 0x05, 0x08, 0x06, 0x0B, 0x0C},
    		{0x06, 0x03, 0x00, 0x03, 0x0A, 0x0E, 0x06, 0x03, 0x09, 0x0C},
    		{0x0D, 0x0D, 0x0C, 0x05, 0x01, 0x03, 0x03, 0x09, 0x0C, 0x0C},
    		{0x06, 0x08, 0x0C, 0x0E, 0x0C, 0x0D, 0x0D, 0x0C, 0x0C, 0x0E},
    		{0x05, 0x02, 0x02, 0x03, 0x08, 0x06, 0x08, 0x0C, 0x06, 0x09},
    		{0x0C, 0x07, 0x09, 0x0D, 0x0C, 0x05, 0x08, 0x04, 0x01, 0x08},
    		{0x04, 0x01, 0x0A, 0x06, 0x02, 0x0A, 0x0E, 0x0C, 0x1E, 0x0E},
    		{0x0E, 0x06, 0x03, 0x03, 0x03, 0x03, 0x0B, 0x06, 0x03, 0x63}};*/
    	
    	
    	
    		
    	
    	
    	
    	
    	
    	
    
    public static final int PARED_ARRIBA = 0x01;
	public static final int PARED_ABAJO = 0x02;
	public static final int PARED_IZQUIERDA = 0x04;
	public static final int PARED_DERECHA = 0x08;
	public static final int HAY_LLAVE = 0x10;
	public static final int HAY_CANDADO = 0x20;
	public static final int ES_SALIDA = 0x40;
	public static final int ES_ENTRADA = 0x80;
	public static final int SIN_PAREDES = 0x00;
	public static final int PAREDES = 0x0F;
	public static final int COSITAS = 0xF0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	PantallaPrincipal frame = new PantallaPrincipal();
    	frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    	//frame.repaint();
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        frame.moverRonly();
    }

    /**
     * Create the frame.
     */
    public PantallaPrincipal() {
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setOpaque(false);
        setContentPane(contentPane);
        setBounds(0,0,800,600);
        
    }

    public void paint (Graphics g)
    {    	
    	ImageIcon imagen = null;
    	ImageIcon ronly = null;
    	ImageIcon elemento = null;
    	
    	
    	switch (orientacionActual){
    	case 1: {											//Ronly mira al norte
    		ronly = new ImageIcon("src/RonlyAtras.png");
    		break;
    	}
    	case 2: {											//Ronly mira al este
    		ronly = new ImageIcon("src/RonlyEste.png");
    		break;
    	}
    	case 3: {											//Ronly mira al sur
    		ronly = new ImageIcon("src/Ronly.png");
    		break;
    	}
    	case 4: {											//Ronly mira al oeste
    		ronly = new ImageIcon("src/RonlyOeste.png");
    		break;
    	}
    	
    	}
    	

    	int x = 100;
    	int y = 100;
    	    	
    	int dimension = laberinto[0].length;
    	
    	for (int fila=0;fila < dimension; fila++){
    		for (int columna =0; columna < dimension; columna ++){
    			
    			switch (laberinto[fila][columna] & PAREDES){
    			case 0x00:{														//SIN_PAREDES
    				imagen = new ImageIcon("src/0.png");
    				break;
    			}
    			case 0x01:{														//PARED_ARRIBA
    				imagen = new ImageIcon("src/1.png");
    				break;
    			}
    			case 0x02:{														//PARED_ABAJO
    				imagen = new ImageIcon("src/2.png");
    				break;
    			}
    			case 0x03:{														//PARED_ARRIBA y PARED_ABAJO
    				imagen = new ImageIcon("src/3.png");
    				break;
    			}
    			case 0x04:{														//PARED_IZQUIERDA
    				imagen = new ImageIcon("src/4.png");
    				break;
    			}
    			case 0x05:{														//PARED_ARRIBA y PARED_IZQUIERDA
    				imagen = new ImageIcon("src/5.png");
    				break;
    			}
    			case 0x06:{														//PARED_IZQUIERDA y PARED_ABAJO
    				imagen = new ImageIcon("src/6.png");
    				break;
    			}
    			case 0x07:{														//PARED_ARRIBA, PARED_IZQUIERDA y PARED_ABAJO
    				imagen = new ImageIcon("src/7.png");
    				break;
    			}
    			case 0x08:{														//PARED_ARRIBA y PARED_DERECHA
    				imagen = new ImageIcon("src/8.png");
    				break;
    			}
    			case 0x09:{														//PARED_ARRIBA y PARED_DERECHA
    				imagen = new ImageIcon("src/9.png");
    				break;
    			}
    			case 0x0A:{														//PARED_DERECHA y PARED_ABAJO
    				imagen = new ImageIcon("src/A.png");
    				break;
    			}
    			case 0x0B:{														//PARED_ARRIBA, PARED_DERECHA y PARED_ABAJO
    				imagen = new ImageIcon("src/B.png");
    				break;
    			}
    			case 0x0C:{														//PARED_DERECHA y PARED_IZQUIERDA
    				imagen = new ImageIcon("src/C.png");
    				break;
    			}
    			case 0x0D:{														//PARED_IZQUIERDA, PARED_ARRIBA y PARED_DERECHA
    				imagen = new ImageIcon("src/D.png");
    				break;
    			}
    			case 0x0E:{														//PARED_IZQUIERDA, PARED_ABAJO Y PARED_DERECHA
    				imagen = new ImageIcon("src/E.png");
    				break;
    			}
    			}
    			
    			//evaluamos si hay esquinas que dibujar
    			
    			ImageIcon esquina1 = new ImageIcon("src/vacia.png");
    			ImageIcon esquina2 = new ImageIcon("src/vacia.png");
    			ImageIcon esquina3 = new ImageIcon("src/vacia.png");
    			ImageIcon esquina4 = new ImageIcon("src/vacia.png");
    			
    			if (!esBorde(fila, columna)){
    				
    				if ((((laberinto[fila-1][columna] & PARED_IZQUIERDA)!=0) || ((laberinto[fila][columna-1] & PARED_ARRIBA)!=0)) &&
    						(((laberinto[fila][columna] & PARED_ARRIBA) ==0) && (laberinto[fila][columna] & PARED_IZQUIERDA) == 0)){
    					System.out.println("esquinaArribaIzquierda");
    					esquina1 = new ImageIcon("src/ArribaIzquierda.png");   											//esquina arribaIzquierda
    				}
    				if ((((laberinto[fila-1][columna] & PARED_DERECHA)!=0) || ((laberinto[fila][columna+1] & PARED_ARRIBA)!=0)) && 
    						(((laberinto[fila][columna] & PARED_ARRIBA) ==0) && (laberinto[fila][columna] & PARED_DERECHA) == 0)){
    					esquina2 = new ImageIcon("src/ArribaDerecha.png");   											//esquina arribaDerecha
    					System.out.println("esquinaArribaDerecha");
    				}
    				if ((((laberinto[fila][columna+1] & PARED_ABAJO)!=0) || ((laberinto[fila+1][columna] & PARED_DERECHA)!=0)) &&
    						(((laberinto[fila][columna] & PARED_DERECHA) ==0) && (laberinto[fila][columna] & PARED_ABAJO) == 0)){
    					esquina3 = new ImageIcon("src/AbajoDerecha.png");   											//esquina arribaDerecha
    					System.out.println("esquinaAbajoDerecha");
    				}
    				if ((((laberinto[fila][columna-1] & PARED_ABAJO)!=0) || ((laberinto[fila+1][columna] & PARED_IZQUIERDA)!=0)) &&
    						(((laberinto[fila][columna] & PARED_ABAJO) ==0) && (laberinto[fila][columna] & PARED_IZQUIERDA) == 0)){
    					esquina4 = new ImageIcon("src/AbajoIzquierda.png");   											//esquina arribaDerecha
    					System.out.println("esquinaAbajoIzquierda");
    				}
    				
    				
    				
    			}
    				
    			
    			
    			g.drawImage (imagen.getImage(), x + (columna*48) , y + (fila * 48), this);
    			g.drawImage(esquina1.getImage(), x + (columna*48) , y + (fila * 48), this );
    			g.drawImage(esquina2.getImage(), x + (columna*48) , y + (fila * 48), this );
    			g.drawImage(esquina3.getImage(), x + (columna*48) , y + (fila * 48), this );
    			g.drawImage(esquina4.getImage(), x + (columna*48) , y + (fila * 48), this );
    			
    			
    			// El siguiente Switch evalua si en la posición actual hay una llave o un candado. Si lo hay setea la variable 
    			//elemento con la imagen correspondiente
    			
    			switch (laberinto[fila][columna] & COSITAS & ~(ES_SALIDA)){
    			case 0x10: {    													//LLAVE
    				elemento = new ImageIcon("src/llave.png");
    				break;
    			}
    			case 0x20: {														//CANDADO
    				elemento = new ImageIcon("src/candado.png");
    				break;
    			}
    			}  //fin Switch
    			
    			if (elemento != null)
    				g.drawImage (elemento.getImage(), x + (columna*48) , y + (fila * 48), this);    
    			
    			elemento = null;
    		}
    		
    		g.drawImage (ronly.getImage(), xRonly, yRonly, this); 
    	}    	
    	
    } //fin paint

	private boolean esBorde(int fila, int columna) {
		
		if (fila==0)
			return true;
		if(columna==0)
			return true;
		if(fila==9)
			return true;
		if (columna==9)
			return true;
		
		return false;
	}

	private void moverRonly() {
		for (int accion =0;accion < acciones.length; accion++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			moverSigRonly(acciones[accion]);
		}
	}

	
	private void moverSigRonly(String accion) {
		
		boolean puedoAvanzar = true;
		int contador = 1;
	
		if (accion.equals("GirarDer")){
			if(orientacionActual == 4){
				orientacionActual=1;
			}
			else 
				orientacionActual++;
		}
		else if (accion.equals("GiraIzq")){
			if(orientacionActual == 1){
				orientacionActual=4;
			}
			else 
				orientacionActual--;
		}
		if (accion.equals("Avanzar")){
			switch (orientacionActual){
			case 1: {																//NORTE
				if ((xRonly==100 && yRonly==100)||(xRonly==532 && yRonly==100))
					puedoAvanzar = false;
				else
					yRonly-=48;
				break;
			}
			case 2:{																//ESTE
				if ((xRonly==100 && yRonly==532)||(xRonly==532 && yRonly==532))
					puedoAvanzar = false;
				else
					xRonly+=48;
				break;
			}
			case 3:{																//SUR
				if ((xRonly==532 && yRonly==532)||(xRonly==100 && yRonly==532))
					puedoAvanzar = false;
				else
					yRonly+=48;
				break;
			}
			case 4:{																//OESTE
				if ((xRonly==100 && yRonly==100)||(xRonly ==100 && yRonly==532))
					puedoAvanzar = false;
				else
					xRonly-=48;
				break;
			}
			}	
		}
		
		if(puedoAvanzar && contador!=0){
			repaint();
		}
		
	}
}