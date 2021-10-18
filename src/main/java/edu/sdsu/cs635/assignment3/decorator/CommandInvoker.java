package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.inventory.Inventory;

public class CommandInvoker {
  private final Inventory inventory;

  public CommandInvoker(Inventory inventory) {
    this.inventory = inventory;
  }

  public void execute(Command command) {
    command.execute(inventory);
  }
}
