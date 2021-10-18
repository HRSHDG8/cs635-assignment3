package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.decorator.SaveToFile;
import edu.sdsu.cs635.assignment3.inventory.Inventory;

public class CommandInvoker {

  private final Inventory inventory;

  public CommandInvoker(Inventory inventory) {
    this.inventory = inventory;
  }

  public void execute(Command command) {
    Command decoratedCommand = new SaveToFile(command);
    decoratedCommand.execute(inventory);
  }


}
