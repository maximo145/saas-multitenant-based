package com.decode.saassharedschema.util;

public class MessageCore {
	
	private boolean success;
	private String status;
	private String message;
	
	public MessageCore() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MessageCore(boolean success, String status, String message) {
		super();
		this.success = success;
		this.status = status;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageCore [success=" + success + ", status=" + status + ", message=" + message + "]";
	}
	
}