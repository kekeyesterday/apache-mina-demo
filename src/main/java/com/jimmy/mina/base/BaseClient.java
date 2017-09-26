/*
 * Copyright (c) 2007-2013 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IT Dept.
 *
 * @Project: Telematics
 */
package com.jimmy.mina.base;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.jimmy.mina.client.MsgClientHandle;
/**
 * 客户端
 * User: jozbt
 * Date: 12-12-5
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseClient{
	private String HOST;
	private int PORT;
	private IoConnector CONNECTOR;
	private int CONNTYPE;
	
	
	ProtocolCodecFactory CODECFACTORY;
	boolean isTcp;
    String msg;

	public BaseClient(String host, int port,int connType) {
		HOST = host;
		PORT = port;
		CONNTYPE = connType;
	}

	public BaseClient(String host, int port, ProtocolCodecFactory codecFactory) {
		HOST = host;
		PORT = port;
		CODECFACTORY = codecFactory;
	}

	public void startup() {

		// 添加过滤器
		if (CODECFACTORY == null) {
			CODECFACTORY = new TextLineCodecFactory(Charset.forName("UTF-8"),
					LineDelimiter.WINDOWS.getValue(),
					LineDelimiter.WINDOWS.getValue());
		}
		switch (CONNTYPE) {
		case 1:
			CONNECTOR = new NioSocketConnector();
			break;
		case 2:
			CONNECTOR = new NioDatagramConnector();
			break;
		default:
			break;
		}
		CONNECTOR.getFilterChain().addLast("logger", new LoggingFilter());//日志过滤器
		CONNECTOR.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(CODECFACTORY));

		// 设置连接超时时间
		CONNECTOR.setConnectTimeoutMillis(1000 * 10);
		
		// 添加业务逻辑处理类
		//setHandler();
		CONNECTOR.setHandler(new MsgClientHandle());
		
		IoSession session = null;

		ConnectFuture future = CONNECTOR.connect(new InetSocketAddress(HOST,
				PORT));
		// 等待连接创建完成
		future.awaitUninterruptibly();
		
		// 获取session
		session = future.getSession();
		
		// send message
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

		// 等待连接断开
		session.getCloseFuture().awaitUninterruptibly();
	}
	
	public void setHandler(){
		//CONNECTOR.setHandler(ioHandler);
	}
	
	public void stop(){
		CONNECTOR.dispose();
	}

    //添加长度前缀
//    private byte[] writeOTAMessagelength(byte[] otaBytes) {
//        int length = otaBytes.length + 4;
//        //System.out.println("length:"+length);
//        byte[] lengthBytes = BinaryAndHexUtil.bytesToHexString(IntByteConvertor.intTo2Byte(length), true).getBytes();
//        byte[] outputBytes = ArrayUtil.combine(lengthBytes, otaBytes);
//        return outputBytes;
//    }



}
