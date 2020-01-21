package com.trail.clothes.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.trail.clothes.api.model.Clothes;

@Repository
public interface TrailClothesRepository extends MongoRepository<Clothes, String>{
	
	Optional<Clothes> findByName(String name);
}
