package com.alpha.http.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.alpha.http.service.MessageService;
import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.model.Message;
import com.alpha.message.model.RequestMessage;
import com.alpha.message.processor.MessageTransferProcessor;
@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private ZSetOperations< Object, Object> zSetOperations;
	
	@Autowired
	private MessageTransferProcessor messageTransfer;
	
	@Override
	public boolean send(RequestMessage msg) {
		// TODO Auto-generated method stub
		Long timestamp = System.currentTimeMillis();	
		msg.timepointer = timestamp;
		//新消息插入redis排序set
		zSetOperations.add(msg.getReciever()+"-"+Message.RedisPrefix.MessageRepo.name(), msg,timestamp);
		//发送tcp消息给reciever，提醒接受新消息
		ClientTcpMessage tcp_reminder = ClientTcpMessage.newMessage();
		tcp_reminder.sender = msg.getSender();
		tcp_reminder.reciever = msg.getReciever();
		tcp_reminder.action = Message.MessageType.MessageTransfer.getCode();
		Boolean result = messageTransfer.process(tcp_reminder);
		return result;
	}

	@Override
	public Set<Object> recieve(RequestMessage msg) {
		// TODO Auto-generated method stub
		Long timepointer = msg.timepointer;
		Set<Object> result = zSetOperations.rangeByScore(msg.getSender()+"-"+Message.RedisPrefix.MessageRepo.name(), timepointer, Long.MAX_VALUE);
		return result;
	}


}
