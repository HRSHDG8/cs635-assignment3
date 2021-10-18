package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.command.CommandInvoker;
import edu.sdsu.cs635.assignment3.command.UpdatePrice;
import edu.sdsu.cs635.assignment3.decorator.SaveToFile;

import java.util.Optional;

public class DecoratedInventory implements Inventory {
  private Inventory inventory;
  private CommandInvoker invoker;

  public DecoratedInventory(Inventory inventory) {
    this.inventory = inventory;
    this.invoker = new CommandInvoker(this);
  }

  @Override
  public void add(Book book) {
    //
    inventory.add(book);
  }

  @Override
  public void sell(Book book) {
    //
    inventory.sell(book);
  }

  @Override
  public void update(Book book) {
    //
    // Command pattern should invoke this one below
    Command command = new SaveToFile(new UpdatePrice(book));
    inventory.update(book);
  }

  @Override
  public Optional<Book> findById(Integer id) {
    return inventory.findById(id);
  }

  @Override
  public Optional<Book> findByName(String name) {
    return inventory.findByName(name);
  }
}
