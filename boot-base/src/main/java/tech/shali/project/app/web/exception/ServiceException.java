package tech.shali.project.app.web.exception;

import tech.shali.project.app.web.advice.Errors;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Errors error;

	public ServiceException(String message) {
		super(message);
		this.error = Errors.ERROR;
	}

	public ServiceException(Errors error, String message) {
		super(message);
		this.error = error;
	}

	public Errors getError() {
		return error;
	}

	public void setError(Errors error) {
		this.error = error;
	}

}
