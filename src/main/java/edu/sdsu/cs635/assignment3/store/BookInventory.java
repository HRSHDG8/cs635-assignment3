package edu.sdsu.cs635.assignment3.store;

import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.command.SellBook;
import edu.sdsu.cs635.assignment3.decorator.SaveToFileDecorator;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.memento.InventoryMemento;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookInventory implements Inventory<Integer, Book> {
  private Map<Integer, Book> bookStore;

  public BookInventory() {
    this.bookStore = new HashMap<>();
  }

  public Map<Integer, Book> getBookStore() {
    return bookStore;
  }

  @Override
  public void add(Book book) {
    Command addBook = new SaveToFileDecorator(new AddBook(this, book));
    addBook.execute();
  }

  @Override
  public boolean sell(Integer id) {
    try {
      Command sellCommand = new SaveToFileDecorator(new SellBook(this, id));
      sellCommand.execute();
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
    return new InventoryMemento(bookStore);
  }

  @Override
  public void restore(InventoryMemento inventoryMemento) {
    this.bookStore = inventoryMemento.getInventory();
  }
}
