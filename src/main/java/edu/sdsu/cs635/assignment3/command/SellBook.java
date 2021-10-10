package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.store.Inventory;

import java.util.Map;

public class SellBook implements Command {
  private final Inventory<Integer, Book> bookInventory;
  private final Integer id;

  public SellBook(Inventory<Integer, Book> bookInventory, Integer id) {
    this.bookInventory = bookInventory;
    this.id = id;
  }

  @Override
  public void execute() {
    Map<Integer, Book> bookStore = bookInventory.getBookStore();
    if (bookStore.containsKey(id)) {
      Book book = bookStore.get(id);
      if (book.getQuantity() <= 0) {
        throw new RuntimeException("Book is not available");
      } else {
        book.setQuantity(book.getQuantity() - 1);
        bookStore.put(book.getId(), book);
      }
    } else {
      throw new RuntimeException("Book is not available");
    }
  }
}
