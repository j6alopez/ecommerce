package com.ecommerce.domain.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;
import com.ecommerce.service.CartService;

import jakarta.validation.constraints.Positive;

/**
 * RestController Class for /carts endpoint
 * @author Jose Antonio Lopez
 * @version 1.0
*/
@RestController
@RequestMapping(value = "/carts", produces = MediaType.APPLICATION_JSON_VALUE)
public final class CartController {
	
	private final CartService cartService;
	
	@Autowired
	public CartController (final CartService cartService) {
		
		this.cartService = cartService;
		
	}
	/**
	 * Returns accepted headers as well as Content-Type
	*/
	@RequestMapping(method = RequestMethod.OPTIONS)
	ResponseEntity<?> options(){
		
		return ResponseEntity.ok()
							 .contentType(MediaType.APPLICATION_JSON)
							 .allow(HttpMethod.GET,HttpMethod.POST,
									HttpMethod.DELETE)
							 		.build();
		
	}

	/**
	 * Creates a {@link com.ecommerce.domain.Cart} object
	*/		
	
	@PostMapping()	
	public ResponseEntity<?> createCart(){
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(cartService.createCart());
		
	}
	
	/**
	 * Returns {@link com.ecommerce.domain.Cart} content 
	*/	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCart(@PathVariable @Positive Long id){ 
		
		return ResponseEntity.ok("Body");
		
	}	

	/**
	 * Adds {@link com.ecommerce.domain.Cart} 
	 * @param id
	 * 		  Cart id
	 * @param addItems
	 * 		  Items to be added
	*/		
	@PostMapping("/{id}/add")
	public ResponseEntity<?> addItems(@PathVariable @Positive Long id, @RequestBody Set<Product> addItems){
		
		return ResponseEntity.ok("Body");
		
	}

	/**
	 * Deletes {@link com.ecommerce.domain.Cart} content 
	 * @param id
	 * 		  Cart id
	*/	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable @Positive Long id){
		
		return ResponseEntity.of(cartService.deleteCartById(id));
		
	}

}
	
	
