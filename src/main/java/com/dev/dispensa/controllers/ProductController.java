package com.dev.dispensa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dispensa.models.Product;
import com.dev.dispensa.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  @GetMapping()
  public ResponseEntity<List<Product>> getAllProducts() {
    try {

      final List<Product> products = productRepository.findAll();

      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (final Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}
