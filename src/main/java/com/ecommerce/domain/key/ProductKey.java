package com.ecommerce.domain.key;

import java.io.Serializable;

import com.ecommerce.domain.Article;
import com.ecommerce.domain.Cart;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class ProductKey implements Serializable {
	
	private Cart cart;
	private Article article;
	
}
