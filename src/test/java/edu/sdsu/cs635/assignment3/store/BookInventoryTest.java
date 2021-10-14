package edu.sdsu.cs635.assignment3.store;

import edu.sdsu.cs635.assignment3.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookInventoryTest {

  private Inventory<Integer, Book> bookInventory;

  @BeforeEach
  void setUp() {
    bookInventory = new BookInventory();
  }

  @Test
  void add() {
    Book book = new Book(null, "Harry Potter", 100.3f, 2);
    bookInventory.add(book);
    bookInventory.add(book);
    assertEquals(3, book.getQuantity());
  }

  @Test
  void sell() {
    Book harryPotter = new Book(null, "Harry Potter", 100.3f, 10);
    bookInventory.add(harryPotter);
    bookInventory.add(harryPotter);
    Book lordOfTheRings = new Book(null, "Lord of the rings", 49.99f, 0);
    bookInventory.add(lordOfTheRings);
    assertTrue(bookInventory.sell(lordOfTheRings));
    assertFalse(bookInventory.sell(lordOfTheRings));
    assertFalse(bookInventory.sell(new Book(10, "No Book", 0.99f, 1)));
  }

  @Test
  void findById() {

  }

  @Test
  void findByName() {
  }
}
