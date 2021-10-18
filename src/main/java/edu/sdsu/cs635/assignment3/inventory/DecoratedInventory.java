package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.command.SellBook;
import edu.sdsu.cs635.assignment3.command.UpdatePrice;
import edu.sdsu.cs635.assignment3.decorator.CommandInvoker;
import edu.sdsu.cs635.assignment3.decorator.WithSaveToFile;

import java.util.Optional;

public class DecoratedInventory implements Inventory {
  private Inventory inventory;
  private final CommandInvoker invoker;

  public DecoratedInventory(Inventory inventory) {
    this.inventory = inventory;
    this.invoker = new CommandInvoker(this);
  }

  @Override
  public void add(Book book) {
    Command add = new WithSaveToFile(new AddBook(book));
    invoker.execute(add);
  }

  @Override
  public void sell(Book book) {
    Command sell = new WithSaveToFile(new SellBook(book));
    invoker.execute(sell);
  }

  @Override
  public void update(Book book) {
    Command update = new WithSaveToFile(new UpdatePrice(book));
    invoker.execute(update);
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
