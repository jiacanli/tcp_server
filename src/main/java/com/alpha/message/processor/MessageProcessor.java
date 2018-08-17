package com.alpha.message.processor;

import com.alpha.message.model.ClientTcpMessage;

public interface MessageProcessor {
		
	boolean process(ClientTcpMessage msg);
	
}
