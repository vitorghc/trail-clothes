package com.trail.clothes.api.excepton.http;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UnprocessableEntityException extends RuntimeException {

	private static final long serialVersionUID = 323174537238345298L;

	private HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
	private String error;

	public UnprocessableEntityException() {
	}

	public UnprocessableEntityException(String error) {
		this.error = error;
	}

}
