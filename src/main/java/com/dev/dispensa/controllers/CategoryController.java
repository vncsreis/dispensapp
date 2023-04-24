package com.dev.dispensa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dispensa.models.Category;
import com.dev.dispensa.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  CategoryRepository categoryRepository;

  @GetMapping()
  public ResponseEntity<List<Category>> getAllCategories() {
    try {
      List<Category> categoryList = categoryRepository.findAll();

      return new ResponseEntity<>(categoryList, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping()
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    try {
      Category newCategory = new Category(category.getName(), category.getDescription());

      newCategory = categoryRepository.save(newCategory);

      return new ResponseEntity<>(newCategory, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
