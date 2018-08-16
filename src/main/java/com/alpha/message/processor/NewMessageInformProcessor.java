package com.alpha.message.processor;

import org.springframework.stereotype.Component;

import com.alpha.message.model.Message;
import com.alpha.server.TcpServer;

import io.netty.channel.Channel;

@Component
public class NewMessageInformProcessor implements MessageProcessor {
	
	
	// 发出新消息通知给reciever
	@Override
	public boolean process(Message msg) {
		// TODO Auto-generated method stub
		//接受消息者未下线
		return false;
	}

}
