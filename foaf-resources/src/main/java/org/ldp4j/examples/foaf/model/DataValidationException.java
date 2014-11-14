package org.ldp4j.examples.foaf.model;

public class DataValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public DataValidationException() {
		
	}
	
	public DataValidationException(String msg) {
		super(msg);
	}
	
	public DataValidationException(String msg, Throwable e){
		super(msg, e);
	}
	

}
