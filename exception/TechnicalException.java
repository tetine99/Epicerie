package fr.imie.gestionepicerie.exception;

@SuppressWarnings("serial")
public class TechnicalException extends RuntimeException {
	
	
	public TechnicalException(Throwable t) {
		super(t);
	}
}
