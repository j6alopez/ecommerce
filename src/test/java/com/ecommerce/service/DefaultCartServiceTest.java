package com.ecommerce.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.ecommerce.domain.Article;
import com.ecommerce.domain.Cart;
import com.ecommerce.domain.Product;
import com.ecommerce.repository.ArticleRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;

/**
 * Tests Entity relations
 * @author Jose Antonio Lopez
 * @version 1.0
*/

@DataJpaTest
@Sql("/test.sql")
public class DefaultCartServiceTest {
	
	@Mock
	private Cart cart;
	
	private final CartRepository cartRepository ;	
	private final ProductRepository productRepository ;		
	private final ArticleRepository articleRepository ;		
	
	@Autowired
	public DefaultCartServiceTest(CartRepository cartRepository, ProductRepository productRepository, ArticleRepository articleRepository ) {
		
		this.cartRepository    = cartRepository;
		this.productRepository = productRepository;
		this.articleRepository = articleRepository;
		
	}
	
	private AutoCloseable autoCloseAble;
	
	@BeforeEach
	public void setUp() {
		
		autoCloseAble = MockitoAnnotations.openMocks(this);
		//given		
		cart = new Cart();
				
	}
	
	@AfterEach
	public void cleanUp() throws Exception {
		
		autoCloseAble.close();
	}

	
	@Test
	public void createCart() {
		//when
		cartRepository.save(cart);
		//then
		assertThat( (int) cartRepository.count()).isOne();		

	}
	
	@Test
	public void addProductsToCart() {
		//given		
		Cart persistedCart = cartRepository.save(cart);
		
		List<Article> cartArticleList = articleRepository.findAll(); 
		Set<Product>  cartItemSet = new HashSet<Product>();
		cartArticleList.stream()
					   .forEach(article -> cartItemSet.add(new Product(persistedCart, article, 0)));
				
		persistedCart.setCartItemSet(cartItemSet);
		
		//when
		Cart cartWithProducts = cartRepository.save(persistedCart);
		
		//then
		assertThat(cartWithProducts.getCartItemSet().size()).isEqualTo(cartArticleList.size());
		assertThat(cartWithProducts.getCartItemSet().size()).isEqualTo(productRepository.count());

	}
	
	@Test
	public void deleteCart() {
		//given	
		
		Cart persistedCart = cartRepository.save(cart);
		//when
		
		cartRepository.deleteById(persistedCart.getId());
		Optional<Cart> optional =  cartRepository.findById(persistedCart.getId());
		//then
		assertThat(optional).isEmpty();

	}	
	
}
