package com.dev.dispensa.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private Double value;
  private int minimalAmount;
  private int amount;

  @ManyToOne(cascade = CascadeType.ALL)
  private Category category;

  public Product() {
  }

  public Product(final String name, final Category category, Double value, int minimalAmount,
      int amount) {
    this.name = name;
    this.category = category;
    this.value = value;
    this.minimalAmount = minimalAmount;
    this.amount = amount;
  }

  public Product(final Long id, final String name, final Category category, Double value, int minimalAmount,
      int amount) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.value = value;
    this.minimalAmount = minimalAmount;
    this.amount = amount;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(final Category category) {
    this.category = category;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getMinimalAmount() {
    return minimalAmount;
  }

  public void setMinimalAmount(int minimalAmount) {
    this.minimalAmount = minimalAmount;
  }

}
