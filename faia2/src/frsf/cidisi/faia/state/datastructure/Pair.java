/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa
 * y Milton Pividori.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package frsf.cidisi.faia.state.datastructure;

/**
 * @author Jorge M. Roa
 * @version 1.0
 * @created 08-Mar-2007 13:16:04
 */
public class Pair<F,S> extends DataStructure {
	    private F first; //first member of pair
	    private S second; //second member of pair

	    public Pair(F first, S second) {
	        this.first = first;
	        this.second = second;
	    }

	    public void setPair(F first, S second) {
	        this.first = first;
	        this.second = second;
	    }
	    
	    public void setFirst(F first) {
	        this.first = first;
	    }

	    public void setSecond(S second) {
	        this.second = second;
	    }

	    public F getFirst() {
	        return first;
	    }

	    public S getSecond() {
	        return second;
	    }

		@Override
		public Pair<F,S> clone() {
			Pair<F,S> givenPair = new Pair<F,S>(getFirst(), getSecond());
			
			return givenPair;
		}

		@Override
		public boolean equals(Object arg0) {
			// TODO Auto-generated method stub
			if(arg0.getClass() == Pair.class) {
				Pair<F,S> givenPair = (Pair<F,S>) arg0;
				return (getFirst() == givenPair.getFirst()) &&
							(getSecond() == givenPair.getSecond());
			}
			
			return false;
		}

		@Override
		public String toString() {
			return "(" + getFirst() + ", " + getSecond() + ")";
		}
	    
	}