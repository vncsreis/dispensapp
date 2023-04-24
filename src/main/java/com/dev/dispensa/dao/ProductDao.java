package com.dev.dispensa.dao;

public class ProductDao {
  private String name;
  private Long categoryId;

  public ProductDao() {

  }

  public ProductDao(final String name, final Long categoryId) {
    this.name = name;
    this.categoryId = categoryId;
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
}
