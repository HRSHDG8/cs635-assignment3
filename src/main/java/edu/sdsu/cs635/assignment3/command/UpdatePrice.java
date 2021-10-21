package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.inventory.Inventory;

public class UpdatePrice implements Command {
  private static final long serialVersionUID = -2500573357300622862L;
  private final Book book;

  public UpdatePrice(Book book) {
    this.book = book;
  }

  @Override
  public void execute(Inventory inventory) {
    inventory.update(book);
  }
}
