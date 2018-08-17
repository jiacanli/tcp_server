package com.alpha.message.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.model.DefaultMessage;
import com.alpha.message.model.Message;

import io.netty.channel.Channel;

@Component
public class MessageTransferProcessor extends BaseProcessor implements MessageProcessor {
	private static final Logger LOG = LoggerFactory.getLogger(MessageTransferProcessor.class);
	
	// 发出新消息通知给reciever
	@Override
	public boolean process(ClientTcpMessage msg) {
		// TODO Auto-generated method stub
		//接受消息者未下线
		if(isCientOnline(msg.getReciever())) {
			LOG.info("recieve is online");
			Long timepointer = (Long) valueOps.get(msg.getReciever()+"-"+Message.RedisPrefix.MessageReadTimePointer.name());
			//首次读取消息 初始化
			if(timepointer == null) {
				timepointer = 0l;
				valueOps.set(msg.getReciever()+"-"+Message.RedisPrefix.MessageReadTimePointer.name(), 0l);
			}
			Channel channel = getClientChannelByKey(msg.getReciever());
			DefaultMessage message = DefaultMessage.newMessage();
			message.action = Message.MessageType.NewMessageArrive.getCode();
			message.timepointer = timepointer;
			try {
				ChannelWrite(message.toJSONString(), channel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
