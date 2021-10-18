package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.BookInventory;
import edu.sdsu.cs635.assignment3.serialization.AddBookDeSerializer;
import edu.sdsu.cs635.assignment3.serialization.AddBookSerializer;

import java.util.Map;

@JsonSerialize(using = AddBookSerializer.class)
@JsonDeserialize(using = AddBookDeSerializer.class)
public class AddBook implements Command {
  private static final long serialVersionUID = -4778810560316362984L;
  private final Book book;

  public AddBook(Book book) {
    this.book = book;
  }

  @Override
  public void execute(BookInventory bookInventory) {
    Map<Integer, Book> bookStore = bookInventory.getBookStore();
    if (bookStore.containsKey(book.getIsbn())) {
      Book inventoryBook = bookStore.get(book.getIsbn());
      inventoryBook.setQuantity(inventoryBook.getQuantity() + 1);
    } else {
      if (book.getQuantity() <= 0) {
        book.setQuantity(1);
      }
      book.setIsbn(computeIndex(bookInventory));
    }
    bookStore.put(book.getIsbn(), book);
  }

  private int computeIndex(BookInventory bookInventory) {
    Map<Integer, Book> bookStore = bookInventory.getBookStore();
    return bookStore.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
  }

  public Book getBook() {
    return book;
  }
}
