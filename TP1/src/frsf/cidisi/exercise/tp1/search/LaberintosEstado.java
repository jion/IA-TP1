package frsf.cidisi.exercise.tp1.search;

import java.util.ArrayList;
import java.util.List;

import frsf.cidisi.exercise.tp1.datastructures.Laberinto;
import frsf.cidisi.exercise.tp1.datastructures.PairInt;
import frsf.cidisi.faia.state.EnvironmentState;

/**
 * This class represents the real world state.
 */
public class LaberintosEstado extends EnvironmentState {
	
    private static final char[][] nivel1 = {
				{0x83, 0x09, 0x07, 0x01, 0x03, 0x09, 0x05, 0x03, 0x0B, 0x0D},
				{0x0D, 0x04, 0x03, 0x08, 0x0D, 0x0E, 0x04, 0x01, 0x03, 0x08},
				{0x0C, 0x06, 0x09, 0x0E, 0x0C, 0x05, 0x08, 0x06, 0x0B, 0x0C},
				{0x06, 0x03, 0x00, 0x03, 0x0A, 0x0E, 0x06, 0x03, 0x09, 0x0C},
				{0x0D, 0x0D, 0x0C, 0x05, 0x01, 0x03, 0x03, 0x09, 0x0C, 0x0C},
				{0x06, 0x08, 0x0C, 0x0E, 0x0C, 0x0D, 0x0D, 0x0C, 0x0C, 0x0E},
				{0x05, 0x02, 0x02, 0x03, 0x08, 0x06, 0x08, 0x0C, 0x06, 0x09},
				{0x0C, 0x07, 0x09, 0x0D, 0x0C, 0x05, 0x08, 0x04, 0x01, 0x08},
				{0x04, 0x01, 0x0A, 0x06, 0x02, 0x0A, 0x0E, 0x0C, 0x1E, 0x0E},
				{0x0E, 0x06, 0x03, 0x03, 0x03, 0x03, 0x0B, 0x06, 0x03, 0x63}};
	
    private static final char[][] nivel2 = { 
	    		{0x05, 0x03, 0x01, 0x03, 0x09, 0x0D, 0x07, 0x03, 0x03, 0x09},
				{0x06, 0x0B, 0x0C, 0x17, 0x08, 0x04, 0x03, 0x03, 0x03, 0x0A},
				{0x05, 0x01, 0x02, 0x0B, 0x0C, 0x06, 0x09, 0x05, 0x03, 0x43},
				{0x0E, 0x0C, 0x07, 0x01, 0x02, 0x01, 0x0A, 0x0C, 0x05, 0x09},
				{0x05, 0x02, 0x0B, 0x0C, 0x05, 0x0A, 0x0D, 0x0C, 0x0C, 0x0C},
				{0x0C, 0x07, 0x03, 0x08, 0x0C, 0x07, 0x00, 0x02, 0x0A, 0x0E},
				{0x0C, 0x05, 0x09, 0x0C, 0x06, 0x03, 0x08, 0x05, 0x03, 0x0B},
				{0x06, 0x08, 0x0C, 0x06, 0x09, 0x05, 0x0A, 0x0C, 0x05, 0x63},
				{0x05, 0x02, 0x0A, 0x0D, 0x0C, 0x06, 0x03, 0x02, 0x08, 0x0D},
				{0x82, 0x03, 0x03, 0x0A, 0x06, 0x03, 0x03, 0x03, 0x02, 0x0A}};

    private static final char[][] nivel3 = { 
				{0x05, 0x03, 0x03, 0x01, 0x03, 0x03, 0x03, 0x03, 0x03, 0x63},
				{0x06, 0x03, 0x0B, 0x04, 0x03, 0x03, 0x03, 0x03, 0x03, 0x09},
				{0x83, 0x09, 0x0D, 0x06, 0x03, 0x03, 0x01, 0x01, 0x09, 0x0C},
				{0x0D, 0x0C, 0x06, 0x03, 0x03, 0x03, 0x0A, 0x0C, 0x0C, 0x0C},
				{0x04, 0x00, 0x03, 0x01, 0x03, 0x03, 0x03, 0x08, 0x0C, 0x0C},
				{0x0C, 0x0C, 0x05, 0x08, 0x0D, 0x05, 0x09, 0x0E, 0x0C, 0x0C},
				{0x0E, 0x0C, 0x0C, 0x0C, 0x0C, 0x0C, 0x06, 0x03, 0x0A, 0x0C},
				{0x83, 0x0A, 0x1E, 0x0C, 0x04, 0x08, 0x05, 0x01, 0x09, 0x0C},
				{0x05, 0x03, 0x03, 0x0A, 0x0E, 0x0C, 0x0E, 0x0C, 0x0C, 0x0C},
				{0x06, 0x03, 0x0B, 0x07, 0x03, 0x02, 0x03, 0x0A, 0x06, 0x0A}};
    
