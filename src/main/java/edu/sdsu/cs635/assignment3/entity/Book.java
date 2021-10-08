package edu.sdsu.cs635.assignment3.entity;

import edu.sdsu.cs635.assignment3.memento.BookMemento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
  private static final long serialVersionUID = -4778810560316362986L;
  private Integer id;
  private String name;
  private Float price;
  private Integer quantity;

  public BookMemento createMemento() {
    return new BookMemento(id, name, price, quantity);
  }

  public void restore(BookMemento bookMemento) {
    if (bookMemento != null) {
      this.id = bookMemento.getId();
      this.name = bookMemento.getName();
      this.quantity = bookMemento.getQuantity();
      this.price = bookMemento.getPrice();
    } else {
      throw new IllegalArgumentException("memento cannot be null");
    }
  }
}
