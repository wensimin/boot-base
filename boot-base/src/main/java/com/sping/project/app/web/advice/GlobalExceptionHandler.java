package com.sping.project.app.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sping.project.app.web.exception.ServiceException;
import com.sping.project.app.web.exception.TokenException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = Exception.class)
	public Response exception(Exception e) {
		// TODO il18n
		e.printStackTrace();
		return new Response("系统错误");
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = ServiceException.class)
	public Response serviceException(ServiceException e) {
		return new Response(e.getCode(), e.getMessage());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = TokenException.class)
	public Response tokenException(TokenException e) {
		return new Response(Codes.TOKEN_ERROR, e.getMessage());
	}
}
