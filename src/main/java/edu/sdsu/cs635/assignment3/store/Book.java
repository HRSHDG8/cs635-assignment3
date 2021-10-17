package edu.sdsu.cs635.assignment3.store;

import java.io.Serializable;

public class Book implements Serializable, Cloneable {
  private static final long serialVersionUID = -4778810560316362986L;
  private Integer id;
  private String name;
  private Float price;
  private Integer quantity;

  @Override
  public Book clone() {
    try {
      return (Book) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public Book() {
  }

  public Book(String name, Float price, Integer quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public Book(Integer id, String name, Float price, Integer quantity) {
    this(name, price, quantity);
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
