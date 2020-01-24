package com.trail.clothes.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.trail.clothes.api.json.request.ClothesRequest;
import com.trail.clothes.api.json.response.ClothesResponse;
import com.trail.clothes.api.service.TrailClothesService;

@RestController
public class TrailClothesController {
	
	@Autowired
	private TrailClothesService trailClothesService;

	@GetMapping("/clothes")
	public ResponseEntity<List<ClothesResponse>> getClothes() {
		return ResponseEntity.ok().body(trailClothesService.getClothes());
	}
	
	@GetMapping("/clothes/{clothesId}")
	public ResponseEntity<ClothesResponse> getClothesById(@PathVariable String clothesId) {
		return ResponseEntity.ok().body(trailClothesService.getClothesById(clothesId));
	}
	
	@PostMapping("/clothes")
	public ResponseEntity<Void> saveClothes(@Valid @RequestBody ClothesRequest request, UriComponentsBuilder uriBuilder) {
		ClothesResponse response = trailClothesService.saveClothes(request);
		return ResponseEntity.created(uriBuilder.path("/clothes/{clothesId}").buildAndExpand(response.getId()).toUri()).build();
	}
	
	@PutMapping("/clothes/{clothesId}")
	public ResponseEntity<Void> alterClothes(@Valid @RequestBody ClothesRequest request, @PathVariable String clothesId) {
		trailClothesService.alterClothes(request, clothesId);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/clothes/{clothesId}")
	public ResponseEntity<Void> alterPartialClothes(@RequestBody ClothesRequest request, @PathVariable String clothesId) {
		trailClothesService.alterPartialClothes(request, clothesId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/clothes/{clothesId}")
	public ResponseEntity<ClothesResponse> deleteClothesById(@PathVariable String clothesId) {
		trailClothesService.deleteClothesById(clothesId);
		return ResponseEntity.noContent().build();
	}
}
