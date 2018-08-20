package com.alpha.message.model;

import com.alibaba.fastjson.JSON;

public class DefaultMessage implements Message {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8640875193276501979L;
	public Integer action;
	public Long sender;
	public Long reciever;
	public Long timepointer;
	
	public Integer getAction() {
		return action;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public Long getReciever() {
		return reciever;
	}

	public void setReciever(Long reciever) {
		this.reciever = reciever;
	}

	public Long getTimepointer() {
		return timepointer;
	}

	public void setTimepointer(Long timepointer) {
		this.timepointer = timepointer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setAction(Integer action) {
		this.action = action;
	}


	public static DefaultMessage newMessage() {
		return new DefaultMessage();
	}

	
	public String toJSONString() {
		return JSON.toJSONString(this);
	}

}
