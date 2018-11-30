/**
 * 
 */
package br.com.adapt.framework.domain;

/**
 * A task's difficulty.
 * @author mayra
 *
 */
public enum Difficulty {
	UNKNOWN,LOW, MEDIUM, HIGH;
	
	@Override
	public String toString() {
		switch(this) {
	    	case UNKNOWN: return "Desconhecida";
	    	case LOW: return "Baixa";
	    	case MEDIUM: return "Média";
	    	case HIGH: return "Alta";
	    	default: throw new IllegalArgumentException();
	    }
	}
}
