package com.alpha.message.model;

import io.netty.channel.Channel;

public class BaseMessage implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4824081074396045804L;
	
	
	private Long sender;
	private Long reciever;
	private String content;
	private Long timestamp;
	private int action;	
	private Channel channel = null;
	
	@Override
	public Integer getAction() {
		// TODO Auto-generated method stub
		return action;
	}

	@Override
	public Long getSenderId() {
		// TODO Auto-generated method stub
		return sender;
	}

	@Override
	public Long getRecieverId() {
		// TODO Auto-generated method stub
		return reciever;
	}

	@Override
	public Long getTime() {
		// TODO Auto-generated method stub
		return timestamp;
	}

	@Override
	public Object getMsgBodyContent() {
		// TODO Auto-generated method stub
		return content;
	}
	
	@Override
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public Channel channel() {
		// TODO Auto-generated method stub
		return channel;
	}
	
}
