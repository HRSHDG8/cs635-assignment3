package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.inventory.Inventory;

public class WithSaveToFile {

  private final Inventory inventory;

  public WithSaveToFile(Inventory inventory) {
    this.inventory = inventory;
  }

  public void execute(Command command) {
    Command decoratedCommand = new SaveToFile(command);
    decoratedCommand.execute(inventory);
  }

}
