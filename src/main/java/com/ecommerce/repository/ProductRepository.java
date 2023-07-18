package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.domain.Product;

/**
 * Repository for {@link com.ecommerce.domain.Product} for the ecommerce
 * 
 * @author Jose Antonio Lopez
 * @version 1.0
 */

public interface ProductRepository extends JpaRepository<Product,Long> {

}
