package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
  @DisplayName("Add a book to inventory and find it by its name")
  public void findABookByName() {
    Book harryPotter = new Book("Harry Potter", 9.99f, 0);
    bookInventory.add(harryPotter);
    Optional<Book> bookOpt = bookInventory.findByName("Harry Potter");
    assertTrue(bookOpt.isPresent());
    Book book = bookOpt.get();
    assertEquals("Harry Potter", book.getName());
  }

  @Test
  @DisplayName("Expect a runtime exception when you search for a book not it the inventory")
  public void sellBookWhenNotPresent() {
    Book book = new Book("Alice in wonder land", 99.99f, 1);
    assertThrows(RuntimeException.class, () -> bookInventory.sell(book));
  }

  @Test
  @DisplayName("Expect a runtime exception when you try to sell a book whose quantity is 0")
  public void sellBookWhenQuantity() {
    Book book = new Book("Alice in wonder land", 99.99f, 1);
    bookInventory.add(book);
    bookInventory.sell(book);
    assertThrows(RuntimeException.class, () -> bookInventory.sell(book));
  }
}
