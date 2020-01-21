package com.trail.clothes.api.excepton;

import com.trail.clothes.api.excepton.http.NotFoundException;

public class ClothesNotFoundException extends NotFoundException {

	private static final long serialVersionUID = 4566431105245497639L;

	public ClothesNotFoundException() {
		super("Clothes not found.");
	}
}
