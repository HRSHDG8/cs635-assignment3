package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.command.*;
import edu.sdsu.cs635.assignment3.serialization.Serializer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Persistence Decorator over the {@link DecoratedInventory} abstract class to implement persistence to a file System.
 * Each method adds a layer of persistence via a {@link Command} and {@link WithSaveToFile} decorator.
 * The command internally invokes the respective methods on the {@link Inventory} implementation.
 */
public class PersistedInventory extends DecoratedInventory {
  public static final String INVENTORY = "inventory.ser";
  public static final String COMMANDS = "command.ser";
  private static final long serialVersionUID = -4778810560316362991L;
  private final CommandInvoker invoker;
  private final Serializer serializer;

  @SuppressWarnings("unchecked")
  public PersistedInventory(Inventory inventory) {
    super(inventory);
    this.serializer = Serializer.getInstance();
    this.invoker = new CommandInvoker(inventory);
    // reload inventory from its last saved state
    try {
      InventoryState inventoryState = (InventoryState) this.serializer.read(INVENTORY);
      this.restoreState(inventoryState);
      List<Command> commands = (List<Command>) this.serializer.read(COMMANDS);
      commands.forEach(command -> command.execute(inventory));
    } catch (IOException | ClassNotFoundException e) {
      System.err.println(e.getMessage());
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
  public Optional<Book> findById(Integer isbn) {
    return inventory.findById(isbn);
  }

  @Override
  public Optional<Book> findByName(String name) {
    return inventory.findByName(name);
  }

  @Override
  public InventoryState createState() {
    InventoryState inventoryState = inventory.createState();
    try {
      serializer.write(INVENTORY, inventoryState);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    return inventoryState;
  }

  @Override
  public void restoreState(InventoryState state) {
    inventory.restoreState(state);
  }
}
