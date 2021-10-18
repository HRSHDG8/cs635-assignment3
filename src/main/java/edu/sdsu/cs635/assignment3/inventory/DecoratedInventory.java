package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.InventoryState;
import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.command.SellBook;
import edu.sdsu.cs635.assignment3.command.UpdatePrice;
import edu.sdsu.cs635.assignment3.decorator.CommandInvoker;
import edu.sdsu.cs635.assignment3.decorator.WithSaveToFile;
import edu.sdsu.cs635.assignment3.serialization.Serialization;

import java.io.IOException;
import java.util.Optional;

public class DecoratedInventory implements Inventory {
  private static final long serialVersionUID = -4778810560316362991L;
  private final Inventory inventory;
  private final CommandInvoker invoker;
  private final Serialization serialization;

  public DecoratedInventory(Inventory inventory) {
    this.inventory = inventory;
    this.serialization = Serialization.getInstance();
    this.invoker = new CommandInvoker(inventory);
    InventoryState inventoryState;
    try {
      inventoryState = (InventoryState) this.serialization.read("inventory.ser");
      this.restoreState(inventoryState);
    } catch (IOException | ClassNotFoundException e) {

    }
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

  @Override
  public InventoryState createState() throws IOException {
    InventoryState inventoryState = inventory.createState();
    serialization.write("inventory.ser", inventoryState);
    return inventoryState;
  }

  @Override
  public void restoreState(InventoryState state) {
    inventory.restoreState(state);
  }
}
