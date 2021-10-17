package edu.sdsu.cs635.assignment3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.file.FileOperator;
import edu.sdsu.cs635.assignment3.serialization.Serialization;
import edu.sdsu.cs635.assignment3.store.Book;
import edu.sdsu.cs635.assignment3.store.BookInventory;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static edu.sdsu.cs635.assignment3.store.BookInventory.InventoryMemento;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {
  private BookInventory bookInventory;
  private final FileOperator fileOperator = new FileOperator();
  private final ObjectMapper objectMapper = Serialization.getInstance();


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
    assertTrue(bookInventory.findById(lordOfTheRings.getId()).isPresent());
  }

  @Test
  @Order(2)
  public void restore() throws IOException {
    InventoryMemento inventoryMemento = objectMapper.readValue(fileOperator.readFile("inventory.json"), InventoryMemento.class);
    List<Command> commands = objectMapper.readValue(fileOperator.readFile("command.json"), new TypeReference<List<Command>>() {
    });
    bookInventory.restore(inventoryMemento);
    commands.forEach(command -> command.execute(bookInventory));
    assertEquals(bookInventory.getBookStore().size(), 3);
  }
}