package com.trail.clothes.api.excepton.http;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1412809597256425505L;
	
	private HttpStatus status = HttpStatus.NOT_FOUND;
	private String error;
	
	public NotFoundException(String error) {
		this.error = error;
	}
}

