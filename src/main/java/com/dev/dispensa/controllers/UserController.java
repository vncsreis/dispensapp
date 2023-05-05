package com.dev.dispensa.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dispensa.dao.StorageDao;
import com.dev.dispensa.dao.UserDao;
import com.dev.dispensa.models.Storage;
import com.dev.dispensa.models.User;
import com.dev.dispensa.repository.StorageRepository;
import com.dev.dispensa.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  StorageRepository storageRepository;

  @GetMapping()
  public ResponseEntity<List<User>> getUsers() {
    try {
      List<User> users = userRepository.findAll();

      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping()
  public ResponseEntity<User> createUser(@RequestBody UserDao userDao) {
    try {
      User user = new User(userDao.getName(), userDao.getEmail(), new ArrayList<>());

      user = userRepository.save(user);

      return new ResponseEntity<>(user, HttpStatus.CREATED);

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{userId}/storages")
  public ResponseEntity<List<Storage>> getUserStorages(@PathVariable("userId") Long userId) {
    try {
      Optional<User> optionalUser = userRepository.findById(userId);

      if (optionalUser.isPresent()) {
        User user = optionalUser.get();

        return new ResponseEntity<>(user.getStorages(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PostMapping("/{userId}/storages")
  public ResponseEntity<Storage> createUserStorage(@PathVariable("userId") Long userId,
      @RequestBody StorageDao storageDao) {

    try {
      Optional<User> optionalUser = userRepository.findById(userId);

      if (optionalUser.isPresent()) {
        User user = optionalUser.get();

        List<Storage> userStorages = user.getStorages();

        userStorages.add(new Storage(storageDao.getName()));

        user.setStorages(userStorages);

        userRepository.save(user);

        Storage returnStorage = user.getStorages().get(user.getStorages().size() - 1);

        return new ResponseEntity<>(returnStorage, HttpStatus.CREATED);

      } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
