package edu.sdsu.cs635.assignment3.memento;

import edu.sdsu.cs635.assignment3.entity.Book;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InventoryMemento implements Serializable {
  private final Map<Integer, Book> inventory;

  public InventoryMemento() {
    this.inventory = new HashMap<>();
  }

  public InventoryMemento(Map<Integer, Book> inventory) {
    this();
    inventory.forEach((id, book) -> this.inventory.put(id, book.clone()));
  }

  public Map<Integer, Book> getInventory() {
    return inventory;
  }
}
