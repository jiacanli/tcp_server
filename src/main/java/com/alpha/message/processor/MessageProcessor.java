package com.alpha.message.processor;

import com.alpha.message.model.Message;

public interface MessageProcessor {
		
	boolean process(Message msg);
	
}
