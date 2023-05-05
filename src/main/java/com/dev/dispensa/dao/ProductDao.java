package com.dev.dispensa.dao;

public class ProductDao {
  private String name;
  private Long categoryId;
  private Double value;
  private int minimalAmount;
  private int amount;

  public ProductDao() {

  }

  public ProductDao(final String name, final Long categoryId, Double value, int minimalAmount, int amount) {
    this.name = name;
    this.categoryId = categoryId;
    this.value = value;
    this.minimalAmount = minimalAmount;
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public int getMinimalAmount() {
    return minimalAmount;
  }

  public void setMinimalAmount(int minimalAmount) {
    this.minimalAmount = minimalAmount;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

}
