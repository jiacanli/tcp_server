package com.alpha.message.processor;

import org.springframework.stereotype.Component;

import com.alpha.message.model.ClientTcpMessage;

@Component
public class BeartHeartProcessor extends BaseProcessor implements MessageProcessor {
	public BeartHeartProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean process(ClientTcpMessage msg) {
		// TODO Auto-generated method stub
		return true;
	}
}
