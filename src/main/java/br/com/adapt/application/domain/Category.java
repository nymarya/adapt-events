package br.com.adapt.application.domain;

/**
 * Categoria do evento (minicursos, palestras, oficinas etc)
 * @author mayra
 *
 */
public enum Category {
	WORKSHOP, MINICOURSE, TALK;
	
	@Override
	public String toString() {
		switch(this) {
	    	case WORKSHOP: return "Oficina";
	    	case MINICOURSE: return "Minicurso";
	    	case TALK: return "Palestra";
	    	default: throw new IllegalArgumentException();
	    }
	}
}
