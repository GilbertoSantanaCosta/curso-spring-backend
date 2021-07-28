package com.implementacao.mc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.implementacao.mc.exceptions.DataIntegrityException;
import com.implementacao.mc.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
}
