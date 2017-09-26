package com.jimmy.mina.client;

/**
 * 常量枚举类
 * @author zghdo
 *
 */
public enum MinaEnum {
	SEVER_PORT(9123,"服务端口");
	
	private int code;
    private String desc;
    
    MinaEnum(int code,String desc){
    	this.code = code;
    	this.desc = desc;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
    

}
