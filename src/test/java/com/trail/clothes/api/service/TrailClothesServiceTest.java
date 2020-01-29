package com.trail.clothes.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.trail.clothes.api.exception.TrailClothesServerErrorException;
import com.trail.clothes.api.excepton.http.InternalServerErrorException;
import com.trail.clothes.api.excepton.http.NotFoundException;
import com.trail.clothes.api.json.response.ClothesResponse;
import com.trail.clothes.api.model.Clothes;
import com.trail.clothes.api.repository.TrailClothesRepository;

@RunWith(SpringRunner.class)
public class TrailClothesServiceTest {

	@Mock
	private TrailClothesRepository trailClothesRepository;

	@InjectMocks
	private TrailClothesService trailClothesService;

	private List<ClothesResponse> responseGetClothes;

	@Test
	public void getTrailClothesFromRepository() {
		givenFindAllFromRepositoryReturnValue();
		whenCallFindAll();
		thenExpectNotNullReturnFromGetClothes();
	}

	@Test(expected = NotFoundException.class)
	public void getTrailClothesFromRepositoryReturnNotFound() {
		givenFindAllFromRepositoryReturnNotFondException();
		whenCallFindAll();
		thenExpectClothesNotFoundException();
	}
	
	@Test(expected = InternalServerErrorException.class)
	public void getTrailClothesFromRepositoryReturnInternalServerError() {
		givenFindAllFromRepositoryReturnTrailClothesServerErrorException();
		whenCallFindAll();
		thenExpectTrailClothesServerErrorException();
	}

	
	// given
	private void givenFindAllFromRepositoryReturnNotFondException() {
		when(trailClothesRepository.findAll()).thenReturn(new ArrayList<>());
	}
	
	private void givenFindAllFromRepositoryReturnTrailClothesServerErrorException() {
		when(trailClothesRepository.findAll()).thenThrow(TrailClothesServerErrorException.class);
	}


	private void givenFindAllFromRepositoryReturnValue() {
		when(trailClothesRepository.findAll()).thenReturn(Arrays.asList(
				Clothes.builder().id("1").name("teste").description("teste").price(1D).category("teste").build()));
	}

	// when
	private void whenCallFindAll() {
		responseGetClothes = trailClothesService.getClothes();
	}

	// then
	private void thenExpectNotNullReturnFromGetClothes() {
		assertThat(responseGetClothes).isNotEmpty();
	}
	
	private void thenExpectClothesNotFoundException() {
		// Expect Clothes NotFound Exception
	}
	
	private void thenExpectTrailClothesServerErrorException() {
		// Expect Trail Clothes Server Error Exception
	}

}
