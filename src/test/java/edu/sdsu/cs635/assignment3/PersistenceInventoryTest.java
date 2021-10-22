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
public class PersistenceInventoryTest {
  private Inventory bookInventory;

  /**
   * Make sure each time the suite runs we get a fresh start, i.e. empty inventory and command only for testing purpose.
   */
  @BeforeAll
  static void clearInventoryAndCommands() throws IOException {
    Serializer serializer = Serializer.instance();
    serializer.write("inventory.ser", new InventoryState(new BookInventory()));
    serializer.write("command.ser", new ArrayList<>());
  }

  /**
   * Before each test method initialize the bookInventory to a fresh state.
   * This would simulate the scenario of the program stopping at a point and being successfully restored to its current state from last stored state.
   */
  @BeforeEach
  public void init() {
    bookInventory = new PersistedInventory(new BookInventory());
  }

  @Test
  @Order(1)
  @DisplayName("Initialize the inventory with some books")
  public void initialize() {
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
  @DisplayName("A fresh creation of PersistedInventory must have all contents from the previous run")
  public void restartAfterProgramCrash() {
    Optional<Book> bookOpt = bookInventory.findByName("Harry Potter");
    assertTrue(bookOpt.isPresent());
    Book book = bookOpt.get();
    assertEquals("Harry Potter", book.getName());
  }

  @Test
  @Order(3)
  @DisplayName("Persisted Inventory must not change the bases behavior of the Inventory, changes should still be immutable!!")
  public void changingABookNameAlongWithPriceDoesntChangeTheBookName() {
    Book book = new Book("Alice in wonder land", 99.99f, 1);
    bookInventory.add(book);
    // changes to book after it has been added to inventory should not alter the book in the inventory
    book.setName("A I W L");
    book.setPrice(49.99f);
    bookInventory.update(book);
    Optional<Book> aliceOpt = bookInventory.findById(book.getIsbn());
    assertTrue(aliceOpt.isPresent());
    Book alice = aliceOpt.get();
    assertEquals("Alice in wonder land", alice.getName());
  }
}
