package com.alpha.message.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Component
public class BaseProcessor {
	public BaseProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	protected RedisTemplate<String,String> redis;
	
	public void addMessage(String key,String msg,int score) {
		ZSetOperations<String, String> operations = redis.opsForZSet();
	}
	
}
