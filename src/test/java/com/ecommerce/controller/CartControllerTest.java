package com.ecommerce.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.domain.Article;
import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;
import com.ecommerce.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CartController.class)
public class CartControllerTest {
	
	@MockBean
	private CartService cartService;
	
	@Autowired
	MockMvc mvc;
	
	private JacksonTester<Cart> json;
	
	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void createCart() throws Exception {
		
//		//given
		given(cartService.createCart()).willReturn(new Cart(1L));
		//when
		MockHttpServletResponse response = mvc.perform(post("/carts")
															.accept(MediaType.APPLICATION_JSON)
															).andReturn().getResponse();
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(new Cart(1L)).getJson());						
	}

	@Test
	public void getCorrectCart() throws Exception {
		
		//given
		given(cartService.createCart()).willReturn(new Cart(1L));		
		Optional<Cart> mockedCart = Optional.of(cartService.createCart());

		//when
		when(cartService.getCartbyId(1L)).thenReturn(mockedCart);
		MockHttpServletResponse response = mvc.perform(get("/carts/{id}",1L)
				.accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(new Cart(1L)).getJson());		
		
	}
	
	@Test
	public void getWrongCart() throws Exception {
		
		//given
		given(cartService.createCart()).willReturn(new Cart(1L));		
		Optional<Cart> mockedCart = Optional.empty();

		//when
		when(cartService.getCartbyId(1L)).thenReturn(mockedCart);
		MockHttpServletResponse response = mvc.perform(get("/carts/{id}",1L)
				.accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		
	}	

	@Test
	public void addProductsToCorrectCart() throws Exception {
		
		//given
		Cart mockedCart = new Cart(1l);
		Set<Product>  cartItemSet = new HashSet<Product>();
		
		Article article = new Article(100L,"ItemTest1");	
		cartItemSet.add(new Product(mockedCart, article, 3));
		
		mockedCart.setCartItemSet(cartItemSet);
		
		given(cartService.addProductsToCart(mockedCart))
						 .willReturn(Optional.of(mockedCart));
		//when
		MockHttpServletResponse response = mvc.perform(post("/carts/{id}/add",1L)
															.accept(MediaType.APPLICATION_JSON)
															.contentType(MediaType.APPLICATION_JSON)
															.content(json.write(mockedCart).getJson())
															).andReturn().getResponse();
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(mockedCart).getJson());	
	}

	@Test
	public void addProductsToWrongCart() throws Exception {
		
		//given
		Cart mockedCart = new Cart(1l);
		Set<Product>  cartItemSet = new HashSet<Product>();
		
		Article article = new Article(100L,"ItemTest1");	
		cartItemSet.add(new Product(mockedCart, article, 3));
		
		mockedCart.setCartItemSet(cartItemSet);
		
		given(cartService.addProductsToCart(mockedCart))
						 .willReturn(Optional.of(mockedCart));
		//when
		MockHttpServletResponse response = mvc.perform(post("/carts/{id}/add",2L)
															.accept(MediaType.APPLICATION_JSON)
															.contentType(MediaType.APPLICATION_JSON)
															.content(json.write(mockedCart).getJson())
															).andReturn().getResponse();
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}	
	
	@Test	
	public void deleteCorrectCart() throws Exception {
		
		//given
		given(cartService.createCart()).willReturn(new Cart(1L));		
		Optional<Cart> mockedCart = Optional.of(cartService.createCart());

		//when
		when(cartService.deleteCartById(1L)).thenReturn(mockedCart);
		
		MockHttpServletResponse response = mvc.perform(delete("/carts/{id}",1L)
				.accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}	
	@Test	
	public void deleteWrongCart() throws Exception {
		
		//given
		given(cartService.createCart()).willReturn(new Cart(1L));		
		Optional<Cart> mockedCart = Optional.empty();
		
		//when
		when(cartService.deleteCartById(1L)).thenReturn(mockedCart);
		
		MockHttpServletResponse response = mvc.perform(delete("/carts/{id}",1L)
				.accept(MediaType.APPLICATION_JSON)
				).andReturn().getResponse();
		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}	
}
