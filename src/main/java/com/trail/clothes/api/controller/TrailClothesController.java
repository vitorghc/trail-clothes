package com.trail.clothes.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trail.clothes.api.json.response.Clothes;
import com.trail.clothes.api.service.TrailClothesService;

@RestController
public class TrailClothesController {
	
	@Autowired
	private TrailClothesService trailClothesService;

	@RequestMapping("/clothes")
	public ResponseEntity<Clothes> getClothes() {
		return ResponseEntity.ok().body(trailClothesService.getClothes());
	}
}
