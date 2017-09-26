package com.jimmy.mina.server;

import org.apache.mina.core.session.IdleStatus;

import com.jimmy.mina.base.BaseUDPServer;
import com.jimmy.mina.base.Constants;

public class MinaUdpServerManager extends BaseUDPServer {

	public static void main(String[] args) throws Exception {

		MinaUdpServerManager udpServerManager = new MinaUdpServerManager();
		udpServerManager.startup();
		System.out.println("======udp server 启动成功=====");
	}
	
	/**
	 * 初始化socket端口
	 */
	public MinaUdpServerManager(){
		super(Constants.SERVER_PORT);
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
		ACCEPTOR.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	}
}
