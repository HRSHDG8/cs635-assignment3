package edu.sdsu.cs635.assignment3.memento;

import edu.sdsu.cs635.assignment3.entity.Book;

import java.util.HashMap;
import java.util.Map;

public class InventoryMemento {
  private final Map<Integer, Book> map;

  public InventoryMemento(Map<Integer, Book> map) {
    this.map = new HashMap<>(map);
  }

  public Map<Integer, Book> getInventory() {
    return map;
  }
}
