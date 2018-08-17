package com.alpha.http.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.model.DataModel;
import com.alpha.message.model.Message;
import com.alpha.message.model.RequestMessage;
import com.alpha.message.processor.MessageTransferProcessor;
@RestController
@RequestMapping("")
public class MessageControl extends BaseContoller {
	
	
	@Autowired
	private ZSetOperations< Object, Object> zSetOperations;
	
	@Autowired
	private MessageTransferProcessor messageTransfer;
	
	public MessageControl() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/send")
	public DataModel<Object> send(RequestMessage message){
		Long timestamp = System.currentTimeMillis();	
		message.timepointer = timestamp;
		zSetOperations.add(message.getReciever()+"-"+Message.RedisPrefix.MessageRepo.name(), message,timestamp);
		ClientTcpMessage tcp_reminder = ClientTcpMessage.newMessage();
		tcp_reminder.sender = message.getSender();
		tcp_reminder.reciever = message.getReciever();
		tcp_reminder.action = Message.MessageType.MessageTransfer.getCode();
		Boolean result = messageTransfer.process(tcp_reminder);
		return result?renderSuccess():renderError();
	}
	
	@RequestMapping("/recieve")
	public DataModel<Object> recieve(RequestMessage message){
		Long timepointer = message.timepointer;
		Set<Object> result = zSetOperations.rangeByScore(message.getSender()+"-"+Message.RedisPrefix.MessageRepo.name(), timepointer, Long.MAX_VALUE);
		
		return renderSuccess(result);
	}
	
	
}
