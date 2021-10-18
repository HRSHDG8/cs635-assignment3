package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookInventoryTest {
  private Inventory bookInventory;

  @BeforeEach
  public void init() {
    bookInventory = new BookInventory();
  }

  @Test
  public void testStart() {
    Book harryPotter = new Book("Harry Potter", 9.99f, 0);
    bookInventory.add(harryPotter);
    Optional<Book> bookOpt = bookInventory.findByName("Harry Potter");
    assertTrue(bookOpt.isPresent());
    Book book = bookOpt.get();
    assertEquals("Harry Potter", book.getName());
  }

  @Test
  public void sellBookWhenNotPresent() {
    Book book = new Book("Alice in wonder land", 99.99f, 1);
    assertThrows(RuntimeException.class, () -> bookInventory.sell(book));
  }

  @Test
  public void sellBookWhenQuantity() {
    Book book = new Book("Alice in wonder land", 99.99f, 1);
    bookInventory.add(book);
    bookInventory.sell(book);
    assertThrows(RuntimeException.class, () -> bookInventory.sell(book));
  }
}