    /* NIVELES XXS **********************************************************/
	private static final char[][] nivelXXS1 = { 
    		{0x83, 0x09, 0x07, 0x09},
			{0x0D, 0x04, 0x03, 0x08},
			{0x0C, 0x06, 0x09, 0x0E},
			{0x06, 0x13, 0x02, 0x63}};
    
	private static final char[][] nivelXXS2 = {
    		{0x0D,0x05,0x09,0x0D},
    		{0x06,0x08,0x06,0x42},
    		{0x05,0x02,0x1B,0x65},
    		{0x82,0x03,0x03,0x0A}};
	private static final char[][] nivelXXS3 = {
    		{0x07, 0x01, 0x09, 0x65},
			{0x83, 0x08, 0x04, 0x0A},
		    {0x81, 0x08, 0x06, 0x09},
			{0x1E, 0x06, 0x03, 0x0A}};
   
    /* NIVELES DE PRUEBA *****************************************************/
    /*
	private static final char[][] nivelX = {
		    	{0x85,0x01,0x09},
		    	{0x0C,0x0C,0x0C},
		    	{0x0E,0x1E,0x6E}};
    
    private static final char[][] nivelY = {
		    	{0x05,0x01,0x03,0x03,0x6B},
		    	{0x04,0x00,0x03,0x01,0x09},
		    	{0x84,0x0A,0x05,0x00,0x08},
		    	{0x0C,0x0D,0x0C,0x04,0x08},
		    	{0x06,0x0A,0x1E,0x06,0x0A}};
    */
    
    
    private List<Laberinto> laberintos;		// Conjunto de laberintos
    private PairInt			posRonly;		// whereis ronly?
    private int 			nivelActual;	// Nivel donde est� el agente
	
    public LaberintosEstado() {
    	super();
    	
    	laberintos = new ArrayList<Laberinto>();
        this.initState();
    }

    /**
     * This method is used to setup the initial real world.
     */
    @Override
    public void initState() {
    	nivelActual=0;
    	laberintos.add(new Laberinto(nivelXXS1));
    	laberintos.add(new Laberinto(nivelXXS2));
    	laberintos.add(new Laberinto(nivelXXS3));
    	
    	laberintos.add(new Laberinto(nivel1));
    	laberintos.add(new Laberinto(nivel2));
    	laberintos.add(new Laberinto(nivel3));
    	
    	
    	posRonly = laberintos.get(0).getPosEntradas().get(0).clone();
    }

    /**
     * String representation of the real world state.
     */
    @Override
    public String toString() {

        return "Nivel: " + this.getNivelActual();
    }

    // The following methods are agent-specific:
    /* Getters & Setters *****************************************************/
	/* Relacionado con los niveles */
    public int getNivelActual(){
		return nivelActual;
	} 
	
	public Laberinto getLaberintoActual(){
		return laberintos.get(nivelActual);
	}
     
	public void pasarNivel(){
		/* TODO: Aca deberia pasarsele el conjunto de acciones para
		 * que ronly pase de nivel? O seria mejor dejar que eso se
		 * haga en el avanzar y que el avance de nivel se de al hacer
		 * la nueva percepcion? (Me gusta mas la 2da)
		 */
		nivelActual++;
		this.posRonly.setSecond(0);
	}
     
	public Boolean isUltimoNivel() {
		return (nivelActual == (laberintos.size()-1));
	}

	/* Posicionamiento del agente */
	public PairInt getPosRonly() {
		return posRonly;
	}

	public void setPosRonly(PairInt posRonly) {
		this.posRonly = posRonly;
	}

	public void setPosRonly(int row, int col) {
		if(this.posRonly == null)
			{ this.posRonly = new PairInt(row,col); }
		else {
			this.posRonly.setFirst(row);
			this.posRonly.setSecond(col);
		}
	}
	
	public boolean isRonlyOnExit() {
		return getLaberintoActual().getPosSalidas().contains(this.getPosRonly());
	}
}

