package com.alpha.message.exception;

public class ClientChannelException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4204981081879517418L;
	
	
	private Long id ;
	
	public ClientChannelException() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	
	public ClientChannelException(String msg,Long id) {
		super(msg);
		this.id = id;
		
	}
}
