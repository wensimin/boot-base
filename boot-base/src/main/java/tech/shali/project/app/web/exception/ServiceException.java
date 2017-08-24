package tech.shali.project.app.web.exception;

import tech.shali.project.app.web.advice.Codes;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String code;
	
	public ServiceException(String message) {
		super(message);
		this.code = Codes.ERROR;
	}
	public ServiceException(String code,String message) {
		super(message);
		this.code = code;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
