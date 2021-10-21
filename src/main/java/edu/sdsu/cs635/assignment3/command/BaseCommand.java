package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.inventory.Inventory;

/**
 * Base Decorator for a {@link Command}
 */
public abstract class BaseCommand implements Command {
  private static final long serialVersionUID = -3068769635397196299L;
  Command command;

  public BaseCommand(Command command) {
    this.command = command;
  }

  @Override
  public void execute(Inventory inventory) {
    command.execute(inventory);
  }
}
