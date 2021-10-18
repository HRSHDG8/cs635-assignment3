package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.serialization.AddBookDeSerializer;
import edu.sdsu.cs635.assignment3.serialization.AddBookSerializer;

@JsonSerialize(using = AddBookSerializer.class)
@JsonDeserialize(using = AddBookDeSerializer.class)
public class AddBook implements Command {
  private static final long serialVersionUID = -4778810560316362984L;
  private final Book book;

  public AddBook(Book book) {
    this.book = book;
  }

  @Override
  public void execute(Inventory inventory) {
    inventory.add(book);
  }

  public Book getBook() {
    return book;
  }
}
