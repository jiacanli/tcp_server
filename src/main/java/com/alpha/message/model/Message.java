package com.alpha.message.model;

import java.io.Serializable;

import io.netty.channel.Channel;

public interface Message extends Serializable {
	
	enum MessageType{
		BeatHeart(1),NewMessageArriave(2),ReadMessageConfirm(3),Register(4),LogOut(5);

		private int code;
		MessageType(int code) {
			this.code = code;
		}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		
		
		
	}
	
	Integer getAction();
	
	Long getSenderId();
	
	Long getRecieverId();
	
	Long getTime();
	
	Object getMsgBodyContent();
	
	Channel channel();
	
	void setChannel(Channel channel);

}
