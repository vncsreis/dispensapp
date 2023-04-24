package com.dev.dispensa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.dispensa.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
