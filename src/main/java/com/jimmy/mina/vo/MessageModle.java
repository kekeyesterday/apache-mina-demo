package com.jimmy.mina.vo;

import java.io.Serializable;
import java.util.Date;

public class MessageModle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5389394409409871824L;

	private String title;
	private String msg;
	private Date createDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		String msger = "title:" + title + "............msg:" + msg + "..........................createDate:" + createDate.toLocaleString();
		return msger;
	}
	
	

}
