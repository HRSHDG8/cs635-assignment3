package edu.sdsu.cs635.assignment3.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.serialization.UpdatePriceDeSerializer;
import edu.sdsu.cs635.assignment3.serialization.UpdatePriceSerializer;

@JsonSerialize(using = UpdatePriceSerializer.class)
@JsonDeserialize(using = UpdatePriceDeSerializer.class)
public class UpdatePrice implements Command {
  private static final long serialVersionUID = -4778810560316362984L;
  private final Book book;

  public UpdatePrice(Book book) {
    this.book = book;
  }

  @Override
  public void execute(Inventory inventory) {
    inventory.update(book);
  }

  public Book getBook() {
    return book;
  }
}
