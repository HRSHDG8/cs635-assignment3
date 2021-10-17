package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.BookInventory;
import edu.sdsu.cs635.assignment3.serialization.UpdatePriceDeSerializer;
import edu.sdsu.cs635.assignment3.serialization.UpdatePriceSerializer;

import java.util.Map;

@JsonSerialize(using = UpdatePriceSerializer.class)
@JsonDeserialize(using = UpdatePriceDeSerializer.class)
public class UpdatePrice implements Command {
  private static final long serialVersionUID = -4778810560316362984L;
  private final Book book;

  public UpdatePrice(Book book) {
    this.book = book;
  }

  @Override
  public void execute(BookInventory bookInventory) {
    Map<Integer, Book> bookStore = bookInventory.getBookStore();
    if (bookStore.containsKey(book.getId())) {
      Book inventoryBook = bookStore.get(book.getId());
      inventoryBook.setPrice(book.getPrice());
    }
    bookStore.put(book.getId(), book);
  }

  public Book getBook() {
    return book;
  }
}
