package com.example.exception;

/***
 * Notify exception,which can be used in service.
 * 
 * @author huxg
 * 
 */
@SuppressWarnings("serial")
public class NotifyException extends RuntimeException {
	int code;
	String message;
	Object object;

	public NotifyException(String message) {
		this.code = -1;
		this.message = message;
	}

	public NotifyException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public NotifyException(int code, String message, Object obj) {
		this.code = code;
		this.message = message;
		this.object = obj;
	}


}
