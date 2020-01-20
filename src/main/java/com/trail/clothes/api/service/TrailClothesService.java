package com.trail.clothes.api.service;

import org.springframework.stereotype.Service;

import com.trail.clothes.api.json.response.Clothes;

@Service
public class TrailClothesService {

	public Clothes getClothes() {
		return Clothes.builder().id("1").build();
	}

}
