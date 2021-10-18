package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {
  private Inventory bookInventory;


  @BeforeEach
  void setUp() {
    bookInventory = new BookInventory();
  }

  @Test
  @Order(1)
  public void addNew() {
    Book harryPotter = new Book("Harry Potter", 100.3f, 2);
    bookInventory.add(harryPotter);
    bookInventory.add(harryPotter);
    bookInventory.add(harryPotter);
    bookInventory.add(harryPotter);
    bookInventory.add(harryPotter);
    bookInventory.sell(harryPotter);
    bookInventory.sell(harryPotter);
    Book lordOfTheRings = new Book("Lord of the rings", 299.99f, 0);
    bookInventory.add(lordOfTheRings);
    bookInventory.sell(lordOfTheRings);
    bookInventory.add(lordOfTheRings);
    Book rickAndMorty = new Book("Rick and Morty", 29.99f, 1);
    bookInventory.add(rickAndMorty);
    bookInventory.sell(rickAndMorty);
    assertTrue(bookInventory.findById(lordOfTheRings.getIsbn()).isPresent());
  }

  @Test
  @Order(2)
  public void restore() throws IOException {
//    InventoryMemento inventoryMemento = objectMapper.readValue(fileOperator.readFile("inventory.ser"), InventoryMemento.class);
//    List<Command> commands = objectMapper.readValue(fileOperator.readFile("command.ser"), new TypeReference<List<Command>>() {
//    });
//    bookInventory.restore(inventoryMemento);
//    commands.forEach(command -> command.execute(bookInventory));
//    assertEquals(bookInventory.getBookStore().size(), 3);
  }
}
