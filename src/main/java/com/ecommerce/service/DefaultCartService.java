package com.ecommerce.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;
import com.ecommerce.repository.CartRepository;
/**
 * Defualt {@link com.ecommerce.service.CartService} for the ecommerce
 * @author JoseAntonio Lopez
 * @version 1.0
*/
public class DefualtCartService implements CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	public Cart createCart() {
		
		return cartRepository.save(new Cart());
		
	}

	/**
	 * Adds products to {@link com.ecommerce.domain.Cart} 
	 * Remove element when  {@link com.ecommerce.domain.Product#getAmount()} is 0
	*/	
	
	public Set<Product> addProductsToCart(Long id, Set<Product> cartItemList) {
		
		Cart cart = cartRepository.getReferenceById(id);
		cart.getCartItemList().addAll(cartItemList);
		cart.getCartItemList().removeIf(product -> product.getAmount() == 0);
		cartRepository.save(cart);
		
		return cart.getCartItemList();		
		
	}		

	/**
	 * Deletes entire {@link com.ecommerce.domain.Cart} 
	*/		
	
	public boolean deleteCart(Cart cart) {
		
		cartRepository.delete(cart);
		
		return cartRepository.findById(cart.getId())
							 .map(c -> true)
							 .orElse(false);		
	}	
	
}
