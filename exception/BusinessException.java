package fr.imie.gestionepicerie.exception;

@SuppressWarnings("serial")
public class BusinessException extends Exception {

	private String code;

	public BusinessException(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

}
