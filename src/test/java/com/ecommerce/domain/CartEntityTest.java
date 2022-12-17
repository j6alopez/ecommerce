package com.ecommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

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
public class CartEntityTest {
	
	@Mock
	private Cart cart;
	
	private final CartRepository cartRepository ;	
	private final ProductRepository productRepository ;		
	private final ArticleRepository articleRepository ;		
	
	@Autowired
	public CartEntityTest(CartRepository cartRepository, ProductRepository productRepository, ArticleRepository articleRepository ) {
		
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
		Cart savedCart = cartRepository.save(cart);
		
		List<Article> cartArticleList = articleRepository.findAll(); 
		Set<Product>  cartItemSet = new HashSet<Product>();
		cartArticleList.stream()
					   .forEach(article -> cartItemSet.add(new Product(savedCart, article, 0)));
		
		
		savedCart.setCartItemSet(cartItemSet);
		
		//when
		Cart cartWithProducts = cartRepository.save(savedCart);
		
		//then
		assertThat(cartWithProducts.getCartItemSet().size()).isEqualTo(cartArticleList.size());

	}

	@Test
	public void deleteCart() {
		//given		
		List<Article> cartArticleList = articleRepository.findAll(); 
		Set<Product>  cartItemSet = new HashSet<Product>();
		cartArticleList.stream()
					   .forEach(article -> cartItemSet.add(new Product(cart, article, 0)));
		//when
		Cart persistedCart = cartRepository.save(cart);
		cartRepository.delete(persistedCart);
		
		//then
		assertThat((int) cartRepository.count()).isZero();		
		assertThat((int) productRepository.count()).isZero();		

	}	
	
}
