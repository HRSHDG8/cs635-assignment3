package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.inventory.Inventory;

/**
 * Decorator over {@link Command} that would serialize and save the command to a file
 */
public class WithSaveToFile implements Command {

  private final Command command;

  public WithSaveToFile(Command command) {
    this.command = command;
  }

  @Override
  public void execute(Inventory inventory) {
    Command decoratedCommand = new SaveToFile(command);
    decoratedCommand.execute(inventory);
  }

}
