package com.jimmy.mina.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClientManager {

	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();  
		DefaultIoFilterChainBuilder  fcb = connector.getFilterChain();
		fcb.addLast("logger", new LoggingFilter());//日志过滤器
		fcb.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"))));//字符集过滤
		connector.setHandler(new MsgClientHandle());
		IoSession session = null;  
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(MinaEnum.SEVER_PORT.getCode()));
			future.awaitUninterruptibly();
			session = future.getSession();
			
			Scanner sc = new Scanner(System.in);
			while(true){
				String msg = sc.nextLine();
				session.write(msg);
				if(msg.trim().equals("quit")){
					session.closeOnFlush();
					sc.close();
					break;
				}
			}
			session.getCloseFuture().awaitUninterruptibly();
		} catch (Exception e) {
			e.printStackTrace();
			connector.dispose();
		}
		
	}

}
