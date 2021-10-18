package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.inventory.Inventory;

import java.io.Serializable;

/**
 * Executes {@link Command} and holds the {@link Inventory} Object that it needs as a parameter.
 */
public class CommandInvoker implements Serializable {
  private static final long serialVersionUID = -4778810560316362988L;
  private final Inventory inventory;

  public CommandInvoker(Inventory inventory) {
    this.inventory = inventory;
  }

  public void execute(Command command) {
    command.execute(inventory);
  }
}
