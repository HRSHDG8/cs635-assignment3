package edu.sdsu.cs635.assignment3;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BookInventory {
  private Map<Integer, Book> bookStore;

  public BookInventory() {
    this.bookStore = new HashMap<>();
  }

  public InventoryMemento createMemento() {
    InventoryMemento inventoryMemento = new InventoryMemento(bookStore);
//    boolean didClear = fileOperator.clearFile("command.ser", new TypeReference<List<Object>>() {
//    });
//    if (didClear) {
//      fileOperator.writeToFile("inventory.ser", inventoryMemento);
//    }
    return inventoryMemento;
  }


  public void restore(InventoryMemento inventoryMemento) {
    this.bookStore = inventoryMemento.getInventory();
  }

  public static class InventoryMemento implements Serializable {
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
}
