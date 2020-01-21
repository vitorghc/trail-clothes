package com.trail.clothes.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.trail.clothes.api.excepton.ClothesNotFoundException;
import com.trail.clothes.api.excepton.http.InternalServerErrorException;
import com.trail.clothes.api.json.request.ClothesRequest;
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

	public ClothesResponse saveClothes(ClothesRequest request) {
		try {
			Clothes clothes = trailClothesRepository
					.save(Clothes.builder().name(request.getName()).description(request.getDescription())
							.price(request.getPrice()).category(request.getCategory()).build());
			return ClothesResponse.toResponse(clothes);
		} catch (Exception e) {
			throw new InternalServerErrorException("Error wwhile trying to save clothes: " + e);
		}
	}
	
	public ClothesResponse getClothesById(String id) {
		try {
			Clothes clothesResponse = trailClothesRepository.findById(id).orElseThrow(NotFoundException::new);
			return ClothesResponse.toResponse(clothesResponse);
		} catch (NotFoundException e) {
			throw new ClothesNotFoundException();
		} catch (Exception e) {
			throw new InternalServerErrorException("Error wwhile trying to get clothes by id: " + e);
		}
	}
	
}
