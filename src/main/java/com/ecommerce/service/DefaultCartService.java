package com.ecommerce.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;
import com.ecommerce.repository.CartRepository;

/**
 * Defualt {@link com.ecommerce.service.CartService} for the ecommerce
 * 
 * @author Jose Antonio Lopez
 * @version 1.0
 */
@Service
public class DefaultCartService implements CartService {

	private final CartRepository cartRepository;

	public DefaultCartService(CartRepository cartRepository) {

		this.cartRepository = cartRepository;
	}

	/**
	 * Creates a {@link com.ecommerce.domain.Cart}
	 */
	public Cart createCart() {
			
		return cartRepository.save(new Cart());		

	}

	/**
	 * Retrieves information about  {@link com.ecommerce.domain.Cart} 
	 */	
	@CachePut(value = "cart")
	public Optional<Cart> getCartbyId(Long id) {
		
		return  cartRepository.findById(id);	
	}

	/**
	 * Adds products to {@link com.ecommerce.domain.Cart} Remove elements when
	 * {@link com.ecommerce.domain.Product#getAmount()} is 0
	 */
	@Caching
	public Optional<Cart> addProductsToCart(Cart cart) {
		final int ZERO=0;
		return 
	    cartRepository
		.findById(cart.getId())
		.map(
			mappedCart 
			-> { //Remove items with amount == 0 and persist Cart
				Set<Product> productSet = cart.getCartItemSet();
				productSet.removeIf(product -> product.getAmount() == ZERO);
				cart.setCartItemSet(productSet);
				return Optional.of(cartRepository.save(cart));
			})
		.orElse(Optional.empty());  

	}

	/**
	 * Deletes entire {@link com.ecommerce.domain.Cart}
	 */

	public Optional<Cart> deleteCartById(Long id) {

		Optional<Cart> optional = cartRepository.findById(id);

		optional.ifPresent(action -> cartRepository.deleteById(id));

		return optional;

	}	


	@Scheduled(fixedRateString = "${caching.spring.cartTTL}")
	protected void runTTLPolicy(){
		
		cartRepository.deleteAll();
		
	}	

}
