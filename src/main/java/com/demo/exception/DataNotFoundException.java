package com.demo.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	
	private String message;
	private static final long serialVersionUID = 1L;
	public DataNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
	

}
