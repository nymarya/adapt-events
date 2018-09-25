/**
 * 
 */
package br.com.adapt.domain;

/**
 * Status of a task
 * @author mayra
 *
 */
public enum Status {
	TODO,DOING,DONE;
	
	@Override
	public String toString() {
		switch(this) {
	    	case TODO: return "A fazer";
	    	case DOING: return "Fazendo";
	    	case DONE: return "Feito";
	    	default: throw new IllegalArgumentException();
	    }
	}
}
