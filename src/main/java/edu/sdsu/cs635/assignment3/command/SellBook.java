package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.serialization.SellBookDeSerializer;
import edu.sdsu.cs635.assignment3.serialization.SellBookSerializer;

@JsonSerialize(using = SellBookSerializer.class)
@JsonDeserialize(using = SellBookDeSerializer.class)
public class SellBook implements Command {
  private static final long serialVersionUID = -4778810560316362985L;

  private final Book book;

  public SellBook(Book book) {
    this.book = book;
  }

  @Override
  public void execute(Inventory inventory) {
    inventory.sell(book);
  }

  public Book getBook() {
    return book;
  }
}
