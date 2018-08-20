package com.alpha.message.processor;

import org.springframework.stereotype.Component;

import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.model.Message;
@Component
public class MessageConfirmProcessor extends BaseProcessor implements MessageProcessor {

	@Override
	public boolean process(ClientTcpMessage msg) {
		// TODO Auto-generated method stub
		Long id = msg.getSender();
		//更新最后读取时间
		valueOps.set(id+"-"+Message.RedisPrefix.MessageReadTimePointer.name(), msg.timepointer+1);
		return true;
	}
	
	

}
