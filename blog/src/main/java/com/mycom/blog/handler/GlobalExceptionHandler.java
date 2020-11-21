package com.mycom.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blog.model.Response;

@ControllerAdvice //이 어노테이션은 aop방식처럼 한번 훑고 지나감 
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public Response<String> handlerException(Exception e) {
		return new Response<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
}
