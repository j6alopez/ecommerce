package com.ecommerce.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a phyisical Article
 * @author Jose Antonio Lopez
 * @version 1.0
*/

@Entity
@Table(name ="article")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Article {
	@Id
	@Positive	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@NotBlank
	private String description;
	
}
