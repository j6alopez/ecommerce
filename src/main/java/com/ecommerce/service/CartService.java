package com.ecommerce.service;

import java.util.Optional;
import java.util.Set;

import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;

/**
 * CartService Interface.Allows multiple services for {@link com.ecommerce.domain.Cart}
 * @author JoseAntonio Lopez
 * @version 1.0
*/
public interface CartService {
	
	public Cart createCart();
	public Optional<Cart> deleteCartById(Long id);
	public Cart addProductsToCart(Long id , Set<Product> cartItemList);
	
}
