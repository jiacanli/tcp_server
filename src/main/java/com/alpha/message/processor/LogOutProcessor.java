package com.alpha.message.processor;

import org.springframework.stereotype.Component;

import com.alpha.message.model.ClientTcpMessage;
@Component
public class LogOutProcessor extends BaseProcessor implements MessageProcessor {

	@Override
	public boolean process(ClientTcpMessage msg) {
		// TODO Auto-generated method stub
		removeClient(msg.getSender());
		return true;
	}

}
