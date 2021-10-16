package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.serialization.SellBookDeSerializer;
import edu.sdsu.cs635.assignment3.serialization.SellBookSerializer;
import edu.sdsu.cs635.assignment3.store.BookInventory;

import java.util.Map;

@JsonSerialize(using = SellBookSerializer.class)
@JsonDeserialize(using = SellBookDeSerializer.class)
public class SellBook implements Command {
  private static final long serialVersionUID = -4778810560316362985L;

  private final Book book;

  public SellBook(Book book) {
    this.book = book;
  }

  public Book getBook() {
    return book;
  }

  @Override
  public void execute(BookInventory bookInventory) {
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
