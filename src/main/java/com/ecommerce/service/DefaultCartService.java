package com.ecommerce.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;
import com.ecommerce.repository.CartRepository;
/**
 * Defualt {@link com.ecommerce.service.CartService} for the ecommerce
 * @author Jose Antonio Lopez
 * @version 1.0
*/
@Service
public class DefaultCartService implements CartService {
	
	
	private final CartRepository cartRepository;

	/**
	 * Constructor used to avoid field dependency injection
	*/		
	
	public DefaultCartService(CartRepository cartRepository) {
		
		this.cartRepository = cartRepository;
	}
	
	public Cart createCart() {
		
		return cartRepository.save(new Cart());
		
	}

	/**
	 * Adds products to {@link com.ecommerce.domain.Cart} 
	 * Remove element when  {@link com.ecommerce.domain.Product#getAmount()} is 0
	*/	
	
	public Cart addProductsToCart(Long id, Set<Product> cartItemList) {
		
		Cart cart = cartRepository.getReferenceById(id);
		cart.getCartItemSet().addAll(cartItemList);
		cart.getCartItemSet().removeIf(product -> product.getAmount() == 0);
		cartRepository.save(cart);
		
		return cart;		
		
	}		

	/**
	 * Deletes entire {@link com.ecommerce.domain.Cart} 
	*/		
	
	public Optional<Cart> deleteCartById(Long id) {
		
		Optional<Cart> optional = cartRepository.findById(id);	
		
		optional.ifPresent(action -> cartRepository.deleteById(id));
		
		return optional;

	}	
	
}
