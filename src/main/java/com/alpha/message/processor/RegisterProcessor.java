package com.alpha.message.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.model.Message;
import com.alpha.server.TcpServer;

@Component
public class RegisterProcessor extends BaseProcessor  implements MessageProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(RegisterProcessor.class);
	
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
	public boolean process(ClientTcpMessage msg) {
		// TODO Auto-generated method stub
		addClient(msg.getSender(),msg.getChannel());
		return true;
	}

}
