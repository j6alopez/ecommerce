package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.domain.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
