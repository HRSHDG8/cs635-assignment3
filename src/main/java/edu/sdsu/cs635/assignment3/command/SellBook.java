package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.serialization.SellBookSerializer;
import edu.sdsu.cs635.assignment3.store.Inventory;

import java.util.Map;

@JsonSerialize(using = SellBookSerializer.class)
public class SellBook implements Command {
  private static final long serialVersionUID = -4778810560316362985L;
  private final Inventory<Integer, Book> bookInventory;

  private final Book book;

  public SellBook(Inventory<Integer, Book> bookInventory, Book book) {
    this.bookInventory = bookInventory;
    this.book = book;
  }

  public Book getBook() {
    return book;
  }

  @Override
  public void execute() {
    Map<Integer, Book> bookStore = bookInventory.getBookStore();
    if (bookStore.containsKey(book.getId())) {
      Book bookToBeSold = bookStore.get(book.getId());
      if (bookToBeSold.getQuantity() <= 0) {
        throw new RuntimeException("Book is not available");
      } else {
        bookToBeSold.setQuantity(bookToBeSold.getQuantity() - 1);
        bookStore.put(bookToBeSold.getId(), bookToBeSold);
      }
    } else {
      throw new RuntimeException("Book is not available");
    }
  }
}
