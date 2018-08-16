package com.alpha.message.processor;

import org.springframework.stereotype.Component;

import com.alpha.message.model.Message;

@Component
public class BeartHeartProcessor implements MessageProcessor {
	public BeartHeartProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean process(Message msg) {
		// TODO Auto-generated method stub
		return true;
	}
}
