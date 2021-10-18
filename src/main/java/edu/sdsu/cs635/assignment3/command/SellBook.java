package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.BookInventory;
import edu.sdsu.cs635.assignment3.serialization.SellBookDeSerializer;
import edu.sdsu.cs635.assignment3.serialization.SellBookSerializer;

import java.util.Map;

@JsonSerialize(using = SellBookSerializer.class)
@JsonDeserialize(using = SellBookDeSerializer.class)
public class SellBook implements Command {
  private static final long serialVersionUID = -4778810560316362985L;

  private final Book book;

  public SellBook(Book book) {
    this.book = book;
  }

  @Override
  public void execute(BookInventory bookInventory) {
    Map<Integer, Book> bookStore = bookInventory.getBookStore();
    if (bookStore.containsKey(book.getIsbn())) {
      Book bookToBeSold = bookStore.get(book.getIsbn());
      if (bookToBeSold.getQuantity() <= 0) {
        throw new RuntimeException("Book is not available");
      } else {
        bookToBeSold.setQuantity(bookToBeSold.getQuantity() - 1);
        bookStore.put(bookToBeSold.getIsbn(), bookToBeSold);
      }
    } else {
      throw new RuntimeException("Book is not available");
    }
  }

  public Book getBook() {
    return book;
  }
}
