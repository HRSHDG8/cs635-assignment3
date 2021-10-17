package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.store.Book;
import edu.sdsu.cs635.assignment3.store.BookInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MementoTest {
  private BookInventory bookInventory;

  @BeforeEach
  public void init() {
    bookInventory = new BookInventory();
  }

  @Test
  public void createAndRestore() {
    bookInventory.add(new Book());
  }
}
