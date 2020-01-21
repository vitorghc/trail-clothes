package com.trail.clothes.api.excepton.http;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 1513607735320639411L;
	
		private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		private String error;

		public InternalServerErrorException() {
		}

		public InternalServerErrorException(String error) {
			this.error = error;
		}

}
