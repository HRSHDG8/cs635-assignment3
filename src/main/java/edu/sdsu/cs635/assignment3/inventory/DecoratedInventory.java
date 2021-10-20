package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;

import java.io.IOException;
import java.util.Optional;

public class DecoratedInventory implements Inventory {
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
  public InventoryState createState() throws IOException {
    return null;
  }

  @Override
  public void restoreState(InventoryState state) {

  }
}
