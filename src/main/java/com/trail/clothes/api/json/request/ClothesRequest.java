package com.trail.clothes.api.json.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ClothesRequest {

	@NotEmpty(message = "Field name is required.")
	private String name;
	@NotEmpty(message = "Field description is required.")
	private String description;
	@NotNull(message = "Field price is required.")
	private Double price;
	@NotEmpty(message = "Field category is required.")
	private String category;
}
