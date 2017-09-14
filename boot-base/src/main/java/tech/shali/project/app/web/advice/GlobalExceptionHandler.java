package tech.shali.project.app.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tech.shali.project.app.web.exception.ServiceException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = Exception.class)
	public Response exception(Exception e) {
		e.printStackTrace();
		return new Response("系统错误");
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Response bindException(MethodArgumentNotValidException e) {
		return new Response(Errors.PARAM_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = ServiceException.class)
	public Response serviceException(ServiceException e) {
		return new Response(e.getError(), e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = AccessDeniedException.class)
	public Response serviceException(AccessDeniedException e) {
		return new Response(Errors.UN_AUTH, e.getMessage());
	}
	
}
