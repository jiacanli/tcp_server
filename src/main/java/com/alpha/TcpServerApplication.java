package com.alpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alpha.server.TcpServer;

@SpringBootApplication
public class TcpServerApplication implements CommandLineRunner  {
	private static final Logger Log = LoggerFactory.getLogger(TcpServerApplication.class);
	
	@Autowired
	private TcpServer server;

	public static void main(String[] args) {
		SpringApplication.run(TcpServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		server.run();
		Log.info("server run");
		
	}
}
