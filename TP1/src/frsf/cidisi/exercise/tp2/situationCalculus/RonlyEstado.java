package frsf.cidisi.exercise.tp2.situationCalculus;

import java.util.Hashtable;

import frsf.cidisi.exercise.tp1.datastructures.Laberinto;
import frsf.cidisi.exercise.tp1.datastructures.PairInt;
import frsf.cidisi.faia.agent.ActionFactory;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.situationcalculus.KnowledgeBase;
import frsf.cidisi.faia.agent.situationcalculus.SituationCalculusPerception;
import frsf.cidisi.faia.exceptions.PrologConnectorException;

public class RonlyEstado extends KnowledgeBase {

	public static final int NORTE = 0;
	public static final int ESTE = 1;
	public static final int SUR = 2;
	public static final int OESTE = 3;

	// Posibles acciones del Agente
	public static final int GIR_IZQ = 1;
	public static final int GIR_DER = 2;
	public static final int AVANZAR = 3;

	// Laberinto actual en la KB.
	Laberinto laberinto;
	
	
	public RonlyEstado() throws PrologConnectorException {
		// Inicializa la base de conocimientos con el archivo prolog
		super("kb/ronly.pl");

		this.initState();
	}

	@Override
	public void initState() {
		// TODO: Que debo definir al iniciar mi KB?
		// Predicados utilizados?
		// Posiciones iniciales?
		
		
		/*
		this.posicion = new PairInt(0, 0);
		this.posLlave = new PairInt(0, 0);
		this.posSalidas = new ArrayList<PairInt>();

		// En realidad el agente nunca sabra su posicion real actual hasta no
		// realizar la primera percepcion.
		this.posicion.setPair(0, 0);
		this.orientacion = ESTE;

		this.llave = false;

		this.posLlave.setPair(0, 0);
		this.posSalidas.clear();
		this.ultimoNivel = false;
		this.cantDeGiros = 0;
		*/
	}

	// /////////////////////////////////////////////////////////////////////////
	public void advanceToNextLevel() {
		int level = this.getLevel();

		// Remove current situation predicate
		this.removeKnowledge(this.getLevelPredicate() + "(" + level + ")");

		// Advance to next situation and add the new situation predicate
		level = level + 1;
		this.addKnowledge(this.getLevelPredicate() + "(" + level + ")");
	}

	/**
	 * Devuelve el nivel actual de la Knowledge Base
	 * 
	 * @return
	 */
	public int getLevel() {
		String query = this.getLevelPredicate() + "(L)";

		Hashtable[] pos = this.query(query);

		int l = Integer.parseInt(pos[0].get("L").toString());

		return l;
	}

	public String getLevelPredicate() {
		return "levelSituation";
	}

	// /////////////////////////////////////////////////////////////////////////

	@Override
	public ActionFactory getActionFactory() {
		return RonlyAgentActionFactory.getInstance();
	}

	@Override
	public String getSituationPredicate() {
		return "actualSituation";
	}

	@Override
	public String getBestActionPredicate() {
		return "bestAction";
	}

	@Override
	public String getExecutedActionPredicate() {
		return "action";
	}

	@Override
	public void updateState(Perception p) {
		this.tell((SituationCalculusPerception) p);
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();

		str.append("\n  Posicion: " + this.getPosicion());
		str.append("\n  Orientacion: " + this.getOrientacion());
		str.append("\n  Tengo llave?: " + this.tieneLlave());
		str.append("\n  Laberinto percibido: ");
		str.append("\n   ");
		str.append(laberinto.toString());

		return str.toString();
	}

	// The following methods are agent-specific:
	public PairInt getPosicion() {
		String positionQuery = "position(X,Y," + this.getSituation() + ")";

		Hashtable[] result = this.query(positionQuery);

		int x = Integer.parseInt(result[0].get("X").toString());
		int y = Integer.parseInt(result[0].get("Y").toString());

		return new PairInt(x, y);
	}

	public int getOrientacion() {
		String positionQuery = "orientation(O," + this.getSituation() + ")";

		Hashtable[] result = this.query(positionQuery);

		return Integer.parseInt(result[0].get("O").toString());
	}

	private boolean tieneLlave() {
		String keyStateQuery = "sostiene(llave," + this.getSituation() + ")";

		return this.queryHasSolution(keyStateQuery);
	}

	private PairInt getPosicionLlave() {
		String positionQuery = "en(llave,X,Y," + this.getSituation() + ")";

		Hashtable[] result = this.query(positionQuery);

		int x = Integer.parseInt(result[0].get("X").toString());
		int y = Integer.parseInt(result[0].get("Y").toString());

		return new PairInt(x, y);
	}
}
