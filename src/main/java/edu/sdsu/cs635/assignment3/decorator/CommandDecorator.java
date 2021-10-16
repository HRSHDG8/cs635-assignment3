package edu.sdsu.cs635.assignment3.decorator;

import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.store.Inventory;

public abstract class CommandDecorator implements Command {
  Command command;

  public CommandDecorator(Command command) {
    this.command = command;
  }

  @Override
  public void execute(Inventory<Integer, Book> inventory) {
    command.execute(inventory);
  }
}
