package edu.sdsu.cs635.assignment3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MementoTest {
  private BookInventory bookInventory;

  @BeforeEach
  public void init() {
    bookInventory = new BookInventory();
  }

  @Test
  public void createAndRestore() {
    Book lordOfTheRings = new Book("Lord of the rings", 299.99f, 0);
    bookInventory.add(lordOfTheRings);
    BookInventory.InventoryMemento inventoryMemento = bookInventory.createMemento();
    Book updated = lordOfTheRings.clone();
    updated.setPrice(133.99f);
    bookInventory.updatePrice(updated);
    bookInventory.restore(inventoryMemento);
    Book bookFromInventory = bookInventory.findByName("Lord of the rings").get();
    assertEquals(299.99f, bookFromInventory.getPrice());
  }
}
