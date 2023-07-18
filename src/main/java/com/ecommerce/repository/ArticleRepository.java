package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.domain.Article;

/**
 * Repository for {@link com.ecommerce.domain.Article} for the ecommerce
 * 
 * @author Jose Antonio Lopez
 * @version 1.0
 */

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
