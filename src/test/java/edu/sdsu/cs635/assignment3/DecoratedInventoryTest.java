package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.inventory.InventoryState;
import edu.sdsu.cs635.assignment3.inventory.PersistedInventory;
import edu.sdsu.cs635.assignment3.serialization.Serializer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class DecoratedInventoryTest {
  private Inventory bookInventory;

  @BeforeAll
  static void clearInventoryAndCommands() throws IOException {
    Serializer serializer = Serializer.getInstance();
    serializer.write("inventory.ser", new InventoryState(new BookInventory()));
    serializer.write("command.ser", new ArrayList<>());
  }

  @BeforeEach
  public void init() {
    bookInventory = new PersistedInventory(new BookInventory());
  }

  @Test
  @Order(1)
  public void testStart() {
    Book harryPotter = new Book("Harry Potter", 9.99f, 1);
    Book immortals = new Book("Immortals", 99.99f, 1);
    Book path = new Book("Frail Path", 9.99f, 1);
    bookInventory.add(harryPotter);
    bookInventory.add(harryPotter);
    bookInventory.sell(harryPotter);
    bookInventory.sell(harryPotter);
    bookInventory.add(immortals);
    bookInventory.add(immortals);
    bookInventory.sell(immortals);
    bookInventory.sell(immortals);
    bookInventory.add(path);
    bookInventory.add(path);
    //10 commands would create a memento
    bookInventory.sell(path);
    bookInventory.sell(path);
    path.setPrice(19.99f);
    bookInventory.update(path);
  }

  @Test
  @Order(2)
  public void findHarryPotterByNameAndItsQuantityMustBe0() {
    Optional<Book> bookOpt = bookInventory.findByName("Harry Potter");
    assertTrue(bookOpt.isPresent());
    Book book = bookOpt.get();
    assertEquals("Harry Potter", book.getName());
  }

  @Test
  @Order(3)
  public void changingABookNameAlongWithPriceDoesntChangeTheBookName() {
    Book book = new Book("Alice in wonder land", 99.99f, 1);
    bookInventory.add(book);
    book.setName("A I W L");
    book.setPrice(49.99f);
    bookInventory.update(book);
    Optional<Book> aliceOpt = bookInventory.findById(4);
    assertTrue(aliceOpt.isPresent());
    Book alice = aliceOpt.get();
    assertEquals("Alice in wonder land", alice.getName());
  }
}
