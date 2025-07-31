package com.vaibhav.ws.peer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(value=UserNullPointerException.class)
	public ResponseEntity<Object> UserNullPointerException(UserNullPointerException ex) {
		
		ErrorResponseFirstName errorResponseFirstName = new ErrorResponseFirstName();
		
		errorResponseFirstName.setMsg(ex.getMessage());
		
		//errorResponseFirstName.setFirstname(ex.getFirstname());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseFirstName);
	}
	
}