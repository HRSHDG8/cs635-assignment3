package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;

import java.util.Optional;

public abstract class DecoratedInventory implements Inventory {
  private static final long serialVersionUID = 4581818108336464405L;
  protected Inventory inventory;

  public DecoratedInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  @Override
  public void add(Book book) {
    inventory.add(book);
  }

  @Override
  public void sell(Book book) {
    inventory.sell(book);
  }

  @Override
  public void update(Book book) {
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

  @Override
  public InventoryState createState() {
    return inventory.createState();
  }

  @Override
  public void restoreState(InventoryState state) {
    inventory.restoreState(state);
  }
}
