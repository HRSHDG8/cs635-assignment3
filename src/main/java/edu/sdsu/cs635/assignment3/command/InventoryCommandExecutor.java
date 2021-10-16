package edu.sdsu.cs635.assignment3.command;

import edu.sdsu.cs635.assignment3.decorator.SaveToFileDecorator;
import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.store.Inventory;

public class InventoryCommandExecutor {

  private Inventory<Integer, Book> inventory;

  public InventoryCommandExecutor(Inventory<Integer, Book> inventory) {
    this.inventory = inventory;
  }

  public void execute(Command command) {
    Command decoratedCommand = new SaveToFileDecorator(command);
    decoratedCommand.execute(inventory);
  }


}
