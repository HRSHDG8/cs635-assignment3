package edu.sdsu.cs635.assignment3.store;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.command.InventoryCommandExecutor;
import edu.sdsu.cs635.assignment3.command.SellBook;
import edu.sdsu.cs635.assignment3.decorator.SaveToFileDecorator;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.file.FileOperator;
import edu.sdsu.cs635.assignment3.memento.InventoryMemento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookInventory implements Inventory<Integer, Book> {
  private Map<Integer, Book> bookStore;
  private final FileOperator fileOperator;
  private final InventoryCommandExecutor executor;

  public BookInventory() {
    this.bookStore = new HashMap<>();
    this.fileOperator = new FileOperator();
    executor = new InventoryCommandExecutor(this);
  }

  public Map<Integer, Book> getBookStore() {
    return bookStore;
  }

  @Override
  public void add(Book book) {
    Command addBook = new SaveToFileDecorator(new AddBook(book));
    executor.execute(addBook);
  }

  @Override
  public boolean sell(Book book) {
    try {
      Command sellCommand = new SaveToFileDecorator(new SellBook(book));
      executor.execute(sellCommand);
      return true;
    } catch (RuntimeException r) {
      return false;
    }
  }

  @Override
  public Optional<Book> findById(Integer id) {
    return Optional.ofNullable(bookStore.get(id));
  }

  @Override
  public Optional<Book> findByName(String name) {
    return bookStore.values()
       .stream()
       .filter(book -> book.getName().equals(name))
       .findFirst();
  }

  @Override
  public InventoryMemento createMemento() {
    boolean didClear = fileOperator.clearFile("command.json", new TypeReference<List<Object>>() {
    });
    if (didClear) {
      fileOperator.writeToFile("inventory.json", this);
    }
    return new InventoryMemento(bookStore);
  }

  @Override
  public void restore(InventoryMemento inventoryMemento) {
    this.bookStore = inventoryMemento.getInventory();
  }
}
