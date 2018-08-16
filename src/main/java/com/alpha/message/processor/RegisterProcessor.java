package com.alpha.message.processor;

import org.springframework.stereotype.Component;

import com.alpha.message.model.Message;
import com.alpha.server.TcpServer;

@Component
public class RegisterProcessor implements MessageProcessor {
	
	 public RegisterProcessor() {
		// TODO Auto-generated constructor stub
	 }
	 
	 /*
	  * (non-Javadoc)
	  * @see com.alpha.message.processor.MessageProcessor#process(com.alpha.message.model.Message)
	  *   注册事件处理
	  * 
	  * 
	  * 
	  */ 
	@Override
	public boolean process(Message msg) {
		// TODO Auto-generated method stub
		TcpServer.Client.put(msg.getSenderId(),msg.channel());
		return true;
	}

}
