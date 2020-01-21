package com.trail.clothes.api.json.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.trail.clothes.api.model.Clothes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ClothesResponse {

	private String id;
	private String name;
	private String description;
	private Double price;
	private String category;

	public static ClothesResponse toResponse(Clothes clothes) {
		return ClothesResponse.builder()
				.id(clothes.getId())
				.name(clothes.getName())
				.description(clothes.getDescription())
				.price(clothes.getPrice())
				.category(clothes.getCategory())
				.build();
	}
}
