package frsf.cidisi.exercise.datastructures;

import frsf.cidisi.faia.state.datastructure.Pair;

public class PairInt extends Pair<Integer, Integer> {

	public PairInt(Integer first, Integer second) {
		super(first, second);
	}

	@Override
	public PairInt clone() {
		PairInt givenPair = new PairInt(getFirst(), getSecond());
		
		return givenPair;
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0.getClass() == PairInt.class) {
			PairInt givenPair = (PairInt) arg0;
			return (getFirst() == givenPair.getFirst()) &&
						(getSecond() == givenPair.getSecond());
		}

		return super.equals(arg0);
	}

}
