package com.alpha.message.model;

public class RequestMessage extends DefaultMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String message;
	public Integer type;
	

	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public RequestMessage() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	
	
}
