/*
 * Copyright (c) 2007-2013 SAIC. All Rights Reserved.
 * This software is published under the terms of the SAIC IT Dept.
 *
 * @Project: Telematics
 */
package com.jimmy.mina.base;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 处理器
 * User: jozbt
 * Date: 12-12-5
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public class BaseClientHandler extends IoHandlerAdapter {

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO 处理接受的数据
        System.out.println("BaseClientHandler messageReceived ");

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	}
}
