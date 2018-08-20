package com.alpha.server;

import java.net.InetSocketAddress;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.processor.MessageProcessor;
import com.alpha.message.processor.ProcessPipeline;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

@Component
@Sharable
public class TcpServerHandler extends SimpleChannelInboundHandler<ClientTcpMessage> {
	
	private static final Logger Log = LoggerFactory.getLogger(TcpServerHandler.class);
	
	@Autowired
	private ProcessPipeline processPipeline;
	
	
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ClientTcpMessage msg) throws Exception {
		// TODO Auto-generated method stub
		Log.info(String.format("recieve msg from [%s]", msg.getSender()));
		MessageProcessor processor = processPipeline.getAvailablePorcessor(msg.getAction());
		if(processor!=null) {
			processor.process(msg);
		}
		
	}
	
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {    	
        Log.info(String.format("channel [%s] disconnected by client ", ctx.channel().remoteAddress()));
    	ctx.fireChannelInactive();
        
    }
	
	
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);
    	InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
    	Log.info(String.format("[%s] connected to server at [%s] ", insocket,new Date()));
    		
    }
    
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        Log.error("exception caught", cause);
        ctx.deregister();
        ctx.disconnect();
        ctx.close();  
    }
    
    
    @Override  
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {  
        if (evt instanceof IdleStateEvent) {  
            IdleStateEvent event = (IdleStateEvent) evt;  
            if (event.state() == IdleState.READER_IDLE) {  
            	Log.info("channel read idle ");
            	closeChannel(ctx.channel());
            }  
            
            if(event.state() == IdleState.WRITER_IDLE) {
            	Log.info("channel write idle ");
            	closeChannel(ctx.channel());
            }
        } else {  
            super.userEventTriggered(ctx, evt);  
        }  
    }  
  
    private void closeChannel(Channel channel) {
    	channel.deregister();
    	channel.disconnect();
    	channel.close();
    }

}
