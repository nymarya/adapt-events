package br.com.adapt.application.domain;

public enum Category {

	CLASS, SUPPORT, EXTRA;
	
	@Override
	public String toString() {
		switch(this) {
	    	case SUPPORT: return "Monitoria";
	    	case EXTRA: return "Aula extra";
	    	case CLASS: return "Aula";
	    	default: throw new IllegalArgumentException();
	    }
	}
	
}
