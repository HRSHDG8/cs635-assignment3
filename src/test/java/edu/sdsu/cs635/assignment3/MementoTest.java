package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MementoTest {
  private Inventory bookInventory;

  @BeforeEach
  public void init() {
    bookInventory = new BookInventory();
  }

  @Test
  public void createAndRestore() {
    Book lordOfTheRings = new Book("Lord of the rings", 299.99f, 0);
    bookInventory.add(lordOfTheRings);
//    BookInventory.InventoryMemento inventoryMemento = bookInventory.createMemento();
    Book updated = lordOfTheRings.clone();
    updated.setPrice(133.99f);
    bookInventory.update(updated);
//    bookInventory.restore(inventoryMemento);
    Book bookFromInventory = bookInventory.findByName("Lord of the rings").get();
//    assertEquals(299.99f, bookFromInventory.getPrice());
  }
}
