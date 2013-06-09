package frsf.cidisi.exercise.tp2.situationCalculus;

import java.util.Hashtable;

import frsf.cidisi.exercise.datastructures.Laberinto;
import frsf.cidisi.exercise.datastructures.PairInt;
import frsf.cidisi.exercise.tp2.situationCalculus.actions.Salir;
import frsf.cidisi.faia.agent.Action;
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
		super("kb/ronly5.pl");

		this.initState();
	}

	@Override
	public void initState() {
		
		// Inicializamos la KB con el nivel actual (0)
		this.addKnowledge(this.getLevelPredicate() + "(1)");
		this.addKnowledge("en(0,0,0)");
		this.addKnowledge("orientacion(1,0)");
		
		
	}

	@Override
	public void tell(Action action) {
		if(action.getClass() == Salir.class) {
			advanceToNextLevel();
		}
		super.tell(action);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
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
		return "nivelActual";
	}

	// /////////////////////////////////////////////////////////////////////////

	@Override
	public ActionFactory getActionFactory() {
		return RonlyAgentActionFactory.getInstance();
	}

	@Override
	public String getSituationPredicate() {
		return "situacionActual";
	}

	@Override
	public String getBestActionPredicate() {
		return "bestAction";
	}

	@Override
	public String getExecutedActionPredicate() {
		return "executedAction";
	}

	@Override
	public void updateState(Perception p) {
		this.tell((SituationCalculusPerception) p);
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();


		
		//if(this.getSituation() > 68) {
		//	String query = "listing(estado)";
		//	Hashtable[] result = this.prologConnector.query(query);
		//	for(Object o: result)
		//		str.append(query + ": " + o + "\n");
		//}
			
			str.append("\n  Situacion: " + this.getSituation());
			str.append("\n  Nivel: " + this.getLevel());
			str.append("\n  Posicion: " + this.getPosicion());
			str.append("\n  Orientacion: " + this.getOrientacion());
			str.append("\n  Tengo llave?: " + this.tieneLlave());
			str.append("\n  pasoPor(" + this.getPosicion().getSecond() + "," + this.getPosicion().getFirst()+",C,"+this.getLevel()+")\n");
			if(this.queryHasSolution("pasoPor(" + this.getPosicion().getSecond() + "," + this.getPosicion().getFirst()+",C,"+this.getLevel()+")"))
				str.append("\n  Cant. de veces que pasó: " + this.prologConnector.query("pasoPor(" + this.getPosicion().getSecond() + "," + this.getPosicion().getFirst()+",C,"+this.getLevel()+")")[0].get("C"));
			else
				str.append("\n  Cant. de veces que pasó: 0");

			
			str.append("\n   ");
		/*
		String query = "paredAdelante(" + this.getPosicion().getSecond() +","+ this.getPosicion().getFirst() +")";
		str.append(query + ": " + this.prologConnector.queryHasSolution(query) + "\n");
		
		String query2 = "paredIzquierda(" + this.getPosicion().getSecond() +","+ this.getPosicion().getFirst() +")";
		str.append(query2 + ": " + this.prologConnector.queryHasSolution(query2) + "\n");
		
		String query3 = "bestAction(A,"+ this.getSituation()+")";
		Hashtable[] result = this.query(query3);
		str.append(query3 + ": " + result[0].get("A") + "\n");
		
		if(this.getSituation() == 7) {
		String query4 = "listing";
		Hashtable[] result1 = this.query(query4);
		str.append(query4 + ": " + result1[0] + "\n");
		System.exit(0);
		}
		
		//String query = "";
		
		str.append("\n   ");
		//str.append(laberinto.toString());
*/
		return str.toString();
	}

	// The following methods are agent-specific:
	public PairInt getPosicion() {
		String positionQuery = "en(X,Y," + this.getSituation() + ")";

		Hashtable[] result = this.query(positionQuery);
		
		int col = Integer.parseInt(result[0].get("X").toString());
		int row = Integer.parseInt(result[0].get("Y").toString());
		
		return new PairInt(row, col);
	}

	public int getOrientacion() {
		String positionQuery = "orientacion(O," + this.getSituation() + ")";

		Hashtable[] result = this.query(positionQuery);

		return Integer.parseInt(result[0].get("O").toString());
	}

	private boolean tieneLlave() {
		String keyStateQuery = "sostiene(" + this.getSituation() + ")";

		return this.queryHasSolution(keyStateQuery);
	}

	private PairInt getPosicionLlave() {
		String positionQuery = "hayLlave(X,Y," + this.getLevel() + ")";

		Hashtable[] result = this.query(positionQuery);

		int row = Integer.parseInt(result[0].get("Y").toString());
		int col = Integer.parseInt(result[0].get("X").toString());

		return new PairInt(row, col);
	}
}
