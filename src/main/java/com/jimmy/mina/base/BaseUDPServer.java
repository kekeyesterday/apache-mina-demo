/*
 * Copyright (c) 2007-2013 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IT Dept.
 *
 * @Project: Telematics
 */
package com.jimmy.mina.base;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
/**
 * UDPServer基础类
 * User: javakill
 * Date: 12-11-29
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class BaseUDPServer extends BaseServer{

    public BaseUDPServer(int port){
        super(port);
    }
    public BaseUDPServer(int port,ProtocolCodecFactory codecFactory){
        super(port,codecFactory);
    }
    protected void setAcceptor(){
        ACCEPTOR = new NioDatagramAcceptor();
    }
}
