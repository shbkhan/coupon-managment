package com.shehab.couponmngmnt.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shehab.couponmngmnt.business.exception.DuplicateDataException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateDataException.class)
	public ResponseEntity<String> handleDuplicate(DuplicateDataException ex) {
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralExecption(Exception ex) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ex.getMessage());
	}
	
}
