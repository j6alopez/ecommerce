package com.ecommerce.domain.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController Class for /carts endpoint
 * @author Jose Antonio Lopez
 * @version 1.0
*/
@RestController
@RequestMapping("/carts")
public class CartController {
		
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
	 * Returns {@link com.ecommerce.domain.Cart} content 
	*/	
	@GetMapping("/{id}")
	public void getCart(@PathVariable int id){ 
		
	}

	/**
	 * Creates a {@link com.ecommerce.domain.Cart} object
	*/		
	
	@PostMapping	
	public void createCart(@PathVariable int id){
					
	}

	/**
	 * Adds {@link com.ecommerce.domain.Cart} 
	 * @param id
	 * 		  Cart id
	 * @param cartItemList
	 * 		  List of items to be added
	*/		
	@PostMapping("/{id}/add")
	public void updateCart(@PathVariable int id){
		
	}

	/**
	 * Deletes {@link com.ecommerce.domain.Cart} content 
	 * @param id
	 * 		  Cart id
	*/	
	
	@DeleteMapping("/{id}")
	public void deleteCart(@PathVariable int id){
					
	}	
	
}
