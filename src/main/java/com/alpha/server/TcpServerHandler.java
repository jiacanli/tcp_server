package com.alpha.server;

import java.net.InetSocketAddress;
import java.util.Date;

import com.alpha.message.model.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpServerHandler extends SimpleChannelInboundHandler<Message> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("recieve msg: "+ msg.getMsgBodyContent());
	}
	
	
	

	@Override
	public void channelRead(ChannelHandlerContext arg0, Object arg1) throws Exception{
		
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
