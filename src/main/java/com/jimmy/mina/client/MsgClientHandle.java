package com.jimmy.mina.client;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MsgClientHandle implements IoHandler {

	@Override
	public void inputClosed(IoSession session) throws Exception {
		System.out.println("====MsgClientHandle==========inputClosed==================");

	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("=====MsgClientHandle=========sessionCreated==================");

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("====MsgClientHandle==========sessionOpened==================");

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("====MsgClientHandle==========sessionClosed==================");
		if (null != session)
			session.closeOnFlush();

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("====MsgClientHandle==========sessionIdle==================");

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println("====MsgClientHandle==========exceptionCaught==================");

	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String msg = message.toString();
		if (msg.trim().equals("quit")) {
			session.closeOnFlush();
		}
		System.out.println("===MsgClientHandle===========messageReceived==================" + msg);

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("===MsgClientHandle===========messageSent==================" + message);

	}

}
