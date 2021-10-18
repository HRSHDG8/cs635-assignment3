package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.inventory.Inventory;

import java.io.Serializable;

public interface Command extends Serializable {
  void execute(Inventory inventory);
}
