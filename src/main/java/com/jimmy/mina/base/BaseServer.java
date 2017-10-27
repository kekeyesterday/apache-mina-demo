/*
 * Copyright (c) 2007-2013 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IT Dept.
 *
 * @Project: Telematics
 */
package com.jimmy.mina.base;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;  
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * BaseServer
 * User: zhangjun
 * Date: 12-11-29
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseServer {
    private static final Logger logger = LoggerFactory.getLogger(BaseServer.class.getName());

    private int PORT;
    protected IoAcceptor ACCEPTOR;
    protected ProtocolCodecFactory CODECFACTORY;
    int IDLETIME;
    private ExecutorService executor = Executors.newFixedThreadPool(1000); //固定大小为1000的连接池 

    public BaseServer(int port, ProtocolCodecFactory codecFactory) {
        PORT = port;
        CODECFACTORY = codecFactory;
        startup();
    }

	public BaseServer(int port) {
        PORT = port;
        startup();
	}

    protected abstract void setAcceptor();

    protected void setCodec(){
//        CODECFACTORY = new TextLineCodecFactory(Charset
//                .forName(Constants.CHARSET),
//                LineDelimiter.WINDOWS.getValue(),
//                LineDelimiter.WINDOWS.getValue());
        
        
        CODECFACTORY = new ObjectSerializationCodecFactory();
		ACCEPTOR.getFilterChain().addLast(
				"codec",new ProtocolCodecFilter(CODECFACTORY));
    }

    protected void setHandler(){
        //ACCEPTOR.setHandler(ioHandle);
    }

    /**
     * 设置filter
     */
    protected void setFilter(){
        // 设置日志过滤器
        LoggingFilter lf = new LoggingFilter();
        lf.setMessageReceivedLogLevel(LogLevel.DEBUG);
        ACCEPTOR.getFilterChain().addLast("logger", lf);
//        ACCEPTOR.getFilterChain().addLast("ThreadPool",
//                new ExecutorFilter(Executors.newCachedThreadPool()));
        ACCEPTOR.getFilterChain().addLast("ThreadPool",
                new ExecutorFilter(executor));
        
//        ACCEPTOR.getFilterChain().addLast("keepAlive", new HachiKeepAliveFilterInMina());//设置心跳检测
    }

    /**
     * 启动mina服务
     */
	public void startup() {
		try {
			//设置接收器
            setAcceptor();
            
            // 设置编解码器
            setCodec();

            // 设置过滤器
			setFilter();
			
			//设置空闲时间
            setIdletime();
            IoSessionConfig  cfg = ACCEPTOR.getSessionConfig();
            cfg.setIdleTime(IdleStatus.BOTH_IDLE, IDLETIME);

            // 绑定逻辑处理器
            setHandler();

			// 绑定端口,并启动
			ACCEPTOR.bind(new InetSocketAddress(PORT));
		} catch (Exception e) {
			logger.error("启动mina服务异常", e);
		}
	}

    //关闭mina服务
    public void shutdown(){
        logger.debug("mina 服务正在关闭");
        ACCEPTOR.dispose();
        logger.debug("mina 服务已经关闭");
    }
    //设置idletime
    protected void setIdletime(){
        IDLETIME = 100;
    }
}
