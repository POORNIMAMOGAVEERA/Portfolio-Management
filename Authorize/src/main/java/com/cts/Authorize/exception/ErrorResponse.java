package com.cts.Authorize.exception;

import lombok.Data;

@Data
public class ErrorResponse {

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public ErrorResponse(String message, int statusCode, Long exceptionTime) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.exceptionTime = exceptionTime;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public Long getExceptionTime() {
		return exceptionTime;
	}
	public void setExceptionTime(Long exceptionTime) {
		this.exceptionTime = exceptionTime;
	}
	private String message;
	private int statusCode;
	private Long exceptionTime;
   public ErrorResponse() {
	   
   }
}
