package com.alpha.http.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.http.service.MessageService;
import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.model.DataModel;
import com.alpha.message.model.Message;
import com.alpha.message.model.RequestMessage;
import com.alpha.message.processor.MessageTransferProcessor;
@RestController
@RequestMapping("")
public class MessageControl extends BaseContoller {
	
	
	@Autowired
	private MessageService messageService;
	
	
	public MessageControl() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/send")
	public DataModel<Object> send(RequestMessage message){
		Boolean result = messageService.send(message);
		return result?renderSuccess():renderError();
	}
	
	@RequestMapping("/recieve")
	public DataModel<Object> recieve(RequestMessage message){
		Set<Object> result = messageService.recieve(message);	
		return renderSuccess(result);
	}
	
	
}
