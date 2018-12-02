/**
 * 
 */
package br.com.adapt.framework.domain;

/**
 * A task's type.
 * @author mayra
 *
 */
public enum Type {
	ROUTINE, TEMPORARY;
	
	@Override
	public String toString() {
		switch(this) {
	    	case ROUTINE: return "Rotina";
	    	case TEMPORARY: return "Temporária";
	    	default: throw new IllegalArgumentException();
	    }
	}
}
