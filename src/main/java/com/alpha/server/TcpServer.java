package com.alpha.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alpha.message.decoder.TcpMessageDecoder;

import ch.qos.logback.core.net.server.Client;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

@Component
public class TcpServer {
	public TcpServer() {}
	
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2; // 默认  
    /** 业务出现线程大小 */  
    protected static final int BIZTHREADSIZE = 4;  
    /* 
     * NioEventLoopGroup实际上就是个线程池, 
     * NioEventLoopGroup在后台启动了n个NioEventLoop来处理Channel事件, 
     * 每一个NioEventLoop负责处理m个Channel, 
     * NioEventLoopGroup从NioEventLoop数组里挨个取出NioEventLoop来处理Channel 
     */  
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);  
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);  
    
    public static final Map<Long, Channel> Client = new ConcurrentHashMap<>();
    
    @Autowired
    private TcpMessageDecoder decoder;
    
    
    
    
}
