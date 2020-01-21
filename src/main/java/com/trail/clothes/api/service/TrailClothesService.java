package com.trail.clothes.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.trail.clothes.api.excepton.ClothesNameAlreadyExistsException;
import com.trail.clothes.api.excepton.ClothesNotFoundException;
import com.trail.clothes.api.excepton.http.InternalServerErrorException;
import com.trail.clothes.api.excepton.http.UnprocessableEntityException;
import com.trail.clothes.api.json.request.ClothesRequest;
import com.trail.clothes.api.json.response.ClothesResponse;
import com.trail.clothes.api.model.Clothes;
import com.trail.clothes.api.repository.TrailClothesRepository;

@Service
public class TrailClothesService {
	
	@Autowired
	private TrailClothesRepository trailClothesRepository;

	public List<ClothesResponse> getClothes() {
		try {
			List<ClothesResponse> clothesResponse = trailClothesRepository.findAll().stream()
					.map(ClothesResponse::toResponse).collect(Collectors.toList());
			clothesResponse.stream().findAny().orElseThrow(NotFoundException::new);
			return clothesResponse;
		} catch (NotFoundException e) {
			throw new ClothesNotFoundException();
		} catch (Exception e) {
			throw new InternalServerErrorException("Error wwhile trying to get clothes: " + e.getMessage());
		}
	}

	public ClothesResponse getClothesById(String id) {
		try {
			Clothes clothesResponse = trailClothesRepository.findById(id).orElseThrow(NotFoundException::new);
			return ClothesResponse.toResponse(clothesResponse);
		} catch (NotFoundException e) {
			throw new ClothesNotFoundException();
		} catch (Exception e) {
			throw new InternalServerErrorException("Error wwhile trying to get clothes by id: " + e.getMessage());
		}
	}
	
	public ClothesResponse saveClothes(ClothesRequest request) {
		try {
			verifyClothesNameExists(request);
			Clothes clothes = trailClothesRepository
					.save(Clothes.builder().name(request.getName()).description(request.getDescription())
							.price(request.getPrice()).category(request.getCategory()).build());
			return ClothesResponse.toResponse(clothes);
		} catch (UnprocessableEntityException e) {
			throw new ClothesNameAlreadyExistsException();
		} catch (Exception e) {
			throw new InternalServerErrorException("Error wwhile trying to save clothes: " + e.getMessage());
		}
	}

	public void alterClothes(ClothesRequest request, String clothesId) {
		try {
			trailClothesRepository.findById(clothesId).orElseThrow(NotFoundException::new);
			trailClothesRepository.save(Clothes.builder().id(clothesId)
					.name(request.getName())
					.description(request.getDescription())
					.price(request.getPrice())
					.category(request.getCategory())
					.build());
		} catch (NotFoundException e) {
			throw new ClothesNotFoundException();
		} catch (Exception e) {
			throw new InternalServerErrorException("Error wwhile trying to alter clothes: " + e.getMessage());
		}
	}
	
	public void deleteClothesById(String clothesId) {
		try {
			Clothes existsClothes = trailClothesRepository.findById(clothesId).orElseThrow(NotFoundException::new);
			trailClothesRepository.delete(existsClothes);
		} catch (NotFoundException e) {
			throw new ClothesNotFoundException();
		} catch (Exception e) {
			throw new InternalServerErrorException("Error wwhile trying to delete clothes: " + e.getMessage());
		}
	}

	private void verifyClothesNameExists(ClothesRequest request) {
		Optional<Clothes> existsClothes = trailClothesRepository.findByName(request.getName());
		if(existsClothes.isPresent()) {
			throw new ClothesNameAlreadyExistsException();
		}
	}
}
