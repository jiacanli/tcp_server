package com.alpha.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.sampled.Port;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.logging.Log;
import com.alpha.message.decoder.TcpMessageDecoder;

import ch.qos.logback.core.net.server.Client;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

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
    
    private static final Logger Log = LoggerFactory.getLogger(TcpServer.class);
    
    @Value("${netty.port}")
    private int Port;
    
    @Autowired
    private TcpMessageDecoder decoder;
    
    @Autowired
    private TcpServerHandler handler;
    
    public void run() throws Exception{
        ServerBootstrap b = new ServerBootstrap();  
        b.group(bossGroup, workerGroup);  
        b.channel(NioServerSocketChannel.class);  
        b.childOption(ChannelOption.TCP_NODELAY, true);
        b.childHandler(new ChannelInitializer<SocketChannel>() {  
            @Override  
            public void initChannel(SocketChannel ch) throws Exception {  
                ChannelPipeline pipeline = ch.pipeline();  
//                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
//                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
//                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));  
//                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new LineBasedFrameDecoder(1024));
//                pipeline.addLast(new StringDecoder());
                pipeline.addLast(decoder);
                pipeline.addLast(handler);
                  
                
            }  
        });  
  
        ChannelFuture f = b.bind(Port).sync();
        Log.info("server running ");
        f.channel().closeFuture().sync();
        
        
    }
    
    
    
    
}
