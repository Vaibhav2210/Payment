package com.vaibhav.ws.peer.exception;

public class ErrorResponseFirstName {
	
	private String msg;
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "ErrorResponseFirstName [msg=" + msg + "]";
	}

}
