package com.alpha.message.processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alpha.message.model.Message;

@Component
public class ProcessPipeline {
	private static final Logger log = LoggerFactory.getLogger(ProcessPipeline.class);
	
	private static final Map<Integer, MessageProcessor> processor = new ConcurrentHashMap<>();
	@Autowired
	private BeartHeartProcessor beartHeartProcessor;
	@Autowired
	private LogOutProcessor logOutProcessor;
	@Autowired
	private MessageConfirmProcessor messageConfirmProcessor;
	@Autowired
	private MessageTransferProcessor messageTransferProcessor;
	@Autowired
	private RegisterProcessor registerProcessor;
	
	public ProcessPipeline() {
		// TODO Auto-generated constructor stub

	}
	
	@PostConstruct
	private void init() {
		processor.put(Message.MessageType.BeatHeart.getCode(), beartHeartProcessor);
		processor.put(Message.MessageType.LogOut.getCode(), logOutProcessor);
		processor.put(Message.MessageType.MessageTransfer.getCode(), messageTransferProcessor);
		processor.put(Message.MessageType.ReadMessageConfirm.getCode(), messageConfirmProcessor);
		processor.put(Message.MessageType.Register.getCode(), registerProcessor);
		log.debug("processor pipeline init sucess");
	}
	
	public MessageProcessor getAvailablePorcessor(Integer type) {
		if(type == null) {
			return null;
		}
		return processor.get(type);
	}
	
	
	
}
