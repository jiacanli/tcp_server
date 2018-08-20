package com.alpha.message.model;

import java.io.Serializable;

import io.netty.channel.Channel;

public interface Message extends Serializable {
	// tcp消息类型
	enum MessageType{
		//---------需和客户端保持一致-------
		//心跳
		BeatHeart(1),
		//转发消息
		MessageTransfer(2),
		//读取消息回执
		ReadMessageConfirm(3),
		//新消息提醒
		NewMessageArrive(4),
		//注册
		Register(5),
		//登出
		LogOut(6);
		

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
	
	enum RedisPrefix{
		MessageReadTimePointer,
		MessageRepo
		
		
	}
	
	
//	Integer getAction();
//	
//	Long getSenderId();
//	
//	Long getRecieverId();
//	
//	Long timestamp();
	

}
