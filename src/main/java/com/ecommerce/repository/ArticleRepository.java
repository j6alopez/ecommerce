package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
