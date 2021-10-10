package edu.sdsu.cs635.assignment3.store;

import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.memento.InventoryMemento;

import java.util.Map;
import java.util.Optional;

public interface Inventory<I, T> {
  void add(T t);

  boolean sell(I id);

  Optional<Book> findById(I i);

  Optional<Book> findByName(String name);

  Map<I, T> getBookStore();

  InventoryMemento createMemento();

  void restore(InventoryMemento inventoryMemento);
}
