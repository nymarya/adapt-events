package br.com.adapt.framework.domain;
/**
 * Enum for priority of a task.
 * @author mayra
 *
 */
public enum Priority {
	NONE, LOW, MEDIUM, HIGH;
	
	@Override
	public String toString() {
		switch(this) {
	    	case NONE: return "Nenhuma";
	    	case LOW: return "Baixa";
	    	case MEDIUM: return "Média";
	    	case HIGH: return "Alta";
	    	default: throw new IllegalArgumentException();
	    }
	}
}
