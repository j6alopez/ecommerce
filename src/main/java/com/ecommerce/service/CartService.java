package com.ecommerce.service;

import java.util.Optional;

import com.ecommerce.domain.Cart;

/**
 * CartService Interface.Allows multiple services for {@link com.ecommerce.domain.Cart}
 * @author JoseAntonio Lopez
 * @version 1.0
*/
public interface CartService {
	
	public Cart createCart();
	public Optional<Cart> getCartbyId(Long id);	
	public Optional<Cart> addProductsToCart(Cart cart);
	public Optional<Cart> deleteCartById(Long id);
	
}
