package frsf.cidisi.exercise.tp2.situationCalculus;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.ActionFactory;

import frsf.cidisi.exercise.tp2.situationCalculus.actions.*;

public class RonlyAgentActionFactory extends ActionFactory {

	private static RonlyAgentActionFactory instance;

	private RonlyAgentActionFactory() {
	}

	public static RonlyAgentActionFactory getInstance() {
		if (instance == null) {
			instance = new RonlyAgentActionFactory();
		}
		return instance;
	}

	@Override
	protected Action stringToAction(String stringAction) {
		Action actionObject = null;

		if (stringAction.equals("girarDer")) {
			actionObject = new GirarDer();
		} else if (stringAction.equals("girarIzq")) {
			actionObject = new GiraIzq();
		} else if (stringAction.equals("avanzar")) {
			actionObject = new Avanzar();
		} else if (stringAction.equals("tomarLlave")) {
			actionObject = new TomarLlave();
		} else if (stringAction.equals("abrirCandado")) {
			actionObject = new AbrirCandado();
		} else if (stringAction.equals("salir")) {
			actionObject = new Salir();
		}

		return actionObject;
	}

	@Override
	protected String endActionString() {
		return "noAction";
	}
}
