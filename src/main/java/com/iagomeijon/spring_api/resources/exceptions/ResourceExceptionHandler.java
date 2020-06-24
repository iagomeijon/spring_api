package com.iagomeijon.spring_api.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.iagomeijon.spring_api.services.exceptions.DataIntegratyException;
import com.iagomeijon.spring_api.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

		@ExceptionHandler(ObjectNotFoundException.class)
		public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
			StandartError error= new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
		
		@ExceptionHandler(DataIntegratyException.class)
		public ResponseEntity<StandartError> dataIntagrity(DataIntegratyException e, HttpServletRequest request){
			StandartError error= new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
}
