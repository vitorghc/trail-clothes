package com.trail.clothes.api.excepton.http;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1412809597256425505L;
	
	private String error;
	
	public NotFoundException(String error) {
		this.error = error;
	}
}

