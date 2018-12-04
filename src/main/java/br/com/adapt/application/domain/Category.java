package br.com.adapt.application.domain;

public enum Category {

	CLASS, SUPPORT, EXTRA;
	
	@Override
	public String toString() {
		switch(this) {
	    	case CLASS: return "Aula";
	    	case SUPPORT: return "Monitoria";
	    	case EXTRA: return "Aula extra";
	    	default: throw new IllegalArgumentException();
	    }
	}
	
}
