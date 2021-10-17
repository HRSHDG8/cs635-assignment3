package edu.sdsu.cs635.assignment3.store;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.CommandInvoker;
import edu.sdsu.cs635.assignment3.command.SellBook;
import edu.sdsu.cs635.assignment3.command.UpdatePrice;
import edu.sdsu.cs635.assignment3.file.FileOperator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookInventory {
  private Map<Integer, Book> bookStore;
  private final FileOperator fileOperator;
  private final CommandInvoker executor;

  public BookInventory() {
    this.bookStore = new HashMap<>();
    this.fileOperator = new FileOperator();
    this.executor = new CommandInvoker(this);
  }

  public Map<Integer, Book> getBookStore() {
    return bookStore;
  }


  public void add(Book book) {
    executor.execute(new AddBook(book));
  }


  public void sell(Book book) {
    executor.execute(new SellBook(book));
  }

  public void updatePrice(Book book) {
    executor.execute(new UpdatePrice(book));
  }


  public Optional<Book> findById(Integer id) {
    return Optional.ofNullable(bookStore.get(id));
  }


  public Optional<Book> findByName(String name) {
    return bookStore.values()
       .stream()
       .filter(book -> book.getName().equals(name))
       .findFirst();
  }


  public InventoryMemento createMemento() {
    InventoryMemento inventoryMemento = new InventoryMemento(bookStore);
    boolean didClear = fileOperator.clearFile("command.json", new TypeReference<List<Object>>() {
    });
    if (didClear) {
      fileOperator.writeToFile("inventory.json", inventoryMemento);
    }
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
