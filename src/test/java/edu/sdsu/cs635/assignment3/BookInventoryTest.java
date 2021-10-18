package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.DecoratedInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import edu.sdsu.cs635.assignment3.inventory.InventoryState;
import edu.sdsu.cs635.assignment3.serialization.Serialization;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class BookInventoryTest {
  private Inventory bookInventory;

  @BeforeAll
  static void before() throws IOException {
    Serialization serialization = Serialization.getInstance();
    serialization.write("inventory.ser", new InventoryState(new BookInventory()));
    serialization.write("command.ser", new ArrayList<>());
  }

  @BeforeEach
  public void init() {
    bookInventory = new DecoratedInventory(new BookInventory());
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
    bookInventory.sell(path);
    bookInventory.sell(path);
    path.setPrice(19.99f);
    bookInventory.update(path);
  }

  @Test
  @Order(2)
  public void restoreFromFile() {

  }
}
