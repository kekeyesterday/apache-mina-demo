package com.jimmy.mina.client;

import com.jimmy.mina.base.BaseClient;
import com.jimmy.mina.base.Constants;

/**
 * @author zghdo
 *
 */
public class MinaTcpClientManager extends BaseClient{
	


	public MinaTcpClientManager(String host, int port) {
		super(host, port);
	}
	
	


	public static void main(String[] args) {
		//测试
		MinaTcpClientManager client = new MinaTcpClientManager(Constants.SERVER_IP, Constants.SERVER_PORT);
		client.startup();
		client.stop();
	}


}
