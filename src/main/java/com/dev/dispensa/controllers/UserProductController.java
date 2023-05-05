package com.dev.dispensa.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dispensa.dao.ProductDao;
import com.dev.dispensa.models.Category;
import com.dev.dispensa.models.Product;
import com.dev.dispensa.models.Storage;
import com.dev.dispensa.models.User;
import com.dev.dispensa.repository.CategoryRepository;
import com.dev.dispensa.repository.ProductRepository;
import com.dev.dispensa.repository.StorageRepository;
import com.dev.dispensa.repository.UserRepository;

@RestController
@RequestMapping("user/{userId}/products")
public class UserProductController {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  StorageRepository storageRepository;

  @GetMapping()
  public ResponseEntity<List<Product>> getAllUserProducts(@PathVariable("userId") Long userId) {
    try {

      List<Product> products = new ArrayList<>();

      Optional<User> user = userRepository.findById(userId);

      if (user.isEmpty())
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

      List<Storage> storages = user.get().getStorages();

      storages.stream().forEach(
          (Storage storage) -> storage.getProducts().stream().forEach((Product product) -> products.add(product)));

      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (final Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PostMapping()
  public ResponseEntity<Product> createProduct(@PathVariable("userId") Long userId,
      @RequestBody ProductDao productDao) {
    try {

      Optional<Category> productCategory = categoryRepository.findById(productDao.getCategoryId());
      Optional<User> user = userRepository.findById(userId);
      Optional<Storage> storage = storageRepository.findById(productDao.getStorageId());

      if (productCategory.isEmpty() || user.isEmpty() || storage.isEmpty()) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

      }

      Product newProduct = new Product(productDao.getName(), productCategory.get(),
          productDao.getValue(),
          productDao.getMinimalAmount(), productDao.getAmount());

      Storage updatedStorage = storage.get();

      updatedStorage.getProducts().add(newProduct);

      storageRepository.save(updatedStorage);

      int len = updatedStorage.getProducts().size();

      Product responseProduct = storageRepository.findById(productDao.getStorageId()).get().getProducts().get(len - 1);

      return new ResponseEntity<>(responseProduct, HttpStatus.CREATED);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{storageId}/{productId}")
  public ResponseEntity<Product> updateProduct(@PathVariable("userId") Long userId,
      @PathVariable("storageId") Long storageId,
      @PathVariable("productId") Long productId,
      @RequestBody ProductDao productDao) {
    try {
      Optional<Storage> storage = storageRepository.findById(storageId);
      Optional<Category> productCategory = categoryRepository.findById(productDao.getCategoryId());

      if (storage.isEmpty()) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }

      Storage updatedStorage = storage.get();

      int index = getProductIndex(updatedStorage, productId);

      Product newProduct = new Product(productId, productDao.getName(), productCategory.get(),
          productDao.getValue(),
          productDao.getMinimalAmount(), productDao.getAmount());

      updatedStorage.getProducts().set(index, newProduct);

      storageRepository.save(updatedStorage);

      return new ResponseEntity<>(newProduct, HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @DeleteMapping("/{storageId}/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable("userId") Long userId,
      @PathVariable("storageId") Long storageId,
      @PathVariable("productId") Long productId) {
    try {
      Optional<Storage> storage = storageRepository.findById(storageId);

      if (storage.isEmpty()) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }

      Storage updatedStorage = storage.get();

      int index = getProductIndex(updatedStorage, productId);

      if (index == -1) {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }

      updatedStorage.getProducts().remove(index);

      storageRepository.save(updatedStorage);

      return new ResponseEntity<>(
          String.format("Product with id %s deleted from storage with id %s", productId, storageId), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  static private int getProductIndex(Storage storage, Long id) {
    List<Product> products = storage.getProducts();

    for (int i = 0; i < products.size(); i++) {
      if (products.get(i).getId() == id) {
        return i;
      }
    }

    return -1;
  }
}
