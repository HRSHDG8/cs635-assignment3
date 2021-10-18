package edu.sdsu.cs635.assignment3;

import edu.sdsu.cs635.assignment3.command.AddBook;
import edu.sdsu.cs635.assignment3.command.Command;
import edu.sdsu.cs635.assignment3.command.SellBook;
import edu.sdsu.cs635.assignment3.inventory.BookInventory;
import edu.sdsu.cs635.assignment3.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
  private Inventory bookInventory;

  @BeforeEach
  public void init() {
    bookInventory = new BookInventory();
  }

  @Test
  public void addCommandTest() {
    Book lordOfTheRings = new Book("LOTR", 299.99f, 10);
    Command add = new AddBook(lordOfTheRings);
    add.execute(bookInventory);
    assertTrue(bookInventory.findByName("LOTR").isPresent());
  }

  @Test
  public void sellWhenBookExists() {
    Book lordOfTheRings = new Book("LOTR", 299.99f, 1);
    Command add = new AddBook(lordOfTheRings);
    add.execute(bookInventory);
    Command sell = new SellBook(lordOfTheRings);
    sell.execute(bookInventory);
    assertTrue(bookInventory.findByName("LOTR").isPresent());
  }

  @Test
  public void sellWhenNoBookExists() {
    Book lordOfTheRings = new Book("LOTR", 299.99f, 1);
    Command sell = new SellBook(lordOfTheRings);
    assertThrows(RuntimeException.class, () -> sell.execute(bookInventory));
  }

  @Test

  public void sellWhenBookHas0Quantity() {
    Book lordOfTheRings = new Book("LOTR", 299.99f, 1);
    Command add = new AddBook(lordOfTheRings);
    add.execute(bookInventory);
    Command sell = new SellBook(lordOfTheRings);
    sell.execute(bookInventory);
    assertThrows(RuntimeException.class, () -> sell.execute(bookInventory));
  }
}
