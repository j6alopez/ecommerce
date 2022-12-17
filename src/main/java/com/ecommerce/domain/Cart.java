package com.ecommerce.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a Cart in the Ecommerce
 * @author JoseAntonio Lopez
 * @version 1.0
*/
@Entity
@Table(name = "cart")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Positive
	@JsonProperty("cart_id")
	private  Long id;
	@JsonProperty("items")
	@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private  Set<Product> cartItemSet = new HashSet<>();
	
}
