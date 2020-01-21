package com.trail.clothes.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.trail.clothes.api.json.request.ClothesRequest;
import com.trail.clothes.api.json.response.ClothesResponse;
import com.trail.clothes.api.service.TrailClothesService;

@RestController
public class TrailClothesController {
	
	@Autowired
	private TrailClothesService trailClothesService;

	@RequestMapping("/clothes")
	public ResponseEntity<List<ClothesResponse>> getClothes() {
		return ResponseEntity.ok().body(trailClothesService.getClothes());
	}
	
	@RequestMapping(value = "/clothes", method = RequestMethod.POST)
	public ResponseEntity<Void> saveClothes(@Valid @RequestBody ClothesRequest request, UriComponentsBuilder uriBuilder) {
		ClothesResponse response = trailClothesService.saveClothes(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.location(uriBuilder.path("/clothes/{clothesId}").buildAndExpand(response.getId()).toUri()).build();
	}
}
