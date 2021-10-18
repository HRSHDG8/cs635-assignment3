package edu.sdsu.cs635.assignment3;

import java.io.Serializable;

public class Book implements Serializable, Cloneable {
  private static final long serialVersionUID = -4778810560316362989L;
  private Integer isbn;
  private String name;
  private Float price;
  private Integer quantity;

  @Override
  public Book clone() {
    return new Book(isbn, name, price, quantity);
  }

  public Book(String name, Float price, Integer quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public Book(Integer isbn, String name, Float price, Integer quantity) {
    this(name, price, quantity);
    this.isbn = isbn;
  }

  public Integer getIsbn() {
    return isbn;
  }

  public void setIsbn(Integer isbn) {
    this.isbn = isbn;
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
