package com.trail.clothes.api.exception;

import com.trail.clothes.api.excepton.http.UnprocessableEntityException;

public class ClothesNameAlreadyExistsException extends UnprocessableEntityException {

	private static final long serialVersionUID = -2271454991014119400L;

	public ClothesNameAlreadyExistsException() {
		super("Clothes name already existis");
	}

}
