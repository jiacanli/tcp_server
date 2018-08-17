package com.alpha.message.model;

import io.netty.channel.Channel;

public class ClientTcpMessage extends DefaultMessage {
	
	    /**
	 * 
	 */
		private static final long serialVersionUID = 7991809019812020393L;
	    public transient Channel channel;



		public Channel getChannel() {
			return channel;
		}

		public void setChannel(Channel channel) {
			this.channel = channel;
		}

		public ClientTcpMessage() {
			// TODO Auto-generated constructor stub
			super();
			
		}
		
		public static ClientTcpMessage newMessage() {
			return new ClientTcpMessage();
		}
		
		
}
