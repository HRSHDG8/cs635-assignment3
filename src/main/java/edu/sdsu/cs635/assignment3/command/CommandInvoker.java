package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.decorator.SaveToFile;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.store.Inventory;

public class CommandInvoker {

  private final Inventory<Integer, Book> inventory;

  public CommandInvoker(Inventory<Integer, Book> inventory) {
    this.inventory = inventory;
  }

  public void execute(Command command) {
    Command decoratedCommand = new SaveToFile(command);
    decoratedCommand.execute(inventory);
  }


}
