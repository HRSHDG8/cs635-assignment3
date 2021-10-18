package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.command.*;
import edu.sdsu.cs635.assignment3.serialization.Serialization;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class DecoratedInventory implements Inventory {
  private static final long serialVersionUID = -4778810560316362991L;
  public static final String INVENTORY = "inventory.ser";
  public static final String COMMANDS = "command.ser";
  private final Inventory inventory;
  private final CommandInvoker invoker;
  private final Serialization serialization;

  @SuppressWarnings("unchecked")
  public DecoratedInventory(Inventory inventory) {
    this.inventory = inventory;
    this.serialization = Serialization.getInstance();
    this.invoker = new CommandInvoker(inventory);

    try {
      InventoryState inventoryState = (InventoryState) this.serialization.read(INVENTORY);
      this.restoreState(inventoryState);
      List<Command> commands = (List<Command>) this.serialization.read(COMMANDS);
      commands.forEach(command -> command.execute(inventory));
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
    serialization.write(INVENTORY, inventoryState);
    return inventoryState;
  }

  @Override
  public void restoreState(InventoryState state) {
    inventory.restoreState(state);
  }
}
