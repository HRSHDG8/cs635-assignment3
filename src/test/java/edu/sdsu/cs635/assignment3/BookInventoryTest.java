package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.DecoratedInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BookInventoryTest {
  private Inventory bookInventory;

  @BeforeEach
  public void init() {
    bookInventory = new DecoratedInventory(new BookInventory());
  }

  @Test
  public void testStart() throws IOException {
    bookInventory.add(new Book("Harry", 9.99f, 10));
  }
}
