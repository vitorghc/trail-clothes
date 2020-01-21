package com.trail.clothes.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clothes")
public class Clothes {

	@Id
	private String id;
	private String name;
	private String description;
	private Double price;
	private String category;
}
