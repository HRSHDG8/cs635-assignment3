package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.BookInventory;
import edu.sdsu.cs635.assignment3.decorator.SaveToFile;

public class CommandInvoker {

  private final BookInventory inventory;

  public CommandInvoker(BookInventory inventory) {
    this.inventory = inventory;
  }

  public void execute(Command command) {
    Command decoratedCommand = new SaveToFile(command);
    decoratedCommand.execute(inventory);
  }


}
