package com.dev.dispensa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.dispensa.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
