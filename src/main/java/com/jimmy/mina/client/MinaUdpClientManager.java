package com.jimmy.mina.client;

import com.jimmy.mina.base.BaseClient;
import com.jimmy.mina.base.Constants;
import com.jimmy.mina.base.MinaEnum;

/**
 * @author zghdo
 *
 */
public class MinaUdpClientManager extends BaseClient{
	


	public MinaUdpClientManager(String host, int port,int connType) {
		super(host, port,connType);
	}
	
	


	public static void main(String[] args) {
		//测试
		MinaTcpClientManager client = new MinaTcpClientManager(Constants.SERVER_IP, Constants.SERVER_PORT,MinaEnum.CONN_TYPE_UDP.getCode());
		client.startup();
		client.stop();
	}


}
