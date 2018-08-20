package com.alpha.message.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.model.DefaultMessage;
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
		DefaultMessage message = DefaultMessage.newMessage();
		Number timepointer = (Number) valueOps.get(msg.getSender()+"-"+Message.RedisPrefix.MessageReadTimePointer.name());
		if(timepointer == null) {
			timepointer = 0L;
		}
		valueOps.set(msg.getSender()+"-"+Message.RedisPrefix.MessageReadTimePointer.name(), timepointer);
		message.timepointer = timepointer.longValue();
		message.action = Message.MessageType.NewMessageArrive.getCode();
		try {
			ChannelWrite(message.toJSONString(), msg.getChannel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
