package com.dev.dispensa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dispensa.dao.ProductDao;
import com.dev.dispensa.models.Category;
import com.dev.dispensa.models.Product;
import com.dev.dispensa.repository.CategoryRepository;
import com.dev.dispensa.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @GetMapping()
  public ResponseEntity<List<Product>> getAllProducts() {
    try {

      final List<Product> products = productRepository.findAll();

      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (final Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PostMapping()
  public ResponseEntity<Product> createProduct(@RequestBody ProductDao productDao) {
    try {

      Optional<Category> productCategory = categoryRepository.findById(productDao.getCategoryId());

      if (productCategory.isEmpty()) {
        throw new Exception();
      }

      Product newProduct = new Product(productDao.getName(), productCategory.get());

      newProduct = productRepository.save(newProduct);

      return new ResponseEntity<>(newProduct, HttpStatus.CREATED);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}
