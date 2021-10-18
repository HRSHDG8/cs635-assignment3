package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.inventory.Inventory;

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
}
