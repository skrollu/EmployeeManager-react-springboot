package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceNotFoundAdvice {

	/**
	 * @ControllerAdvice is a specialization of the @Component annotation which
	 *                   allows to handle exceptions across the whole application in
	 *                   one global handling component. It can be viewed as an
	 *                   interceptor of exceptions thrown by methods annotated
	 *                   with @RequestMapping and similar.
	 */
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String orderNotFoundHandler(ResourceNotFoundException ex) {
		return "ADVICE: " + ex.getMessage();
	}
}
