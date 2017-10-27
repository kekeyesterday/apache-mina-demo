package com.jimmy.mina.server;

import java.net.SocketAddress;
import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jimmy.mina.vo.MessageModle;

public class MsgServerHandle implements IoHandler {
	private Logger logger = LoggerFactory.getLogger(MsgServerHandle.class);
	private static long sessionId;
	

	@Override
	public void inputClosed(IoSession session) throws Exception {
		System.out.println(sessionId + "=====MsgServerHandle=========inputClosed==================");
		session.closeNow();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		sessionId = session.getId();
		SocketAddress socketAddress = session.getRemoteAddress();
		
		System.out.println(sessionId + "=====MsgServerHandle=========sessionCreated==================" + socketAddress.toString());
		
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
		
		MessageModle mmd = null;
		if(null == message){
			return;
		}
		try {
			mmd = (MessageModle)message;
			String msg = mmd.toString();//message.toString();
			if(msg.trim().equals("quit")){
				session.closeNow();
				return;
			}
			Date date = new Date();
			mmd.setMsg("date server:" + date);
			IoBuffer buffer = IoBuffer.allocate(8);
			buffer.setAutoExpand(Boolean.TRUE);
			buffer.putObject(buffer);
			session.write(mmd);
			//session.write("date server:" + date);
			//session.getService().broadcast("这是一条广播消息...");
			System.out.println("Message written...");  
			logger.info(sessionId + "=====MsgServerHandle=========messageReceived==================" + msg);
			System.out.println(sessionId + "=====MsgServerHandle=========messageReceived==================" + msg);
		} catch (Exception e) {
			System.out.println("exception message:" + message);
		}
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println(sessionId + "====MsgServerHandle==========messageSent==================" + message);
		
	}

}
