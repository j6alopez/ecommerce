package com.ecommerce.domain;

import com.ecommerce.domain.key.ProductKey;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Potential physical item to be sold which has been added to a {@link com.ecommerce.domain.Cart} 
 * @author Jose Antonio Lopez
 * @version 1.0
*/
@Entity
@IdClass(ProductKey.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "cart")
@ToString(exclude = "cart")
public class Product implements CartItem{
	
	@Id
	@ManyToOne
	private Cart cart;
	
	@Id
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Article article;
	
	private long amount;
	
}
