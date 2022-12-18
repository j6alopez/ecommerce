package com.ecommerce.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;
import com.ecommerce.repository.ArticleRepository;
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
	private final ArticleRepository articleRepository;

	/**
	 * Constructor used to avoid field dependency injection
	 */
	@Autowired
	public DefaultCartService(CartRepository cartRepository, ArticleRepository articleRepository) {

		this.cartRepository 	= cartRepository;
		this.articleRepository 	= articleRepository;
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
	
	public Optional<Cart> getCartbyId(Long id) {
		System.out.println(cartRepository.findById(id).toString());
		return  cartRepository.findById(id);	
	}

	/**
	 * Adds products to {@link com.ecommerce.domain.Cart} Remove elements when
	 * {@link com.ecommerce.domain.Product#getAmount()} is 0
	 */

	public Optional<Cart> addProductsToCart(Cart cart) {
		
		return 
	    cartRepository
		.findById(cart.getId())
		.map(
			mappedCart 
			-> { //Remove items with amount == 0 and persist Cart
				Set<Product> productSet = cart.getCartItemSet();
				productSet.removeIf(product -> product.getAmount() == 0);
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

}
