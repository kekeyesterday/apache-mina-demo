package com.jimmy.mina.server;

import java.util.Date;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MsgServerHandle implements IoHandler {
	private static long sessionId;
	

	@Override
	public void inputClosed(IoSession session) throws Exception {
		System.out.println(sessionId + "=====MsgServerHandle=========inputClosed==================");
		session.closeNow();
		
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		sessionId = session.getId();
		System.out.println(sessionId + "=====MsgServerHandle=========sessionCreated==================");
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println(sessionId + "=====MsgServerHandle=========sessionOpened==================");
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		session.write("quite");
		System.out.println(sessionId + "=====MsgServerHandle=========sessionClosed==================");
		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println(sessionId + "=====MsgServerHandle=========sessionIdle==================");
		
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		System.out.println(sessionId + "=====MsgServerHandle=========exceptionCaught==================");
		cause.printStackTrace();
		
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String msg = message.toString();
		if(msg.trim().equals("quit")){
			session.closeNow();
			return;
		}
		Date date = new Date();
		session.write("date server:" + date);
		System.out.println("Message written...");  
		System.out.println(sessionId + "=====MsgServerHandle=========messageReceived==================" + msg);
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println(sessionId + "====MsgServerHandle==========messageSent==================" + message);
		
	}

}
