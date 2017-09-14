package tech.shali.project.app.web.advice;

public class Response {
	private Errors error;
	private String message;

	public Response(String message) {
		this.error = Errors.ERROR;
		this.message = message;
	}

	public Response(Errors error, String message) {
		this.error = error;
		this.message = message;
	}

	public Errors getError() {
		return error;
	}

	public void setError(Errors error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
