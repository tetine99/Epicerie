package fr.imie.gestionepicerie.exception;


@SuppressWarnings("serial")
public class BusinessException extends Exception{
	//attribut
	private String code;
	
	//constructeurs
	public BusinessException (String code) {
		this.code = code;
	}
	
	//getters
	public String getCode(){
		return code;
	}

}
