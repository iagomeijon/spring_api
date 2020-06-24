package com.iagomeijon.spring_api.services.exceptions;

public class DataIntegratyException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegratyException(String message) {
		super(message);
	}
	public DataIntegratyException(String message, Throwable cause) {
		super(message, cause);
	}
}
