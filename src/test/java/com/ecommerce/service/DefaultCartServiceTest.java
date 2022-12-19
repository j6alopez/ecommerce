package com.ecommerce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.ecommerce.domain.Article;
import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;
import com.ecommerce.repository.CartRepository;

/**
 * Tests Entity relations
 * @author Jose Antonio Lopez
 * @version 1.0
*/

@DataJpaTest
@Sql("/test.sql")
public class DefaultCartServiceTest {

	private AutoCloseable autoCloseAble;
	
	@Mock
	private CartRepository cartRepository;
	
	private CartService cartService; 
	
	@BeforeEach
	public void setUp() {
		
		autoCloseAble = MockitoAnnotations.openMocks(this);	
		cartService = new DefaultCartService(cartRepository);
	}
	
	@AfterEach
	public void cleanUp() throws Exception {
		
		autoCloseAble.close();
	}

	
	@Test
	public void createCart() {
		//given	
		
		Cart dummyCart = new Cart(1L);
		when(cartService.createCart()).thenReturn(dummyCart);
		//when
		Cart result = cartService.createCart();

		assertThat(result).isNotNull();
		verify(cartRepository, times(1)).save(new Cart());

	}

	@Test
	public void getCart() {
		//given	
		
		Cart dummyCart = new Cart(1L);
		when(cartService.getCartbyId(dummyCart.getId()))
						.thenReturn(Optional.of(dummyCart));
//		//when
		Optional<Cart> result = cartService.getCartbyId(dummyCart.getId());

		assertThat(result).isPresent();
		verify(cartRepository, times(1)).findById(dummyCart.getId());

	}	
	
	
	@Test
	public void addProductsToCart() {
		//given	
		Cart dummyCart = new Cart(1l);
		Set<Product>  cartItemSet = new HashSet<Product>();
		
		Article article = new Article(100L,"ItemTest1");	
		cartItemSet.add(new Product(dummyCart, article, 3));
		
		dummyCart.setCartItemSet(cartItemSet);
		//when
		when(cartRepository.save(dummyCart))
		   				   .thenReturn(dummyCart);
		
		when(cartService.addProductsToCart(dummyCart))
		   				.thenReturn(Optional.of(dummyCart));		
		//then
		Optional <Cart> response = cartService.addProductsToCart(dummyCart);
		
		verify(cartRepository, times(1)).findById(dummyCart.getId());		
		verify(cartRepository, times(1)).save(dummyCart);		
		assertThat(response).isPresent();

	}
	
	@Test
	public void deleteCart() {
		//given
		Cart dummyCart = new Cart(1L);
		
		when(cartRepository.findById(dummyCart.getId()))
						   .thenReturn(Optional.of(dummyCart));
		cartService.deleteCartById(dummyCart.getId());
		Mockito.verify(cartRepository,times(1)).deleteById(dummyCart.getId());

	}
	
}
