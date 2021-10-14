package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.memento.InventoryMemento;
import edu.sdsu.cs635.assignment3.store.BookInventory;
import edu.sdsu.cs635.assignment3.store.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
  private Inventory<Integer, Book> bookInventory;

  @BeforeEach
  void setUp() {
    bookInventory = new BookInventory();
  }

  @Test
  void add() {
    Book harryPotter = new Book(null, "Harry Potter", 100.3f, 2);
    bookInventory.add(harryPotter);
    bookInventory.add(harryPotter);
    InventoryMemento inventoryMemento = bookInventory.createMemento();
    Book lordOfTheRings = new Book(null, "Lord of the rings", 299.99f, 0);
    Map<Integer, Book> map = inventoryMemento.getInventory();
    assertFalse(map.containsKey(lordOfTheRings.getId()));
    bookInventory.add(lordOfTheRings);
    inventoryMemento = bookInventory.createMemento();
    bookInventory.sell(lordOfTheRings);
    bookInventory.sell(harryPotter);
    bookInventory.add(new Book(null, "Rick and Morty", 29.99f, 1));
    bookInventory.restore(inventoryMemento);
    assertTrue(bookInventory.findById(lordOfTheRings.getId()).isPresent());
  }
}
