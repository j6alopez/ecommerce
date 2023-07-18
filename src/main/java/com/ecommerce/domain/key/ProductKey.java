package com.ecommerce.domain.key;

import java.io.Serializable;

import com.ecommerce.domain.Article;
import com.ecommerce.domain.Cart;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a primary key for {@link com.ecommerce.domain.Product}
 * @author JoseAntonio Lopez
 * @version 1.0
*/
@NoArgsConstructor
@EqualsAndHashCode
public class ProductKey implements Serializable {
	
	private Cart cart;
	private Article article;
	
}
