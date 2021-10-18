package edu.sdsu.cs635.assignment3.inventory;

import edu.sdsu.cs635.assignment3.Book;
import edu.sdsu.cs635.assignment3.InventoryState;

import java.io.Serializable;
import java.util.Optional;

public interface Inventory extends Serializable, Cloneable {
  void add(Book book);

  void sell(Book book);

  void update(Book book);

  Optional<Book> findById(Integer id);

  Optional<Book> findByName(String name);

  InventoryState createState();

  void restoreState(InventoryState state);
}
