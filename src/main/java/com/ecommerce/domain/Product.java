package com.ecommerce.domain;


import com.ecommerce.domain.key.ProductKey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@IdClass(ProductKey.class)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Product implements CartItem{
	
	@Id
	@ManyToOne
	private Cart cart;
	@Id
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Article article;
	
	@Override
	public String toString() {
		return "Product [article=" + article.getDescription() + "]";
	}
		
	
}
