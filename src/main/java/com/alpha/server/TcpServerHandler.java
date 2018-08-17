package com.alpha.server;

import java.net.InetSocketAddress;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alpha.message.model.ClientTcpMessage;
import com.alpha.message.model.Message;
import com.alpha.message.processor.MessageProcessor;
import com.alpha.message.processor.ProcessPipeline;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

@Component
@Sharable
public class TcpServerHandler extends SimpleChannelInboundHandler<ClientTcpMessage> {
	
	@Autowired
	private ProcessPipeline processPipeline;
	
	
	
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ClientTcpMessage msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("message recieve" + msg.getSender());
		MessageProcessor processor = processPipeline.getAvailablePorcessor(msg.getAction());
		if(processor!=null) {
			processor.process(msg);
		}
		
	}
	
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {    	
        System.out.println("channel ["+ctx.channel().remoteAddress()+"] disconnected by client ");
    	ctx.fireChannelInactive();
        
    }
	
	
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);
    	InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                .remoteAddress();
        
    	System.out.println(insocket+" connected at "+new Date());
    		
    }
    
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        System.out.println("exection");
        cause.printStackTrace();
        ctx.close();  
    }


}
