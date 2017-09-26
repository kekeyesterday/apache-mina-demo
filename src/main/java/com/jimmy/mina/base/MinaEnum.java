package com.jimmy.mina.base;

/**
 * 常量枚举类
 * @author zghdo
 *
 */
public enum MinaEnum {
	CONN_TYPE_TCP(1,"tcp"),
	CONN_TYPE_UDP(2,"udp");
	
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
