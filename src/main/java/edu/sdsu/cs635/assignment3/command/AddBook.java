package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.store.Inventory;

import java.util.Map;

public class AddBook implements Command {

  private final Inventory<Integer, Book> bookInventory;
  private final Book book;

  public AddBook(Inventory<Integer, Book> bookInventory, Book book) {
    this.bookInventory = bookInventory;
    this.book = book;
  }

  @Override
  public void execute() {
    Map<Integer, Book> bookStore = bookInventory.getBookStore();
    if (bookStore.containsKey(book.getId())) {
      Book inventoryBook = bookStore.get(book.getId());
      inventoryBook.setQuantity(inventoryBook.getQuantity() + 1);
    } else {
      if (book.getQuantity() <= 0) {
        book.setQuantity(1);
      }
      book.setId(computeIndex());
    }
    bookStore.put(book.getId(), book);
  }


  private int computeIndex() {
    Map<Integer, Book> bookStore = bookInventory.getBookStore();
    return bookStore.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
  }
}
