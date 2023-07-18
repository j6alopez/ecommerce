package com.ecommerce.domain;

import com.ecommerce.domain.key.ProductKey;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "product")
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
	@JsonBackReference
	private Cart cart;
	
	@Id
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JsonUnwrapped
	private Article article;
	
	private long amount;
	
}
