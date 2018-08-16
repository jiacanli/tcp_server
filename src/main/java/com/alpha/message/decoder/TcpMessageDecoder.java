package com.alpha.message.decoder;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alpha.message.model.BaseMessage;
import com.alpha.message.model.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;


@Component
public class TcpMessageDecoder extends MessageToMessageDecoder<ByteBuf> {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		
		String message_str = msg.toString(Charset.forName("UTF-8"));
		try {
			
			Message message = JSONObject.parseObject(message_str, BaseMessage.class);
			message.setChannel(ctx.channel());
			out.add(message);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.add(null);
		}
		
	}

}
