package com.sping.project.app.web.advice;

public class Response {
	private String code;
	private String message;

	public Response(String message) {
		this.code = Codes.ERROR;
		this.message = message;
	}

	public Response(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
