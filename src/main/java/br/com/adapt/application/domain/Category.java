package br.com.adapt.application.domain;

/**
 * Categoria do evento (minicursos, palestras, oficinas etc)
 * @author mayra
 *
 */
public enum Category {
	WORKSHOP, TALK, MINICOURSE;
	
	@Override
	public String toString() {
		switch(this) {
	    	case WORKSHOP: return "Oficina";
	    	case TALK: return "Palestra";
	    	case MINICOURSE: return "Minicurso";
	    	default: throw new IllegalArgumentException();
	    }
	}
}
