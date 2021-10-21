package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.inventory.Inventory;

/**
 * Executes {@link Command} and holds the {@link Inventory} Object that it needs as a parameter.
 */
public class CommandInvoker {
  private final Inventory inventory;

  public CommandInvoker(Inventory inventory) {
    this.inventory = inventory;
  }

  public void execute(Command command) {
    command.execute(inventory);
  }
}
