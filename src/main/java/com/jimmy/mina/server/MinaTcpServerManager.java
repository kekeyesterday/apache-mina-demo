package com.jimmy.mina.server;

import org.apache.mina.core.session.IdleStatus;

import com.jimmy.mina.base.BaseTCPServer;
import com.jimmy.mina.client.MinaEnum;

public class MinaTcpServerManager extends BaseTCPServer {

	public static void main(String[] args) throws Exception {

		MinaTcpServerManager tcpServerManager = new MinaTcpServerManager();
		tcpServerManager.startup();
		System.out.println("======tcp server 启动成功=====");
	}
	
	/**
	 * 初始化socket端口
	 */
	public MinaTcpServerManager(){
		super(MinaEnum.SEVER_PORT.getCode());
	}
	

	@Override
	protected void setCodec() {
		super.setCodec();
	}

	@Override
	protected void setHandler() {
		ACCEPTOR.setHandler(new MsgServerHandle());
	}

	@Override
	public void setAcceptor() {
		super.setAcceptor();
		ACCEPTOR.getSessionConfig().setReadBufferSize(2048);
		//ACCEPTOR.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	}
}
