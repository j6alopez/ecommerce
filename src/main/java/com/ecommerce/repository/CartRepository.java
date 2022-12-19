package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.domain.Cart;

/**
 * Repository for {@link com.ecommerce.domain.Cart} for the ecommerce
 * 
 * @author Jose Antonio Lopez
 * @version 1.0
 */

public interface CartRepository extends JpaRepository<Cart,Long> {

}
