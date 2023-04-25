package com.dev.dispensa.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @Column(unique = true)
  private String email;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Storage> storages;

  public User() {

  }

  public User(final String name, final String email, final List<Storage> storages) {
    this.name = name;
    this.email = email;
    this.storages = storages;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Storage> getStorages() {
    return storages;
  }

  public void setStorages(List<Storage> storages) {
    this.storages = storages;
  }

}
