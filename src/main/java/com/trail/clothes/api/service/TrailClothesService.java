package com.trail.clothes.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.trail.clothes.api.json.response.ClothesResponse;
import com.trail.clothes.api.model.Clothes;
import com.trail.clothes.api.repository.TrailClothesRepository;

@Service
public class TrailClothesService {
	
	@Autowired
	private TrailClothesRepository trailClothesRepository;

	public List<ClothesResponse> getClothes() {
		List<ClothesResponse> clothesResponse = trailClothesRepository.findAll().stream()
				.map(ClothesResponse::toResponse)
				.collect(Collectors.toList());
		return clothesResponse;
	}
	
	public ClothesResponse getClothesById(String id) throws NotFoundException {
		Clothes clothesResponse = trailClothesRepository.findById(id).orElseThrow(NotFoundException::new);
		return ClothesResponse.toResponse(clothesResponse);
	}
	
}
