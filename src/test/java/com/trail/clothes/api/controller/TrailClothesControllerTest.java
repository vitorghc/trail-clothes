package com.trail.clothes.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.trail.clothes.api.json.response.ClothesResponse;
import com.trail.clothes.api.service.TrailClothesService;

public class TrailClothesControllerTest extends AbstractControllerTest{
	
	private static final String GET_CLOTHES = "/clothes";
	
	private static final String GET_CLOTHES_BY_ID = "/clothes/{id}"; 
	
	@MockBean
	private TrailClothesService trailClothesService;
	
	@Test
	public void getClothesSuccess() throws Exception {
		givenGetClothesReturnSuccess();
		whenCallGetClothes();
		thenExpectSuccessStatus();
	}
	
	@Test
	public void getClothesByIdSuccess() throws Exception {
		givenGetClothesByIdReturnSuccess();
		whenCallGetClothesById();
		thenExpectSuccessStatus();
	}

	//given
	private void givenGetClothesReturnSuccess() {
		when(trailClothesService.getClothes()).thenReturn(Arrays.asList(new ClothesResponse("12", "teste", "teste", 1.0,"teste")));
	}
	
	private void givenGetClothesByIdReturnSuccess() {
		when(trailClothesService.getClothesById(anyString())).thenReturn(new ClothesResponse("12", "teste", "teste", 1.0,"teste"));
	}

	//when
	private void whenCallGetClothes() throws Exception {
        response = mockMvc.perform(get(GET_CLOTHES))
                .andReturn()
                .getResponse();
	}
	
	private void whenCallGetClothesById() throws Exception {
		 response = mockMvc.perform(get(GET_CLOTHES_BY_ID, "1"))
			    .andReturn()
			    .getResponse();
	}
	
	//then
	private void thenExpectSuccessStatus() {
		assertThat(HttpStatus.OK.value()).isEqualTo(response.getStatus());
	}
	
}
