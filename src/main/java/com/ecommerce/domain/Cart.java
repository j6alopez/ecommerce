package com.ecommerce.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int id;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private  List<Product> cartItemList;
	
}
