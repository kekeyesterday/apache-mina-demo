/*
 * Copyright (c) 2007-2013 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IT Dept.
 *
 * @Project: Telematics
 */
package com.jimmy.mina.base;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
/**
 * TCPServer基础类
 * User: jozbt
 * Date: 12-11-29
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class BaseTCPServer extends BaseServer{

    public BaseTCPServer(int port){
        super(port);
    }
    public BaseTCPServer(int port,ProtocolCodecFactory codecFactory){
        super(port,codecFactory);
    }
    public void setAcceptor(){
        ACCEPTOR = new NioSocketAcceptor();
    }
}
