package com.trail.clothes.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/clothes/{clothesId}")
	public ResponseEntity<ClothesResponse> getClothesById(@PathVariable String clothesId) {
		return ResponseEntity.ok().body(trailClothesService.getClothesById(clothesId));
	}
	
	@RequestMapping(value = "/clothes", method = RequestMethod.POST)
	public ResponseEntity<Void> saveClothes(@Valid @RequestBody ClothesRequest request, UriComponentsBuilder uriBuilder) {
		ClothesResponse response = trailClothesService.saveClothes(request);
		return ResponseEntity.created(uriBuilder.path("/clothes/{clothesId}").buildAndExpand(response.getId()).toUri()).build();
	}
	
	@RequestMapping(value = "/clothes/{clothesId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterClothes(@Valid @RequestBody ClothesRequest request, @PathVariable String clothesId) {
		trailClothesService.alterClothes(request, clothesId);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/clothes/{clothesId}", method = RequestMethod.DELETE)
	public ResponseEntity<ClothesResponse> deleteClothesById(@PathVariable String clothesId) {
		trailClothesService.deleteClothesById(clothesId);
		return ResponseEntity.noContent().build();
	}
}
