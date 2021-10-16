package edu.sdsu.cs635.assignment3.memento;

import edu.sdsu.cs635.assignment3.entity.Book;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InventoryMemento implements Serializable {
  private final Map<Integer, Book> bookStore;

  public InventoryMemento(Map<Integer, Book> map) {
    this.bookStore = new HashMap<>(map);
  }

  public Map<Integer, Book> getInventory() {
    return bookStore;
  }
}
