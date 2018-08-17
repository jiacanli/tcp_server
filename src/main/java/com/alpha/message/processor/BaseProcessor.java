package com.alpha.message.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import com.alpha.server.TcpServer;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

@Component
public class BaseProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseProcessor.class);
	public BaseProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	protected ZSetOperations<Object, Object> zsetOps;
	
	@Autowired
	protected ValueOperations<Object, Object> valueOps;
	
	public Channel getClientChannelByKey(Long key) {
		return TcpServer.Client.get(key);
	}
	
	public boolean isCientOnline(Long key) {
		return TcpServer.Client.containsKey(key);
	}
	
	public void addClient(Long key,Channel channel) {
		
		TcpServer.Client.put(key, channel);
		LOG.info(String.format("id [%d] register success " , key));
	}
	
	public void removeClient(Long key) {
		Channel channel = getClientChannelByKey(key);
		if(channel != null) {			
			channel.deregister();
			channel.disconnect();
			channel.close();			
		}
		TcpServer.Client.remove(key);
	}
	
	
	public void ChannelWrite(String msg,Channel channel) throws Exception {
		if(channel.isOpen()&&channel.isWritable()&&channel.isActive()) {
			channel.writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
		}
	}
}
