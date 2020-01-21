package com.trail.clothes.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.trail.clothes.api.excepton.http.InternalServerErrorException;
import com.trail.clothes.api.excepton.http.NotFoundException;
import com.trail.clothes.api.json.response.ResponseMessage;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseMessage notFound(NotFoundException ex) {
		return ResponseMessage.builder().code("Not Found").message(ex.getError()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseMessage badRequestValidationFailed(MethodArgumentNotValidException e) {
		String error = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).findFirst()
				.orElse(null);
		return ResponseMessage.builder().code("Bad Request").message(error.toString()).build();
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<String> handleNumberFormat(NumberFormatException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

	@ExceptionHandler(InternalServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseMessage internalServerError(InternalServerErrorException ex) {
		return ResponseMessage.builder().code("Internal Server Error").message(ex.getError()).build();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseMessage genericServerError(Exception ex) {
		return ResponseMessage.builder().code("Internal Server Error").message(ex.getMessage()).build();
	}

}
