package edu.sdsu.cs635.assignment3.store;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.CommandInvoker;
import edu.sdsu.cs635.assignment3.command.SellBook;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.file.FileOperator;
import edu.sdsu.cs635.assignment3.memento.InventoryMemento;

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


  public boolean sell(Book book) {
    try {
      executor.execute(new SellBook(book));
      return true;
    } catch (RuntimeException r) {
      return false;
    }
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
}
