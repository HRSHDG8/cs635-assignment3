package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.entity.Book;
import edu.sdsu.cs635.assignment3.memento.InventoryMemento;
import edu.sdsu.cs635.assignment3.store.BookInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainTest {
  private BookInventory bookInventory;

  @BeforeEach
  void setUp() {
    bookInventory = new BookInventory();
  }

  @Test
  void add() {
    Book book = new Book(null, "Harry Potter", 100.3f, 2);
    bookInventory.add(book);
    bookInventory.add(book);
    InventoryMemento inventoryMemento = bookInventory.createMemento();
    Book lordOfTheRings = new Book(null, "Lord of the rings", 299.99f, 0);
    bookInventory.add(lordOfTheRings);
    bookInventory.restore(inventoryMemento);
    assertFalse(bookInventory.findById(lordOfTheRings.getId()).isPresent());
  }
}
