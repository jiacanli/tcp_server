package com.alpha.http.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.alpha.message.model.RequestMessage;

public interface MessageService{
	
		boolean send(RequestMessage msg);
		Set<Object> recieve(RequestMessage msg);
}
